package com.saltside.bird.db;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response.Status;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.saltside.bird.exception.BirdException;
import com.saltside.bird.exception.BirdException.ErrorCode;
import com.saltside.bird.exception.BirdException.ErrorMessage;
import com.saltside.bird.model.Bird;

/**
 * @author santhoshsrinivasan
 *
 */
public class DBStore {

	private static DBStore INSTANCE = new DBStore();

	public static DBStore getInstance() {
		return INSTANCE;
	}

	MongoCollection<Document> col = MongoUtils.getCollectionByName("bird");

	public Bird add(Bird bird) {
		Gson gson = new Gson();
		Document doc = Document.parse(gson.toJson(bird));
		col.insertOne(doc);
		return bird.id(doc.getObjectId("_id").toHexString());
	}

	public Object[] get(String offset, int limit, boolean visible) throws BirdException {
		MongoCursor<Document> itr = null;
		Bson filter = Filters.eq("visible", visible);
		if (offset != null) {
			Bson idFilter = Filters.lte("_id", getAsObjectId(offset));
			itr = col.find(Filters.and(filter, idFilter)).limit(limit + 1).iterator();
		} else {
			itr = col.find(filter).limit(limit).iterator();
		}
		List<Bird> retList = new ArrayList<Bird>();
		int count = 0;
		String nextOffset = "END";
		while (itr.hasNext()) {
			Document doc = itr.next();
			if (count >= limit) {
				nextOffset = doc.getObjectId("_id").toHexString();
			}
			retList.add(getAsBird(doc));
		}
		return new Object[] { retList, nextOffset };
	}

	/**
	 * @param bird
	 * @return
	 */
	private Bird getAsBird(Document doc) {
		Gson gson = new Gson();
		Bird bird = gson.fromJson(doc.toJson(), Bird.class);
		bird.setId(doc.getObjectId("_id").toHexString());
		return bird;
	}

	public static void main(String[] args) throws BirdException {
		System.out.println(getInstance().get("599826f94befdf0a6666d657", 10, false));
		System.out.println(getInstance().get("59980f674befdf0a6666d655"));
		System.out.println(getInstance().delete("59980f674befdf0a6666d655"));
		System.out.println(getInstance().get(null, 10, true));
		System.out.println(getInstance().get("59980f674befdf0a6666d655"));
		System.out.println(getInstance().getByNameFamily("test", "testng"));
	}

	/**
	 * @param id
	 * @return
	 * @throws BirdException
	 */
	public Bird get(String id) throws BirdException {
		Bson filter = Filters.eq("_id", getAsObjectId(id));

		MongoCursor<Document> doc = col.find(filter).iterator();
		if (doc.hasNext()) {
			return getAsBird(doc.next());
		}
		return null;
	}

	private ObjectId getAsObjectId(String id) throws BirdException {
		try {
			return new ObjectId(id);
		} catch (Exception e) {
			throw new BirdException(Status.BAD_REQUEST, ErrorCode.NOT_FOUND, ErrorMessage.BirdNotFound);
		}
	}

	/**
	 * @param id
	 * @throws BirdException
	 */
	public boolean delete(String id) throws BirdException {
		Bson filter = Filters.eq("_id", getAsObjectId(id));
		Document doc = col.findOneAndDelete(filter);
		return doc != null;
	}

	/**
	 * @param name
	 * @param family
	 * @return
	 */
	public Bird getByNameFamily(String name, String family) {
		Bson nameFilter = Filters.eq("name", name);
		Bson familyFilter = Filters.eq("family", family);

		MongoCursor<Document> doc = col.find(Filters.and(nameFilter, familyFilter)).iterator();
		if (doc.hasNext()) {
			return getAsBird(doc.next());
		}
		return null;
	}
}

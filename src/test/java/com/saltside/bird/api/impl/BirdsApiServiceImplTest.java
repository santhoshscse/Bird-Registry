package com.saltside.bird.api.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.saltside.bird.api.BirdsApiService;
import com.saltside.bird.api.factory.BirdsApiServiceFactory;
import com.saltside.bird.model.ApiResponseMessage;
import com.saltside.bird.model.Bird;

public class BirdsApiServiceImplTest {
	private final BirdsApiService delegate = BirdsApiServiceFactory.getBirdsApi();
	private List<String> birdIdList = new ArrayList<String>();

	@Test
	public void testPostBird() {

		Bird body = new Bird().name("testPostBird").family("testBird").addContinentsItem("testng");
		Response res = testCreate(body);
		Bird bird = getAsBird(res);
		addToCleanupList(bird);

		Assert.assertEquals(Status.CREATED.getStatusCode(), res.getStatus());
	}

	@Test
	public void testPostDuplicateBird() {

		Bird body = new Bird().name("testPostDuplicateBird").family("testBird").addContinentsItem("testng");
		Response res = testCreate(body);
		Bird bird = getAsBird(res);
		addToCleanupList(bird);

		Assert.assertEquals(Status.CREATED.getStatusCode(), res.getStatus());

		res = testCreate(body);
		Assert.assertEquals(Status.BAD_REQUEST.getStatusCode(), res.getStatus());
	}

	private Bird getAsBird(Response res) {
		ApiResponseMessage apiRes = (ApiResponseMessage) res.getEntity();
		Bird bird = (Bird) apiRes.getData();
		Assert.assertNotNull(bird.getId());
		return bird;
	}

	/**
	 * @param bird
	 */
	private void addToCleanupList(Bird bird) {
		birdIdList.add(bird.getId());
	}

	@Test
	public void testPostBirdWithoutName() {
		Bird body = new Bird().family("testng").addContinentsItem("india");
		Response res = testCreate(body);
		Assert.assertEquals(Status.BAD_REQUEST.getStatusCode(), res.getStatus());
	}

	@Test
	public void testPostBirdWithoutFamily() {
		Bird body = new Bird().name("name").addContinentsItem("india");
		Response res = testCreate(body);
		Assert.assertEquals(Status.BAD_REQUEST.getStatusCode(), res.getStatus());
	}

	@Test
	public void testPostBirdWithoutContinent() {
		Bird body = new Bird().name("name").family("testng");
		Response res = testCreate(body);
		Assert.assertEquals(Status.BAD_REQUEST.getStatusCode(), res.getStatus());
	}

	private Response testCreate(Bird body) {
		return delegate.postBirds(body, null);
	}

	@Test
	public void testDeleteExistingBird() {
		Bird body = new Bird().name("testDeleteExistingBird").family("testBird").addContinentsItem("testng");
		Response res = testCreate(body);
		Bird createdBird = getAsBird(res);
		addToCleanupList(createdBird);

		Assert.assertEquals(Status.CREATED.getStatusCode(), res.getStatus());
		Response delRes = delegate.deleteBirdsId(createdBird.getId(), null);
		Assert.assertEquals(Status.OK.getStatusCode(), delRes.getStatus());
	}

	@Test
	public void testDeleteNonExistingBird() {
		Response delRes = delegate.deleteBirdsId("59987d494befdf5236cb796b", null);
		Assert.assertEquals(Status.NOT_FOUND.getStatusCode(), delRes.getStatus());
	}

	@Test
	public void testGetBirds() {
		Bird bird1 = new Bird().name("testGetBirds1").family("testBird").addContinentsItem("testng").visible(true);
		Response res = testCreate(bird1);
		addToCleanupList(getAsBird(res));
		Assert.assertEquals(Status.CREATED.getStatusCode(), res.getStatus());

		Bird bird2 = new Bird().name("testGetBirds2").family("testBird").addContinentsItem("testng");
		res = testCreate(bird2);
		addToCleanupList(getAsBird(res));
		Assert.assertEquals(Status.CREATED.getStatusCode(), res.getStatus());

		res = delegate.getBirds(new BigDecimal("10"), null, null);
		Assert.assertEquals(Status.OK.getStatusCode(), res.getStatus());
		ApiResponseMessage apiRes = (ApiResponseMessage) res.getEntity();
		List<Bird> birdList = (List<Bird>) apiRes.getData();
		Assert.assertTrue(birdList.size() > 0);
		for (Bird bird : birdList) {
			Assert.assertEquals(true, bird.getVisible().booleanValue());
		}
	}

	@Test
	public void testGetBirdById() {
		Bird bird = new Bird().name("testGetBirdById").family("testBird").addContinentsItem("testng").visible(true);
		Response res = testCreate(bird);
		addToCleanupList(getAsBird(res));

		Assert.assertEquals(Status.CREATED.getStatusCode(), res.getStatus());

		res = delegate.getBirdsId(bird.getId(), null);
		Assert.assertEquals(Status.OK.getStatusCode(), res.getStatus());
	}

	@AfterClass
	public void clear() {
		for (String id : birdIdList) {
			delegate.deleteBirdsId(id, null);
		}
	}
}

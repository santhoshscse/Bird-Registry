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
		Bird bird = testCreate();
		birdIdList.add(bird.getId());
	}

	private Bird testCreate() {
		Bird body = new Bird().name("test").family("testng").addContinentsItem("india");
		Response res = delegate.postBirds(body, null);
		Assert.assertEquals(Status.OK.getStatusCode(), res.getStatus());
		ApiResponseMessage apiRes = (ApiResponseMessage) res.getEntity();
		Bird bird = (Bird) apiRes.getData();
		Assert.assertNotNull(bird.getId());
		return bird;
	}

	@Test
	public void testPostBirdWithoutName() {
		Bird body = new Bird().family("testng").addContinentsItem("india");
		testCreate(body);
	}

	@Test
	public void testPostBirdWithoutFamily() {
		Bird body = new Bird().name("name").addContinentsItem("india");
		testCreate(body);
	}

	@Test
	public void testPostBirdWithoutContinent() {
		Bird body = new Bird().name("name").family("testng");
		testCreate(body);
	}

	private void testCreate(Bird body) {
		Response res = delegate.postBirds(body, null);
		Assert.assertEquals(Status.BAD_REQUEST.getStatusCode(), res.getStatus());
	}

	@Test
	public void testDeleteExistingBird() {
		Bird bird = testCreate();
		Response delRes = delegate.deleteBirdsId(bird.getId(), null);
		Assert.assertEquals(Status.OK.getStatusCode(), delRes.getStatus());
	}

	@Test
	public void testDeleteNonExistingBird() {
		Response delRes = delegate.deleteBirdsId("59987d494befdf5236cb796b", null);
		Assert.assertEquals(Status.NOT_FOUND.getStatusCode(), delRes.getStatus());
	}

	@Test
	public void testGetBirds() {
		Response res = delegate.getBirds(new BigDecimal("10"), null, null);
		Assert.assertEquals(Status.OK.getStatusCode(), res.getStatus());
	}

	@Test
	public void testGetBirdById() {
		Bird bird = testCreate();
		birdIdList.add(bird.getId());
		Response res = delegate.getBirdsId(bird.getId(), null);
		Assert.assertEquals(Status.OK.getStatusCode(), res.getStatus());
	}

	@AfterClass
	public void clear() {
		for (String id : birdIdList) {
			delegate.deleteBirdsId(id, null);
		}
	}
}

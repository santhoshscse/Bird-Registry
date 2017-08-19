package com.saltside.bird.api.impl;

import java.math.BigDecimal;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.saltside.bird.api.BirdsApiService;
import com.saltside.bird.api.factory.BirdsApiServiceFactory;
import com.saltside.bird.model.Bird;

public class BirdsApiServiceImplTest {
	private final BirdsApiService delegate = BirdsApiServiceFactory.getBirdsApi();

	@Test
	public void testPostBird() {
		Bird body = new Bird().name("test").family("testng").addContinentsItem("india");
		Response res = delegate.postBirds(body, null);
		Assert.assertEquals(Status.OK.getStatusCode(), res.getStatus());
	}

	@Test
	public void testGetBirds() {
		Response res = delegate.getBirds(new BigDecimal("10"), null, null);
		Assert.assertEquals(Status.OK.getStatusCode(), res.getStatus());
	}

	@AfterClass
	public void clear() {

	}
}

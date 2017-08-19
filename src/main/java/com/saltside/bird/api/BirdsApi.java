package com.saltside.bird.api;

import java.math.BigDecimal;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.saltside.bird.api.factory.BirdsApiServiceFactory;
import com.saltside.bird.model.Bird;
import com.saltside.bird.model.SuccessResponse;
import com.wordnik.swagger.annotations.ApiParam;

@Path("/birds")

@com.wordnik.swagger.annotations.Api(description = "the birds API")
@javax.annotation.Generated(value = "com.wordnik.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-08-19T05:47:47.983Z")
public class BirdsApi {
	private final BirdsApiService delegate = BirdsApiServiceFactory.getBirdsApi();

	@DELETE
	@Path("/{id}")

	@Produces({ "application/json" })
	@com.wordnik.swagger.annotations.ApiOperation(value = "Delete bird by id", notes = "", response = SuccessResponse.class, tags = {})
	@com.wordnik.swagger.annotations.ApiResponses(value = {
			@com.wordnik.swagger.annotations.ApiResponse(code = 200, message = "", response = SuccessResponse.class),

			@com.wordnik.swagger.annotations.ApiResponse(code = 404, message = "", response = SuccessResponse.class) })
	public Response deleteBirdsId(@ApiParam(value = "", required = true) @PathParam("id") String id,
			@Context SecurityContext securityContext) {
		return delegate.deleteBirdsId(id, securityContext);
	}

	@GET

	@Produces({ "application/json" })
	@com.wordnik.swagger.annotations.ApiOperation(value = "List all birds", notes = "", tags = {})
	@com.wordnik.swagger.annotations.ApiResponses(value = {
			@com.wordnik.swagger.annotations.ApiResponse(code = 200, message = "") })
	public Response getBirds(
			@ApiParam(value = "no.of records to return", defaultValue = "20") @DefaultValue("20") @QueryParam("limit") BigDecimal limit,
			@ApiParam(value = "") @QueryParam("offset") String offset, @Context SecurityContext securityContext) {
		return delegate.getBirds(limit, offset, securityContext);
	}

	@GET
	@Path("/{id}")

	@Produces({ "application/json" })
	@com.wordnik.swagger.annotations.ApiOperation(value = "Get bird by id", notes = "", response = Bird.class, tags = {})
	@com.wordnik.swagger.annotations.ApiResponses(value = {
			@com.wordnik.swagger.annotations.ApiResponse(code = 200, message = "", response = Bird.class),

			@com.wordnik.swagger.annotations.ApiResponse(code = 404, message = "", response = Bird.class) })
	public Response getBirdsId(@ApiParam(value = "", required = true) @PathParam("id") String id,
			@Context SecurityContext securityContext) {
		return delegate.getBirdsId(id, securityContext);
	}

	@POST

	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@com.wordnik.swagger.annotations.ApiOperation(value = "Add bird", notes = "Add a new bird to the library", response = Bird.class, tags = {})
	@com.wordnik.swagger.annotations.ApiResponses(value = {
			@com.wordnik.swagger.annotations.ApiResponse(code = 201, message = "", response = Bird.class),

			@com.wordnik.swagger.annotations.ApiResponse(code = 400, message = "", response = Bird.class) })
	public Response postBirds(@ApiParam(value = "") Bird body, @Context SecurityContext securityContext) {
		return delegate.postBirds(body, securityContext);
	}
}

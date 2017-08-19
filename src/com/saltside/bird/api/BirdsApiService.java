package com.saltside.bird.api;

import java.math.BigDecimal;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.saltside.bird.model.Bird;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-08-19T05:47:47.983Z")
public abstract class BirdsApiService {
	public abstract Response deleteBirdsId(String id, SecurityContext securityContext);

	public abstract Response getBirds(BigDecimal limit, String offset, SecurityContext securityContext);

	public abstract Response getBirdsId(String id, SecurityContext securityContext);

	public abstract Response postBirds(Bird body, SecurityContext securityContext);
}

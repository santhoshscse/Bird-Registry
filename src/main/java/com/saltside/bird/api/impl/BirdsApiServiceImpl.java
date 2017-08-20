package com.saltside.bird.api.impl;

import java.math.BigDecimal;
import java.util.Date;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import com.saltside.bird.api.BirdsApiService;
import com.saltside.bird.db.DBStore;
import com.saltside.bird.exception.BirdException;
import com.saltside.bird.exception.BirdException.ErrorCode;
import com.saltside.bird.exception.BirdException.ErrorMessage;
import com.saltside.bird.model.Bird;
import com.saltside.bird.util.DateTimeUtil;
import com.saltside.bird.util.ResponseUtil;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-08-19T05:47:47.983Z")
public class BirdsApiServiceImpl extends BirdsApiService {
	@Override
	public Response deleteBirdsId(String id, SecurityContext securityContext) {
		try {

			boolean st = DBStore.getInstance().delete(id);
			if (!st) {
				throw new BirdException(Status.NOT_FOUND, ErrorCode.NOT_FOUND, ErrorMessage.BirdNotFound);
			}
			return ResponseUtil.getSuccessResponse("Deleted successfully");
		} catch (BirdException e) {
			return ResponseUtil.getBirdExceptionResponse(e);
		} catch (Exception e) {
			return ResponseUtil.getUnknownExceptionResponse(e);
		}
	}

	@Override
	public Response getBirds(BigDecimal limit, String offset, SecurityContext securityContext) {
		try {
			int limitVal = 20;
			if (limit != null) {
				limitVal = limit.intValue();
			}
			if (limitVal > 20) {
				limitVal = 20;
			}
			return ResponseUtil.getSuccessResponse(DBStore.getInstance().get(offset, limitVal, true));
		} catch (Exception e) {
			return ResponseUtil.getUnknownExceptionResponse(e);
		}
	}

	@Override
	public Response getBirdsId(String id, SecurityContext securityContext) {
		try {
			Bird bird = DBStore.getInstance().get(id);
			if (bird == null) {
				throw new BirdException(Status.NOT_FOUND, ErrorCode.NOT_FOUND, ErrorMessage.BirdNotFound);
			}
			return ResponseUtil.getSuccessResponse(bird);
		} catch (BirdException e) {
			return ResponseUtil.getBirdExceptionResponse(e);
		} catch (Exception e) {
			return ResponseUtil.getUnknownExceptionResponse(e);
		}
	}

	@Override
	public Response postBirds(Bird body, SecurityContext securityContext) {
		try {
			if (!body.isValid()) {
				throw new BirdException(Status.BAD_REQUEST, ErrorCode.FIELD_NOT_FOUND, ErrorMessage.FieldNotFound);
			}
			Bird existBird = DBStore.getInstance().getByNameFamily(body.getName(), body.getFamily());
			if (existBird != null) {
				throw new BirdException(Status.BAD_REQUEST, ErrorCode.DUPLICATE, ErrorMessage.BirdExistAlready);
			}
			body.setAdded(DateTimeUtil.formatDate(new Date()));
			Bird res = DBStore.getInstance().add(body);
			return ResponseUtil.getSuccessResponse(Status.CREATED, res);
		} catch (BirdException e) {
			return ResponseUtil.getBirdExceptionResponse(e);
		} catch (Exception e) {
			return ResponseUtil.getUnknownExceptionResponse(e);
		}
	}
}

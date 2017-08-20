package com.saltside.bird.util;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.saltside.bird.exception.BirdException;
import com.saltside.bird.model.ApiResponseMessage;
import com.saltside.bird.model.ErrorResponse;
import com.saltside.bird.model.ResponseLink;

public class ResponseUtil {
	protected final static Logger LOG = LoggerFactory.getLogger(ResponseUtil.class);

	public static Response getBirdExceptionResponse(BirdException e) {
		ApiResponseMessage res = new ApiResponseMessage();
		ErrorResponse error = new ErrorResponse().code(e.getCode()).message(e.getMessage());
		res.error(error);
		if (e.getData() != null) {
			res.data(e.getData());
		}
		return Response.status(e.getStatus()).entity(res).build();
	}

	public static Response getUnknownExceptionResponse(Exception e) {
		e.printStackTrace();
		ErrorResponse error = new ErrorResponse().message(e.getMessage());
		ApiResponseMessage res = new ApiResponseMessage().error(error);
		return Response.serverError().entity(res).build();
	}

	public static Response getSuccessResponse(Status status, Object result) {
		ApiResponseMessage res = new ApiResponseMessage();
		res.data(result);
		return Response.status(status).entity(res).build();
	}

	public static Response getSuccessResponse(Object result) {
		ApiResponseMessage res = new ApiResponseMessage();
		res.data(result);
		return Response.ok().entity(res).build();
	}

	public static Response getSuccessResponse(Object[] result) {
		ApiResponseMessage res = new ApiResponseMessage();
		if (result != null) {
			res.data(result[0]);
			ResponseLink obj = new ResponseLink();
			obj.nextOffset((String) result[1]);
			res.links(obj);
		}
		return Response.ok().entity(res).build();
	}
}

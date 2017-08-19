package com.saltside.bird.exception;

import javax.ws.rs.core.Response.Status;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-08-19T05:47:47.983Z")
public class BirdException extends Exception {
	private static final long serialVersionUID = 1L;
	private Status status = Status.OK;
	private ErrorCode code;
	private ErrorMessage message;
	private Object data;
	private String rawMessage;

	public static enum ErrorCode {
		FIELD_NOT_FOUND, NOT_FOUND

	}

	public static enum ErrorMessage {
		FieldNotFound, BirdNotFound
	}

	public BirdException(Status status, ErrorCode code, ErrorMessage message) {
		this(status, code, message, null);
	}

	public BirdException(Status status, ErrorCode code, ErrorMessage message, Object data) {
		this.status = status;
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public BirdException(Status status, ErrorCode code, String rawMessage) {
		this.status = status;
		this.code = code;
		this.rawMessage = rawMessage;
	}

	public String getMessage() {
		if (message != null) {
			switch (message) {
			case FieldNotFound:
				return "name, family, continents are required fields";
			case BirdNotFound:
				return "Bird id not found";
			default:
				return super.getMessage();
			}
		} else {
			return rawMessage;
		}
	}

	public String getCode() {
		return code.name();
	}

	public Status getStatus() {
		return status;
	}

	public Object getData() {
		return data;
	}
}

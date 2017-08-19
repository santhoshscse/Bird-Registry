package com.saltside.bird.model;

public class ApiResponseMessage {

	ErrorResponse error;
	Object data;
	ResponseLink links;

	public ApiResponseMessage() {
	}

	public ApiResponseMessage(Object data) {
		this(data, null);
	}

	public ApiResponseMessage(Object data, ErrorResponse error) {
		this(data, error, null);
	}

	public ApiResponseMessage(Object data, ErrorResponse error, ResponseLink links) {
		this.data = data;
		this.error = error;
		this.links = links;
	}

	public Object getError() {
		return error;
	}

	public ApiResponseMessage error(ErrorResponse error) {
		this.error = error;
		return this;
	}

	public Object getData() {
		return data;
	}

	public ApiResponseMessage data(Object data) {
		this.data = data;
		return this;

	}

	public Object getLinks() {
		return links;
	}

	public ApiResponseMessage links(ResponseLink links) {
		this.links = links;
		return this;

	}

}

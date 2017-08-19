package com.saltside.bird.model;

public class ApiResponseMessage {

	Object error;
	Object data;
	Object links;

	public ApiResponseMessage() {
	}

	public ApiResponseMessage(Object data) {
		this(data, null);
	}

	public ApiResponseMessage(Object data, Object error) {
		this(data, error, null);
	}

	public ApiResponseMessage(Object data, Object error, Object links) {
		this.data = data;
		this.error = error;
		this.links = links;
	}

	public Object getError() {
		return error;
	}

	public ApiResponseMessage error(Object error) {
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

	public ApiResponseMessage links(Object links) {
		this.links = links;
		return this;

	}

}

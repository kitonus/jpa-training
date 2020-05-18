package com.jatis.training.trainingjpa.dto;

public class ErrorMessage {
	private String name;
	private String message;
	public ErrorMessage(Throwable t) {
		this.name = t.getClass().getName();
		this.message = t.getMessage();
	}
	public String getName() {
		return name;
	}
	public String getMessage() {
		return message;
	}
	
	
}

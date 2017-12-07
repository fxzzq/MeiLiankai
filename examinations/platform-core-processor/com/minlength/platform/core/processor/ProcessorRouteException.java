package com.minlength.platform.core.processor;

public class ProcessorRouteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ProcessorRouteException(String message){
		super(message);
	}
	
	public ProcessorRouteException(Throwable t){
		super(t);
	}
	
}

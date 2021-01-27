package com.exception.handling.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such order")
public class OrderNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5948623140422213911L;

	public OrderNotFoundException(String orderId) {
		super(orderId + " not found");
	}
}

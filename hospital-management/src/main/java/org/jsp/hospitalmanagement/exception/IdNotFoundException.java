package org.jsp.hospitalmanagement.exception;

public class IdNotFoundException extends RuntimeException{
	public String getMessage() {
		return "Invalid Id";
	}
}

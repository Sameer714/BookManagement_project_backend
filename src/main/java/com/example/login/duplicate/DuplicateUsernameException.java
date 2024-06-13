package com.example.login.duplicate;

public class DuplicateUsernameException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorCode;

    public DuplicateUsernameException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}

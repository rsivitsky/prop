package com.sivitsky.ruslan.exception;

/**
 * @author Paul Jakimov
 *         Date: 3/4/14
 *         Time: 9:32 PM
 */
public class GeneralServiceException extends RuntimeException {

    public GeneralServiceException() {
    }

    public GeneralServiceException(String message) {
        super(message);
    }

    public GeneralServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public GeneralServiceException(Throwable cause) {
        super(cause);
    }
}

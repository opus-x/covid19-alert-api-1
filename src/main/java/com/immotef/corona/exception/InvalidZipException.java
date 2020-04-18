package com.immotef.corona.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Uploaded zip is invalid")
public class InvalidZipException extends Exception {
}

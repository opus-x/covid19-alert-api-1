package com.immotef.corona.security.app_login;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.UNAUTHORIZED, reason="Not authorized")
public class NotAuthenticatedException extends Exception {
}

package com.immotef.corona.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Resource you are trying to reach does not exist")
public class ElementNotFoundException extends Exception {
}

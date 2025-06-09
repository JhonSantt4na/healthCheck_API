package com.santt4na.health_check.exception.handler;

import java.util.Date;

public record ExceptionResponse(Date timestamp, String message, String details) {
}

package com.protogest.service.exceptions;

public class NoLocalProtocolSchemaFound extends RuntimeException {
    public NoLocalProtocolSchemaFound() {
        super("No schema template found.");
    }
}

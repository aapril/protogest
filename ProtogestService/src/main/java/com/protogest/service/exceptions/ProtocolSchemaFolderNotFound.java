package com.protogest.service.exceptions;

public class ProtocolSchemaFolderNotFound extends RuntimeException {
    public ProtocolSchemaFolderNotFound() {
        super("Protocol Schema Folder Not Found");
    }
}

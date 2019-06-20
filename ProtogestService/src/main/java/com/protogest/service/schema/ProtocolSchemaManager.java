package com.protogest.service.schema;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.google.gson.Gson;
import com.protogest.service.database.models.ProtocolSchema;
import com.protogest.service.exceptions.NoLocalProtocolSchemaFound;
import com.protogest.service.exceptions.ProtocolSchemaFolderNotFound;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class ProtocolSchemaManager {
    private static final String LOCAL_SCHEMA_FOLDER_NAME = "protocol-schemas";

    private final List<ProtocolSchema> schemaTemplates = new ArrayList<>();

    public ProtocolSchemaManager(DynamoDBMapper mapper) {
        loadSchemaTemplates();
        validateDBSchemas(mapper);
    }

    private void loadSchemaTemplates() {
        try {
            final File schemaDir = new ClassPathResource(LOCAL_SCHEMA_FOLDER_NAME).getFile();

            // Make sure the schema folder exists
            if (!schemaDir.exists()) {
                throw new ProtocolSchemaFolderNotFound();
            }

            final File[] files = Objects.requireNonNull(schemaDir.listFiles());
            for (File file : files) {
                final String content = new String(Files.readAllBytes(file.toPath()));
                final ProtocolSchema protocol = new Gson().fromJson(content, ProtocolSchema.class);
                protocol.setName(file.getName());
                this.schemaTemplates.add(protocol);
            }

            // If the folder is empty and no schema has been found, throw an exception
            if (this.schemaTemplates.isEmpty()) {
                throw new NoLocalProtocolSchemaFound();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validateDBSchemas(DynamoDBMapper mapper) {
        if (this.schemaTemplates.isEmpty()) {
            throw new NoLocalProtocolSchemaFound();
        }

        final List<ProtocolSchema> schemas = mapper.scan(ProtocolSchema.class, new DynamoDBScanExpression());
        if (schemas.size() != this.schemaTemplates.size()) {
            // 1. Delete all the schemas in the database, since it doesn't match the local.
            // 2. Save the schemas in the database that reflects the local schemas.
            mapper.batchWrite(this.schemaTemplates, schemas);
        }
    }
}
package org.yaro.validator;

import com.networknt.schema.Error;
import com.networknt.schema.InputFormat;
import com.networknt.schema.Schema;
import com.networknt.schema.SchemaLocation;
import com.networknt.schema.SchemaRegistry;
import com.networknt.schema.SchemaRegistryConfig;
import com.networknt.schema.SpecificationVersion;
import com.networknt.schema.regex.JoniRegularExpressionFactory;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/** Standards-backed JSON Schema validation entry point. */
public final class SchemaValidate {
    private SchemaValidate() {}

    public static void main(String[] args) {
        if (args.length != 2 && args.length != 3) {
            System.err.println("Usage: SchemaValidate <schema.json> <instance.json> [--check-schema]");
            System.exit(2);
        }
        try {
            String schemaText = read(Path.of(args[0]));
            String instanceText = read(Path.of(args[1]));
            SchemaRegistryConfig config = SchemaRegistryConfig.builder()
                .regularExpressionFactory(JoniRegularExpressionFactory.getInstance())
                .build();
            SchemaRegistry registry = SchemaRegistry.withDefaultDialect(
                SpecificationVersion.DRAFT_2020_12,
                builder -> builder.schemaRegistryConfig(config)
            );

            if (args.length == 3) {
                if (!"--check-schema".equals(args[2])) {
                    throw new IllegalArgumentException("Unknown option: " + args[2]);
                }
                Schema metaSchema = registry.getSchema(
                    SchemaLocation.of("https://json-schema.org/draft/2020-12/schema")
                );
                List<Error> schemaErrors = metaSchema.validate(schemaText, InputFormat.JSON);
                if (!schemaErrors.isEmpty()) {
                    printErrors("SCHEMA_INVALID", schemaErrors);
                    System.exit(1);
                }
            }

            Schema schema = registry.getSchema(schemaText, InputFormat.JSON);
            List<Error> errors = schema.validate(instanceText, InputFormat.JSON,
                executionContext -> executionContext.executionConfig(
                    execution -> execution.formatAssertionsEnabled(true)
                )
            );
            if (errors.isEmpty()) {
                System.out.println("SCHEMA_VALID");
                System.exit(0);
            }
            printErrors("INSTANCE_INVALID", errors);
            System.exit(1);
        } catch (Exception ex) {
            System.err.println("VALIDATOR_ERROR: " + ex.getClass().getSimpleName() + ": " + ex.getMessage());
            System.exit(2);
        }
    }

    private static String read(Path path) throws Exception {
        return new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
    }

    private static void printErrors(String label, List<Error> errors) {
        System.out.println(label);
        for (Error error : errors) System.out.println(error);
    }
}

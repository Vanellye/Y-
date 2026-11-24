package org.yaro.validator;

import com.networknt.schema.Error;
import com.networknt.schema.InputFormat;
import com.networknt.schema.Schema;
import com.networknt.schema.SchemaLocation;
import com.networknt.schema.SchemaRegistry;
import com.networknt.schema.SchemaRegistryConfig;
import com.networknt.schema.SpecificationVersion;
import com.networknt.schema.regex.JoniRegularExpressionFactory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SchemaValidateTest {
    private final SchemaRegistry registry = SchemaRegistry.withDefaultDialect(
        SpecificationVersion.DRAFT_2020_12,
        builder -> builder.schemaRegistryConfig(SchemaRegistryConfig.builder()
            .regularExpressionFactory(JoniRegularExpressionFactory.getInstance()).build())
    );

    private List<Error> validate(String schemaText, String instanceText) {
        Schema schema = registry.getSchema(schemaText, InputFormat.JSON);
        return schema.validate(instanceText, InputFormat.JSON,
            context -> context.executionConfig(config -> config.formatAssertionsEnabled(true))
        );
    }

    @Test void supportsCombinatorsAndConditionalSchemas() {
        String schema = "{\"$schema\":\"https://json-schema.org/draft/2020-12/schema\"," +
            "\"if\":{\"properties\":{\"kind\":{\"const\":\"number\"}}}," +
            "\"then\":{\"required\":[\"value\"],\"properties\":{\"value\":{\"type\":\"number\"}}}," +
            "\"else\":{\"properties\":{\"value\":{\"type\":\"string\"}}}," +
            "\"properties\":{\"kind\":{\"type\":\"string\"}}}";
        assertTrue(validate(schema, "{\"kind\":\"number\",\"value\":4}").isEmpty());
        assertFalse(validate(schema, "{\"kind\":\"number\",\"value\":\"four\"}").isEmpty());
    }

    @Test void supportsReferencesAndUnevaluatedProperties() {
        String schema = "{\"$schema\":\"https://json-schema.org/draft/2020-12/schema\"," +
            "\"$defs\":{\"base\":{\"type\":\"object\",\"properties\":{\"id\":{\"type\":\"integer\"}}}}," +
            "\"allOf\":[{\"$ref\":\"#/$defs/base\"}],\"unevaluatedProperties\":false}";
        assertTrue(validate(schema, "{\"id\":7}").isEmpty());
        assertFalse(validate(schema, "{\"id\":7,\"extra\":true}").isEmpty());
    }

    @Test void assertsFormatsWhenConfigured() {
        String schema = "{\"$schema\":\"https://json-schema.org/draft/2020-12/schema\"," +
            "\"type\":\"string\",\"format\":\"date-time\"}";
        assertTrue(validate(schema, "\"2026-11-24T08:00:00Z\"").isEmpty());
        assertFalse(validate(schema, "\"not-a-date\"").isEmpty());
    }

    @Test void validatesSchemaAgainstItsMetaSchema() {
        Schema meta = registry.getSchema(
            SchemaLocation.of("https://json-schema.org/draft/2020-12/schema")
        );
        assertTrue(meta.validate("{\"type\":\"string\"}", InputFormat.JSON).isEmpty());
        assertFalse(meta.validate("{\"type\":\"not-a-json-schema-type\"}", InputFormat.JSON).isEmpty());
    }
}

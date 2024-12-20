package dev.rohit.productservice.integrations.elasticsearch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ElasticSearchHelper {

    private static final ObjectMapper MAPPER = createObjectMapper();

    public static <T> Optional<T> mapForES(Class<T> type, T input) {
        return mapObject(type, input);
    }

    public static <T> List<T> mapListForES(Class<T> type, List<T> input) {
        return mapObjectList(type, input);
    }

    private static <T> Optional<T> mapObject(Class<T> type, T input) {
        try {
            return Optional.ofNullable(MAPPER.readValue(MAPPER.writeValueAsString(input), type));
        } catch (JsonProcessingException e) {
            return Optional.empty();
        }
    }

    private static <T> List<T> mapObjectList(Class<T> type, List<T> input) {
        try {
            JavaType javaType = MAPPER.getTypeFactory().constructCollectionType(List.class, type);
            String serialText = MAPPER.writeValueAsString(input);
            return MAPPER.readValue(serialText, javaType);
        } catch (JsonProcessingException e) {
            return new ArrayList<>();
        }
    }

    @NotNull
    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(SerializationFeature.WRITE_SELF_REFERENCES_AS_NULL, true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Hibernate5Module module = new Hibernate5Module();
        module.disable(Hibernate5Module.Feature.FORCE_LAZY_LOADING);
        module.enable(Hibernate5Module.Feature.SERIALIZE_IDENTIFIER_FOR_LAZY_NOT_LOADED_OBJECTS);
        module.enable(Hibernate5Module.Feature.USE_TRANSIENT_ANNOTATION);
        module.enable(Hibernate5Module.Feature.REPLACE_PERSISTENT_COLLECTIONS);
        mapper.registerModule(module);
        return mapper;
    }
}

package com.base.java.util.json;

import com.base.java.util.json.deserializers.LocalDateDeserialzer;
import com.base.java.util.json.deserializers.LocalDateTimeDerializer;
import com.base.java.util.json.deserializers.LocalTimeDeserializer;
import com.base.java.util.json.serializers.BigDecimalSerizlier;
import com.base.java.util.json.serializers.LocalDateTimeSerializer;
import com.base.java.util.json.serializers.LocalTimeSerializer;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.Getter;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class JsonHelper {
    private static final SimpleModule module = initModule();

    @Getter
    private final static ObjectMapper mapper = new ObjectMapper().registerModule(module).configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
    @Getter
    private final static ObjectMapper prettyMapper = mapper.copy().configure(SerializationFeature.INDENT_OUTPUT, true);

    private static SimpleModule initModule() {
        return new SimpleModule()
                .addSerializer(BigDecimal.class, new BigDecimalSerizlier()).//
                addSerializer(LocalTime.class, new LocalTimeSerializer()).//
                addDeserializer(LocalTime.class, new LocalTimeDeserializer()).//
                addDeserializer(LocalDate.class, new LocalDateDeserialzer()).//
                addSerializer(LocalDateTime.class, new LocalDateTimeSerializer()).//
                addDeserializer(LocalDateTime.class, new LocalDateTimeDerializer());
    }

    public static JavaType genJavaType(Type type) {
        return getMapper().getTypeFactory().constructType(type);
    }
}

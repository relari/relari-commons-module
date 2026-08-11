package pe.com.relari.commons.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import java.io.InputStream;
import lombok.extern.slf4j.Slf4j;

/**
 * <b>Class:</b> JsonUtil.<br>
 *
 * @author Relari.
 */

@Slf4j
public class JsonUtil {

  private JsonUtil() {}

  private static final ObjectMapper MAPPER = new ObjectMapper()
      .findAndRegisterModules()
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
      .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
      .setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);

  public static <T> T readJsonFromResource(String resourcePath, Class<T> type) throws IOException {
    try (InputStream is = JsonUtil.class.getClassLoader().getResourceAsStream(resourcePath)) {
      if (is == null) {
        throw new IOException("Recurso no encontrado en la ruta: " + resourcePath);
      }
      return MAPPER.readValue(is, type);
    }
  }

  public static <T> T readJson(String json, Class<T> type) throws JsonProcessingException {
    if (json == null || json.isEmpty()) {
      return null;
    }
    return MAPPER.readValue(json, type);
  }

  public static <T> T readJsonSafe(String json, Class<T> type) {
    try {
      return readJson(json, type);
    } catch (JsonProcessingException e) {
      log.error("Error al deserializar JSON: {}", e.getMessage());
      return null;
    }
  }

  public static String toJson(Object object) throws JsonProcessingException {
    if (object == null) {
      return null;
    }
    return MAPPER.writeValueAsString(object);
  }

  public static String toJsonSafe(Object object) {
    try {
      return toJson(object);
    } catch (JsonProcessingException e) {
      log.error("Error al convertir objeto a JSON: {}", e.getMessage());
      return null;
    }
  }
}

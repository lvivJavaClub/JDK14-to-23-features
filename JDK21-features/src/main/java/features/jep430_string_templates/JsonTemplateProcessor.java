package features.jep430_string_templates;

import org.json.JSONObject;

import java.time.LocalDate;

public class JsonTemplateProcessor {

  public static StringTemplate.Processor<JSONObject, RuntimeException> JSON =
      stringTemplate -> {
        if(stringTemplate.values().stream().anyMatch(v -> v instanceof LocalDate)){
          throw new RuntimeException("Ooops...");
        }
        return new JSONObject(stringTemplate.interpolate());
      };
}

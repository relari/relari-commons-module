package pe.com.relari.commons.model.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * <b>Class:</b> ErrorResponse.<br>
 *
 * @author Relari.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

  private String code;
  private Integer status;
  private String description;
  private String timestamp;
  private Map<String, String> metadata;
  private Throwable throwable;
  private Object details;

}

package pe.com.relari.error.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import pe.com.relari.error.category.ErrorCategory;
import pe.com.relari.error.model.ErrorDetail;
import pe.com.relari.support.generic.GenericMap;

/**
 * <b>Class:</b> ApplicationProperties.</br>
 * @author Renzo Lavado Rivas.
 * @version 1.0.0
 */

@Data
@Lazy
@Configuration
@ConfigurationProperties(prefix = "application.errors")
public class ErrorProperties {

    private GenericMap<ErrorCategory, ErrorDetail> categories;

}

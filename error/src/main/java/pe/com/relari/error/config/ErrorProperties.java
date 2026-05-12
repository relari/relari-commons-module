package pe.com.relari.error.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import pe.com.relari.error.category.ErrorCatalog;
import pe.com.relari.error.model.ErrorCategory;
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

    private String code;
    private GenericMap<String, ErrorCategory> categories;

    public ErrorCategory getCategory(ErrorCatalog category) {
        if (categories == null) {
            return null;
        }
        return categories.get(category.name());
    }

    public String buildErrorCode(String code) {
        return this.code.concat(code);
    }

}

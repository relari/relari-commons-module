package pe.com.relari.library.error.config;

import java.util.Map;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import pe.com.relari.library.error.category.ErrorCatalog;
import pe.com.relari.library.error.model.ErrorCategory;

/**
 * <b>Class:</b> ApplicationProperties.<br>
 *
 * @author Relari.
 */

@Data
@Lazy
@Configuration
@ConfigurationProperties(prefix = "application.errors")
public class ErrorProperties {

    private String code;
    private Map<String, ErrorCategory> categories;

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

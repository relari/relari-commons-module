package pe.com.relari.error.exception;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import pe.com.relari.error.category.ErrorCatalog;

/**
 * Class: ApiException.
 *
 * @author Relari
 */

@Getter
public class ApiException extends RuntimeException {

    @NotNull(message = "Catalog no puede ser nulo")
    private final ErrorCatalog catalog;

    public ApiException(
            ErrorCatalog catalog) {
        super();
        this.catalog = catalog;
    }

    public ApiException(
            ErrorCatalog catalog,
            String message) {
        super(message);
        this.catalog = catalog;
    }

    public ApiException(
            ErrorCatalog catalog,
            Throwable throwable) {
        super(throwable);
        this.catalog = catalog;
    }

    public ApiException(
            String message,
            Throwable throwable) {
        super(message, throwable);
        this.catalog = null;
    }
}

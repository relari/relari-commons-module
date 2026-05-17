package pe.com.relari.library.support.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiHeaders {

    public static final String HEADER_REQUEST_ID = "Request-Id";
    public static final String HEADER_USER_ID = "user-id";
    public static final String HEADER_SESSION_ID = "session-id";
    public static final String HEADER_X_FORWARDED_FOR = "x-forwarded-for";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String HEADER_APP_CODE = "app-code";

    private String requestId;
    private String userId;
    private String sessionId;
    private String xForwardedFor;
    private String authorization;
    private String appCode;



}

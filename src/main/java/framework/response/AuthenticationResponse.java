package framework.response;

import lombok.Data;

@Data
public class AuthenticationResponse {
    private String token;
    private String reason;
}

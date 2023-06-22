package framework;

import framework.request.Authentication;
import framework.response.AuthenticationResponse;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateAuthenticationTests {

    @Test
    public void postAuthenticationWithIncorrectCredentialsReturnsMessage() {
        Authentication credentials = Authentication.getCredentials();

        credentials.setUsername("invalidUsername");
        credentials.setPassword("invalidPassword045");

        AuthenticationResponse authenticationResponse = AuthenticationApi
                .createToken(credentials)
                .as(AuthenticationResponse.class);

        assertThat(authenticationResponse.getReason()).isEqualTo("Bad credentials");
    }

    @Test
    public void postAuthenticationWithCorrectCredentialsReturnsToken() {
        Authentication credentials = Authentication.getCredentials();

        AuthenticationResponse authenticationResponse = AuthenticationApi
                .createToken(credentials)
                .as(AuthenticationResponse.class);

        assertThat(authenticationResponse.getToken()).isNotEmpty();
    }
}
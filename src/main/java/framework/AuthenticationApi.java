package framework;

import framework.request.Authentication;
import framework.request.Booking;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthenticationApi {
    private static final String BASE_URL = "https://restful-booker.herokuapp.com";
    private static final String AUTH_API = BASE_URL + "/auth";

    public static Response createToken(Authentication credentials) {
        return given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(credentials)
                .when()
                .post(AUTH_API);
    }
}

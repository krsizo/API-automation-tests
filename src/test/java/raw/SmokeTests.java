package raw;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.http.ContentType.JSON;

public class SmokeTests {

    public static final String API_URL = "https://restful-booker.herokuapp.com/booking";

    @Test
    public void getAllBookingsShouldReturnHttp200() {
        when()
                .get(API_URL)
                .then()
                .statusCode(200);
    }

    @Test
    void getBookingByIdShouldReturnHttp200() {
        given()
                .contentType(JSON.toString())
                .when()
                .get(API_URL + "/7")
                .then()
                .statusCode(200);
    }

    @Test
    void postBookingShouldReturnHttp200() {
        String payload = """
                {
                    "firstname": "Sally",
                    "lastname": "Brown",
                    "totalprice": 111,
                    "depositpaid": true,
                    "bookingdates": {
                        "checkin": "2013-02-23",
                        "checkout": "2014-10-23"
                    },
                    "additionalneeds": "Breakfast"
                }
                """;
        given()
                .accept(JSON.toString())
                .contentType(JSON.toString())
                .body(payload)
                .when()
                .post(API_URL)
                .then()
                .statusCode(200);
    }

}

package raw;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CreateBookingTests {

    public static final String API_URL = "https://restful-booker.herokuapp.com/booking";

    @Test
    void postBookingShouldReturnBookingId(){
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
                .contentType(JSON.toString())
                .accept(JSON.toString())
                .body(payload)
                .when()
                .post(API_URL)
                .then()
                .statusCode(200)
                .body("bookingid", notNullValue());
    }

    @Test
    void postBookingShouldContainBooking(){
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
                .contentType(JSON.toString())
                .accept(JSON.toString())
                .body(payload)
                .when()
                .post(API_URL)
                .then()
                .body("booking", notNullValue());
    }

    @Test
    void postBookingShouldReturnCorrectBookingPerson(){
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
                .contentType(JSON.toString())
                .accept(JSON.toString())
                .body(payload)
                .when()
                .post(API_URL)
                .then()
                .body("booking.firstname", equalTo("Sally"))
                .body("booking.lastname", equalTo("Brown"));
    }
}

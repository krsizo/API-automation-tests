package framework;

import framework.request.Booking;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.Validatable;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class BookingApi {

    private static final String BASE_URL = "https://restful-booker.herokuapp.com";
    private static final String BOOKING_API = BASE_URL + "/booking/";

    public static Response getBookings() {
        return given().get(BOOKING_API);
    }

    public static Response getBookingById(int id) {
        return getBookingById(id, JSON);
    }

    public static Response getBookingById(int bookingId, ContentType contentType) {
        return given()
                .accept(contentType.toString())
                .when()
                .get(BOOKING_API + bookingId);
    }

    public static Response postBooking(Booking bookingPayload) {
        return postBooking(bookingPayload, JSON);
    }

    public static Response postBooking(Booking bookingPayload, ContentType contentType) {
        return given()
                .accept(contentType.toString())
                .contentType(JSON.toString())
                .body(bookingPayload)
                .when()
                .post(BOOKING_API);
    }

    public static Response putBooking(Booking bookingPayload, String token, int id) {
        return putBooking(bookingPayload, JSON, token, id);
    }

    public static Response putBooking(Booking bookingPayload, ContentType contentType, String token, int id) {
        return given()
                .accept(contentType.toString())
                .contentType(JSON.toString())
                .header("Cookie", "token=" + token)
                .body(bookingPayload)
                .when()
                .put(BOOKING_API + id);
    }

    public static Response deleteBooking(String token, int id) {
        return given()
                .contentType(JSON.toString())
                .header("Cookie", "token=" + token)
                .when()
                .delete(BOOKING_API + id);
    }
}
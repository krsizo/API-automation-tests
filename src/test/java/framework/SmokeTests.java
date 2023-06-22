package framework;

import framework.request.Authentication;
import framework.request.Booking;
import framework.response.AuthenticationResponse;
import framework.response.BookingResponse;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

public class SmokeTests {

    @Test
    public void getBookingsShouldReturn200() {
        BookingApi.getBookings().then().statusCode(200);
    }

    @Test
    public void getBookingByIdShouldReturn200() {
        BookingApi.getBookingById(11).then().statusCode(200);
    }

    @Test
    public void postBookingsShouldReturn200() {
        Booking bookingPayload = Booking.getFullPayload();

        BookingApi.postBooking(bookingPayload).then().statusCode(200);
    }

    // Authentication
    @Test
    public void postAuthenticationWithCorrectCredentialsShouldReturn200() {
        Authentication credentials = Authentication.getCredentials();

        AuthenticationApi.createToken(credentials).then().statusCode(200);
    }

    @Test
    public void postBookingWithWrongAcceptHeaderReturns418() {
        Booking bookingPayload = Booking.getFullPayload();

        BookingApi.postBooking(bookingPayload, ContentType.BINARY).then().statusCode(418);
    }

    @Test
    public void putBookingShouldReturn200() {
        Booking bookingPayload = Booking.getFullPayload();

        Authentication credentials = Authentication.getCredentials();

        AuthenticationResponse authenticationResponse = AuthenticationApi
                .createToken(credentials)
                .as(AuthenticationResponse.class);

        String token = authenticationResponse.getToken();

        BookingApi.putBooking(bookingPayload, token, 12).then().statusCode(200);
    }

    @Test
    public void deleteBookingShouldReturn201() {
        Authentication credentials = Authentication.getCredentials();

        AuthenticationResponse authenticationResponse = AuthenticationApi
                .createToken(credentials)
                .as(AuthenticationResponse.class);

        String token = authenticationResponse.getToken();

        BookingApi.deleteBooking(token, 12).then().statusCode(201);
    }
}

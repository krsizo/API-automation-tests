package framework;

import framework.request.Booking;
import framework.response.BookingResponse;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateBookingTests {

    @Test
    public void postBookingShouldReturnBookingId() {
        Booking bookingPayload = Booking.getFullPayload();

        BookingResponse bookingResponse = BookingApi
                .postBooking(bookingPayload)
                .as(BookingResponse.class);

        assertThat(bookingResponse.getBookingid()).isNotNull();
    }

    @Test
    public void postBookingShouldReturnCorrectBookingPerson() {
        Booking bookingPayload = Booking.getFullPayload();
        bookingPayload.setFirstname("Kristina");
        bookingPayload.setLastname("Sizova");

        BookingResponse bookingResponse = BookingApi
                .postBooking(bookingPayload)
                .as(BookingResponse.class);

        assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo("Kristina");
        assertThat(bookingResponse.getBooking().getLastname()).isEqualTo("Sizova");
    }

    @Test
    public void createBookingFromFile() throws IOException {
        Booking bookingPayload = Booking.buildWithResource("test_data/test.json");

        BookingResponse bookingResponse = BookingApi
                .postBooking(bookingPayload)
                .as(BookingResponse.class);

        assertThat(bookingResponse.getBooking())
                .extracting("firstname", "lastname")
                .contains("Kristina", "Sizova");
    }
}
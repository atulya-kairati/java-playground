package service.bookingservice;

import models.booking.Booking;
import models.car.Car;
import models.user.User;

import java.util.Optional;

public interface BookingService {

    void createBooking(Car car, User user);

    Optional<Booking> getBookingById(String id);

    Booking[] getAllBookings();

    Car[] getAllCarBookedByUser(String userId);
}

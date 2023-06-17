package service.carbookingservice;

import models.booking.Booking;
import models.car.Car;
import models.user.User;

import java.util.Optional;

public interface CarBookingService {
    void createBooking(Car car, User user);

    Optional<Booking> getBookingById(String id);

    Booking[] getAllBookings();

    Car[] getAllCarBookedByUser(String userId);

    Optional<Car> getCarById(String id);

    Car[] getAllCars();

    Car[] getAllElectricCars();

    Optional<User> getUserById(String id);

    User[] getAllUsers();
}

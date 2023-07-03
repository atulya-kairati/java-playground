package service.carbookingservice;

import models.booking.Booking;
import models.car.Car;
import models.user.User;

import java.util.List;
import java.util.Optional;

public interface CarBookingService {
    void createBooking(Car car, User user);

    Optional<Booking> getBookingById(String id);

    List<Booking> getAllBookings();

    List<Car> getAllCarBookedByUser(String userId);

    Optional<Car> getCarById(String id);

    List<Car> getAllCars();

    List<Car> getAllElectricCars();

    Optional<User> getUserById(String id);

    List<User> getAllUsers();
}

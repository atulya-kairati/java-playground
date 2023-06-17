package service.carbookingservice;

import dao.bookingdao.BookingDAO;
import dao.cardao.CarDAO;
import dao.userdao.UserDAO;
import models.booking.Booking;
import models.car.Car;
import models.user.User;

import java.util.Optional;

public class CarBookingServiceImpl implements CarBookingService{

    UserDAO userDAO;
    CarDAO carDAO;
    BookingDAO bookingDAO;

    public CarBookingServiceImpl(UserDAO userDAO, CarDAO carDAO, BookingDAO bookingDAO) {
        this.userDAO = userDAO;
        this.carDAO = carDAO;
        this.bookingDAO = bookingDAO;
    }

    @Override
    public void createBooking(Car car, User user) {
        bookingDAO.createBooking(car, user);
    }

    @Override
    public Optional<Booking> getBookingById(String id) {
        return bookingDAO.getBookingById(id);
    }

    @Override
    public Booking[] getAllBookings() {
        return bookingDAO.getAllBookings();
    }

    @Override
    public Car[] getAllCarBookedByUser(String userId) {
        return bookingDAO.getAllCarBookedByUser(userId);
    }

    @Override
    public Optional<Car> getCarById(String id) {
        return carDAO.getCarById(id);
    }

    @Override
    public Car[] getAllCars() {
        return carDAO.getAllCars();
    }

    @Override
    public Car[] getAllElectricCars() {
        return carDAO.getAllElectricCars();
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userDAO.getUserById(id);
    }

    @Override
    public User[] getAllUsers() {
        return userDAO.getAllUsers();
    }
}

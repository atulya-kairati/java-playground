package service.bookingservice;

import dao.bookingdao.BookingDAO;
import models.booking.Booking;
import models.car.Car;
import models.user.User;

import java.util.Optional;

public class BookingServiceImpl implements BookingService{

    BookingDAO bookingDAO;

    public BookingServiceImpl(BookingDAO bookingDAO) {
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
}

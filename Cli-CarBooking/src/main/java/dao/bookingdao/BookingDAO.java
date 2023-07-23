package dao.bookingdao;

import models.booking.Booking;
import models.car.Car;
import models.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class BookingDAO {
    List<Booking> bookings = new ArrayList<>();

    public void createBooking(Car car, User user){
        Booking booking = new Booking(car, user);
        bookings.add(booking);
    }

    public Optional<Booking> getBookingById(String id){
        UUID uuid = UUID.fromString(id);
        for (Booking booking: bookings){
            if(booking.getRefNo().equals(uuid)) return Optional.of(booking);
        }
        return Optional.empty();
    }

    public List<Booking> getAllBookings(){
        return bookings;
    }

    public List<Car> getAllCarBookedByUser(String userId){
        UUID userUuid = UUID.fromString(userId);

        return bookings.stream()
                .filter(booking -> booking.getUser().getId().equals(userUuid))
                .map(Booking::getCar)
                .collect(Collectors.toList());

    }
}

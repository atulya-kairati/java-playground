package dao.bookingdao;

import models.booking.Booking;
import models.car.Car;
import models.user.User;

import java.util.Optional;
import java.util.UUID;

public class BookingDAO {
    private final int CAPACITY = 100;
    private final Booking[] bookings = new Booking[CAPACITY];
    private int currentSlot = 0;

    public void createBooking(Car car, User user){
        Booking booking = new Booking(car, user);
        bookings[currentSlot++] = booking;
    }

    public Optional<Booking> getBookingById(String id){
        UUID uuid = UUID.fromString(id);
        for (Booking booking: bookings){
            if(booking == null) break;

            if(booking.getRefNo().equals(uuid)) return Optional.of(booking);
        }
        return Optional.empty();
    }

    public Booking[] getAllBookings(){
        Booking[] currentBookings = new Booking[currentSlot];

        System.arraycopy(
                bookings,
                0,
                currentBookings,
                0,
                currentSlot
        );

        return currentBookings;
    }

    public Car[] getAllCarBookedByUser(String userId){
        UUID userUuid = UUID.fromString(userId);

        Car[] userCars = new Car[bookings.length];
        int ptr = 0;

        for (Booking booking: bookings){

            if (booking == null) continue;

            if(booking.getUser().getId().equals(userUuid)){
                userCars[ptr++]  = booking.getCar();
            }
        }

        return userCars;
    }
}

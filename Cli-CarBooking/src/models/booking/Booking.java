package models.booking;

import models.car.Car;
import models.user.User;

import java.util.Objects;
import java.util.UUID;

public class Booking {
    private final UUID refNo;
    private final Car car;
    private final User user;


    public Booking(Car car, User user) {
        this.refNo = UUID.randomUUID();
        this.car = car;
        this.user = user;
    }

    public UUID getRefNo() {
        return refNo;
    }

    public Car getCar() {
        return car;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "refNo=" + refNo +
                ", car=" + car +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(refNo, booking.refNo) && Objects.equals(car, booking.car) && Objects.equals(user, booking.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(refNo, car, user);
    }
}

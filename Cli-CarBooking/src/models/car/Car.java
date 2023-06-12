package models.car;

import enums.Brand;

import java.util.Objects;
import java.util.UUID;

public class Car {
    private final UUID id;
    private final Brand brand;

    private final boolean isElectric;

    public Car(Brand brand, boolean isElectric) {
        this.id = UUID.randomUUID();
        this.brand = brand;
        this.isElectric = isElectric;
    }

    public Car(Brand brand) {
        this(brand, false);
    }

    public UUID getId() {
        return id;
    }

    public Brand getBrand() {
        return brand;
    }

    public boolean isElectric() {
        return isElectric;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand=" + brand +
                ", isElectric=" + isElectric +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return isElectric == car.isElectric && Objects.equals(id, car.id) && brand == car.brand;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, isElectric);
    }
}

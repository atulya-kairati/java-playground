import dao.bookingdao.BookingDAO;
import dao.cardao.CarDAO;
import dao.userdao.UserDAO;
import dao.userdao.UserDAOFromCSV;
import models.car.Car;
import models.user.User;
import service.carbookingservice.CarBookingService;
import service.carbookingservice.CarBookingServiceImpl;

import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner((System.in));

        UserDAO userDAO = new UserDAOFromCSV();
        CarDAO carDAO = new CarDAO();
        BookingDAO bookingDAO = new BookingDAO();

        CarBookingService carBookingService = new CarBookingServiceImpl(
                userDAO,
                carDAO,
                bookingDAO
        );

        System.out.println();

        String menuText = """
                🚗 Bhosdi Car Booking Corner (BCBC)🚗
                1️⃣ -> Book Car
                2️ -> View all cars booked by the user
                3️⃣ -> View sll bookings
                4️⃣ -> View all cars
                5️⃣ -> View all electric cars
                6️⃣ -> View all users
                7️⃣ -> Exit
                """;

        while (true) {
            System.out.println(menuText);
            System.out.print("Choose: ");
            int choice = in.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.println("Booking car, select id from available cars");
                    System.out.println(Arrays.toString(carBookingService.getAllCars()));
                    System.out.print("Selected car id: ");
                    String carId = in.next();

                    Optional<Car> carMaybe = carBookingService.getCarById(carId);
                    if (carMaybe.isEmpty()) {
                        System.out.println("Car not found");
                        break;
                    }
                    Car car = carMaybe.get();

                    System.out.println(Arrays.toString(carBookingService.getAllUsers()));
                    System.out.print("User id: ");
                    String userId = in.next();

                    Optional<User> userMaybe = carBookingService.getUserById(userId);
                    if (userMaybe.isEmpty()) {
                        System.out.println("Car not found");
                        break;
                    }
                    User user = userMaybe.get();

                    carBookingService.createBooking(car, user);
                    System.out.println(car.getBrand() + " booked for " + user.getName() + " ✅");
                }
                case 2 -> {
                    System.out.println(Arrays.toString(carBookingService.getAllUsers()));
                    System.out.print("User id: ");
                    String id = in.next();
                    System.out.println("Cars booked by " + carBookingService.getUserById(id) + " :");
                    System.out.println(Arrays.toString(carBookingService.getAllCarBookedByUser(id)));
                }
                case 3 -> {
                    System.out.println("All bookings: \n" + Arrays.toString(carBookingService.getAllBookings()));
                }
                case 4 -> {
                    System.out.println("All cars: \n" + Arrays.toString(carBookingService.getAllCars()));
                }
                case 5 -> {
                    System.out.println("All electric cars: \n" + Arrays.toString(carBookingService.getAllElectricCars()));
                }
                case 6 -> {
                    System.out.println("All Users: \n" + Arrays.toString(carBookingService.getAllUsers()));
                }
                case 7 -> {
                    System.out.println("Bye 👋");
                    System.exit(0);
                }

                default -> System.out.println("Wrong choice ❌");
            }
        }
    }
}

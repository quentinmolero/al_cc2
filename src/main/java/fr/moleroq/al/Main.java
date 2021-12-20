package fr.moleroq.al;


import fr.moleroq.al.application.PaymentService;
import fr.moleroq.al.application.UserPaymentService;
import fr.moleroq.al.application.UserService;
import fr.moleroq.al.domain.*;
import fr.moleroq.al.infrastructure.InMemoryPaymentRepository;
import fr.moleroq.al.infrastructure.InMemoryUserPaymentRepository;
import fr.moleroq.al.infrastructure.InMemoryUserRepository;

import java.time.Instant;
import java.util.Date;
import java.util.List;

public final class Main {

    public static void main(String[] args) {

        UserRepository userRepository = new InMemoryUserRepository();
        PaymentRepository paymentRepository = new InMemoryPaymentRepository();
        UserPaymentRepository userPaymentRepository = new InMemoryUserPaymentRepository();

        UserService userService = new UserService(userRepository);
        PaymentService paymentService = new PaymentService(paymentRepository);
        UserPaymentService userPaymentService = new UserPaymentService(userPaymentRepository);

        final UserId myUserId = userRepository.nextIdentity();
        final PaymentId myPaymentId = paymentRepository.nextIdentity();

        createUser(userService, myUserId);
        createPayment(paymentService, myPaymentId);
        userPaymentService.create(userRepository.byId(myUserId), paymentRepository.byId(myPaymentId));
        changePassword(userService, myUserId);
        printAllUsers(userService);
    }

    private static void createUser(UserService userService, UserId myUserId) {
        User user = User.of(myUserId, "MOLERO", "Quentin", "PASSWORD");
        userService.create(user);
    }

    private static void createPayment(PaymentService paymentService, PaymentId paymentId) {
        Payment payment = Payment.of(10_00, Date.from(Instant.now()), paymentId);
        paymentService.create(payment);
    }

    private static void changePassword(UserService userService, UserId myUserId) {
        userService.changePassword(myUserId, "P4SSW0RD");
    }

    private static void printAllUsers(UserService userService) {
        System.out.println("List all users");

        final List<User> users = userService.all();
        users.forEach(System.out::println);
    }
}

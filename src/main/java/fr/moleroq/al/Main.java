package fr.moleroq.al;


import fr.moleroq.al.application.*;
import fr.moleroq.al.domain.*;
import fr.moleroq.al.infrastructure.InMemoryPaymentRepository;
import fr.moleroq.al.infrastructure.InMemoryUserPaymentRepository;
import fr.moleroq.al.infrastructure.InMemoryUserRepository;
import fr.moleroq.al.kernel.ApplicationEvent;
import fr.moleroq.al.kernel.EventBus;
import fr.moleroq.al.kernel.SimpleEventBus;

import java.time.Instant;
import java.util.Date;
import java.util.List;

public final class Main {

    public static void main(String[] args) {

        UserRepository userRepository = new InMemoryUserRepository();
        PaymentRepository paymentRepository = new InMemoryPaymentRepository();
        UserPaymentRepository userPaymentRepository = new InMemoryUserPaymentRepository();

        PaymentService paymentService = new PaymentService(paymentRepository);
        UserPaymentService userPaymentService = new UserPaymentService(userPaymentRepository);

        EventBus<ApplicationEvent> eventBus = new SimpleEventBus<>();
        eventBus.register(UserStartSubscription.class, List.of(new UserSubscriptionListener(paymentRepository, paymentService, userPaymentService)));

        UserService userService = new UserService(userRepository, eventBus);

        final UserId myUserId = userRepository.nextIdentity();
//        final PaymentId myPaymentId = paymentRepository.nextIdentity();

        createUser(userService, myUserId);
//        createPayment(paymentService, myPaymentId);
//        userPaymentService.create(userRepository.byId(myUserId), paymentRepository.byId(myPaymentId));
        changePassword(userService, myUserId);
        printAllUsers(userService);
        printAllPayments(paymentService);
    }

    private static void createUser(UserService userService, UserId myUserId) {
        try {
            User user = User.of(myUserId, "MOLERO", "Quentin", "PASSWORD");
            userService.create(user);
        } catch (IllegalArgumentException e) {
            System.out.println(ErrorEngine.getInstance().createError(e.getMessage()));
        }
    }

    private static void createPayment(PaymentService paymentService, PaymentId paymentId) {
        try {
            Payment payment = Payment.of(10_00, Date.from(Instant.now()), paymentId);
            paymentService.create(payment);
        } catch (IllegalArgumentException e) {
            System.out.println(ErrorEngine.getInstance().createError(e.getMessage()));
        }
    }

    private static void changePassword(UserService userService, UserId myUserId) {
        userService.changePassword(myUserId, "P4SSW0RD");
    }

    private static void printAllUsers(UserService userService) {
        System.out.println("List all users");

        final List<User> users = userService.all();
        users.forEach(System.out::println);
    }

    private static void printAllPayments(PaymentService paymentService) {
        System.out.println("List all payments");

        final List<Payment> payments = paymentService.all();
        payments.forEach(System.out::println);
    }
}

package fr.moleroq.al;

import fr.moleroq.al.application.*;
import fr.moleroq.al.domain.PaymentRepository;
import fr.moleroq.al.domain.UserPaymentRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
class SpringMain {
    public static void main(String[] args) {
        final ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringMain.class, args);

        CreateUserCommandHandler userCommandHandler = applicationContext.getBean(CreateUserCommandHandler.class);
        CreateUser createUser = new CreateUser("MOLERO", "Quentin", "PASSWORD");
        try {
            userCommandHandler.handle(createUser);
        } catch (IllegalArgumentException e) {
            System.out.println(ErrorEngine.getInstance().createError(e.getMessage()));
        }

        UserPaymentRepository userPaymentRepository = applicationContext.getBean(UserPaymentRepository.class);
        PaymentRepository paymentRepository = applicationContext.getBean(PaymentRepository.class);
        PaymentService paymentService = applicationContext.getBean(PaymentService.class);

        MonthlyPaymentEngine.setupInstance(userPaymentRepository, paymentService, paymentRepository);
        MonthlyPaymentEngine.getInstance().start();
    }
}

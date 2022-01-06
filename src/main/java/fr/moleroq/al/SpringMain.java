package fr.moleroq.al;

import fr.moleroq.al.application.CreateUser;
import fr.moleroq.al.application.CreateUserCommandHandler;
import fr.moleroq.al.domain.UserId;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringMain {
    public static void main(String[] args) {
        final ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringMain.class, args);

        CreateUserCommandHandler userCommandHandler = applicationContext.getBean(CreateUserCommandHandler.class);
        CreateUser createUser = new CreateUser("MOLERO", "Quentin", "PASSWORD");
        final UserId userId = userCommandHandler.handle(createUser);
    }
}

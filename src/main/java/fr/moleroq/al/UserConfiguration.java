package fr.moleroq.al;

import fr.moleroq.al.application.*;
import fr.moleroq.al.domain.PaymentRepository;
import fr.moleroq.al.domain.UserRepository;
import fr.moleroq.al.infrastructure.DefaultEventDispatcher;
import fr.moleroq.al.infrastructure.InMemoryUserRepository;
import fr.moleroq.al.kernel.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
class UserConfiguration {

    @Bean
    public UserRepository userRepository() {
        return new InMemoryUserRepository();
    }

    @Bean
    public EventDispatcher<Event> userEventEventDispatcher(PaymentRepository paymentRepository, PaymentService paymentService, UserPaymentService userPaymentService) {
        final Map<Class<? extends Event>, List<EventListener<? extends Event>>> listenerMap = new HashMap<>();
        listenerMap.put(UserStartSubscription.class, List.of(new UserSubscriptionListener(paymentRepository, paymentService, userPaymentService)));
        return new DefaultEventDispatcher(listenerMap);
    }

    @Bean
    public CreateUserCommandHandler createUserCommandHandler(PaymentRepository paymentRepository, PaymentService paymentService, UserPaymentService userPaymentService) {
        return new CreateUserCommandHandler(userRepository(), userEventEventDispatcher(paymentRepository, paymentService, userPaymentService));
    }

    @Bean
    public CommandBus userCommandBus(PaymentRepository paymentRepository, PaymentService paymentService, UserPaymentService userPaymentService) {
        final Map<Class<? extends Command>, CommandHandler> commandHandlerMap = new HashMap<>();
        commandHandlerMap.put(CreateUser.class,
                createUserCommandHandler(paymentRepository, paymentService, userPaymentService));
        return new SimpleCommandBus(commandHandlerMap);
    }

    @Bean
    public QueryBus usersQueryBus() {
        final Map<Class<? extends Query>, QueryHandler> queryHandlerMap =
                Collections.singletonMap(RetrieveUsers.class, new RetrieveUsersHandler(userRepository()));
        return new SimpleQueryBus(queryHandlerMap);
    }

    @Bean
    public QueryBus userQueryBus() {
        final Map<Class<? extends Query>, QueryHandler> queryHandlerMap =
                Collections.singletonMap(RetrieveUser.class, new RetrieveUserHandler(userRepository()));
        return new SimpleQueryBus(queryHandlerMap);
    }
}

package fr.moleroq.al;

import fr.moleroq.al.application.*;
import fr.moleroq.al.domain.PaymentRepository;
import fr.moleroq.al.infrastructure.DefaultEventDispatcher;
import fr.moleroq.al.infrastructure.InMemoryPaymentRepository;
import fr.moleroq.al.kernel.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
class PaymentConfiguration {

    @Bean
    public PaymentRepository paymentRepository() {
        return new InMemoryPaymentRepository();
    }

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(paymentRepository());
    }

    @Bean
    public CreatePaymentCommandHandler createPaymentCommandHandler(UserPaymentService userPaymentService) {
        return new CreatePaymentCommandHandler(paymentRepository(), paymentEventEventDispatcher(userPaymentService));
    }

    @Bean
    public EventDispatcher<Event> paymentEventEventDispatcher(UserPaymentService userPaymentService) {
        final Map<Class<? extends Event>, List<EventListener<? extends Event>>> listenerMap = new HashMap<>();
        listenerMap.put(UserNewPayment.class, List.of(new UserPaymentListener(userPaymentService)));
        return new DefaultEventDispatcher(listenerMap);
    }

    @Bean
    public CommandBus paymentCommandBus(UserPaymentService userPaymentService) {
        final Map<Class<? extends Command>, CommandHandler> commandHandlerMap =
                Collections.singletonMap(CreatePayment.class, createPaymentCommandHandler(userPaymentService));
        return new SimpleCommandBus(commandHandlerMap);
    }

    @Bean
    public QueryBus paymentsQueryBus() {
        final Map<Class<? extends Query>, QueryHandler> queryHandlerMap =
                Collections.singletonMap(RetrievePayments.class, new RetrievePaymentsHandler(paymentRepository()));
        return new SimpleQueryBus(queryHandlerMap);
    }

    @Bean
    public QueryBus paymentQueryBus() {
        final Map<Class<? extends Query>, QueryHandler> queryHandlerMap =
                Collections.singletonMap(RetrievePayment.class, new RetrievePaymentHandler(paymentRepository()));
        return new SimpleQueryBus(queryHandlerMap);
    }
}

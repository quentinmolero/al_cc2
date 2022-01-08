package fr.moleroq.al;

import fr.moleroq.al.application.*;
import fr.moleroq.al.domain.UserPaymentRepository;
import fr.moleroq.al.infrastructure.InMemoryUserPaymentRepository;
import fr.moleroq.al.kernel.Query;
import fr.moleroq.al.kernel.QueryBus;
import fr.moleroq.al.kernel.QueryHandler;
import fr.moleroq.al.kernel.SimpleQueryBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.Map;

@Configuration
class UserPaymentConfiguration {

    @Bean
    public UserPaymentRepository userPaymentRepository() {
        return new InMemoryUserPaymentRepository();
    }

    @Bean
    public UserPaymentService userPaymentService() {
        return new UserPaymentService(userPaymentRepository());
    }

    @Bean
    public QueryBus userPaymentsQueryBus() {
        final Map<Class<? extends Query>, QueryHandler> queryHandlerMap =
                Collections.singletonMap(RetrieveUserPayments.class, new RetrieveUserPaymentsHandler(userPaymentRepository()));
        return new SimpleQueryBus(queryHandlerMap);
    }

    @Bean
    public QueryBus userPaymentQueryBus() {
        final Map<Class<? extends Query>, QueryHandler> queryHandlerMap =
                Collections.singletonMap(RetrieveUserPayment.class, new RetrieveUserPaymentHandler(userPaymentRepository()));
        return new SimpleQueryBus(queryHandlerMap);
    }
}

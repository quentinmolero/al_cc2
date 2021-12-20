package fr.moleroq.al.application;

import fr.moleroq.al.domain.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class UserPaymentService {

    private final UserPaymentRepository userPaymentRepository;

    public UserPaymentService(UserPaymentRepository userPaymentRepository) {
        this.userPaymentRepository = userPaymentRepository;
    }

    public void create(User user, Payment payment) {
        this.userPaymentRepository.save(user.getUserId(), payment.getPaymentId());
    }

    public Map<UserId, List<PaymentId>> all() {
        return new ConcurrentHashMap<>(this.userPaymentRepository.findAll());
    }
}

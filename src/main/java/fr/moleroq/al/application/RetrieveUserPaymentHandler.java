package fr.moleroq.al.application;

import fr.moleroq.al.domain.UserPaymentRepository;
import fr.moleroq.al.exposition.UserPaymentDTO;
import fr.moleroq.al.kernel.QueryHandler;

public class RetrieveUserPaymentHandler implements QueryHandler<RetrieveUserPayment, UserPaymentDTO> {

    private final UserPaymentRepository userPaymentRepository;

    public RetrieveUserPaymentHandler(UserPaymentRepository userPaymentRepository) {
        this.userPaymentRepository = userPaymentRepository;
    }

    @Override
    public UserPaymentDTO handle(RetrieveUserPayment query) {
        return new UserPaymentDTO(query.userId, userPaymentRepository.byUserId(query.userId));
    }
}

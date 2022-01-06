package fr.moleroq.al.application;

import fr.moleroq.al.domain.UserPaymentRepository;
import fr.moleroq.al.exposition.UserPaymentDTO;
import fr.moleroq.al.kernel.QueryHandler;

import java.util.List;
import java.util.stream.Collectors;

public class RetrieveUserPaymentsHandler implements QueryHandler<RetrieveUserPayments, List<UserPaymentDTO>> {

    private final UserPaymentRepository userPaymentRepository;

    public RetrieveUserPaymentsHandler(UserPaymentRepository userPaymentRepository) {
        this.userPaymentRepository = userPaymentRepository;
    }

    @Override
    public List<UserPaymentDTO> handle(RetrieveUserPayments query) {
        return userPaymentRepository.findAll().keySet()
                .stream()
                .map(userPayment ->
                        new UserPaymentDTO(userPayment,
                                userPaymentRepository.byUserId(userPayment)))
                .collect(Collectors.toList());
    }
}

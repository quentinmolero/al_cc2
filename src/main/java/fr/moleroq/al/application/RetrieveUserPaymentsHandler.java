package fr.moleroq.al.application;

import fr.moleroq.al.domain.UserPaymentRepository;
import fr.moleroq.al.exposition.UserPaymentDTO;
import fr.moleroq.al.kernel.QueryHandler;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class RetrieveUserPaymentsHandler implements QueryHandler<RetrieveUserPayments, List<UserPaymentDTO>> {

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

    @Override
    public String toString() {
        return "RetrieveUserPaymentsHandler{" +
                "userPaymentRepository=" + userPaymentRepository +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RetrieveUserPaymentsHandler that = (RetrieveUserPaymentsHandler) o;
        return Objects.equals(userPaymentRepository, that.userPaymentRepository);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userPaymentRepository);
    }
}

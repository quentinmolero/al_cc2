package fr.moleroq.al.application;

import fr.moleroq.al.domain.UserPaymentRepository;
import fr.moleroq.al.exposition.UserPaymentDTO;
import fr.moleroq.al.kernel.QueryHandler;

import java.util.Objects;

public final class RetrieveUserPaymentHandler implements QueryHandler<RetrieveUserPayment, UserPaymentDTO> {

    private final UserPaymentRepository userPaymentRepository;

    public RetrieveUserPaymentHandler(UserPaymentRepository userPaymentRepository) {
        this.userPaymentRepository = userPaymentRepository;
    }

    @Override
    public UserPaymentDTO handle(RetrieveUserPayment query) {
        return new UserPaymentDTO(query.userId, userPaymentRepository.byUserId(query.userId));
    }

    @Override
    public String toString() {
        return "RetrieveUserPaymentHandler{" +
                "userPaymentRepository=" + userPaymentRepository +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RetrieveUserPaymentHandler that = (RetrieveUserPaymentHandler) o;
        return Objects.equals(userPaymentRepository, that.userPaymentRepository);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userPaymentRepository);
    }
}

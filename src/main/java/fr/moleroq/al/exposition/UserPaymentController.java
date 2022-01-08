package fr.moleroq.al.exposition;

import fr.moleroq.al.application.RetrieveUserPayment;
import fr.moleroq.al.application.RetrieveUserPayments;
import fr.moleroq.al.domain.UserId;
import fr.moleroq.al.kernel.QueryBus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
final class UserPaymentController {

    private final QueryBus userPaymentsQueryBus;
    private final QueryBus userPaymentQueryBus;

    public UserPaymentController(
            @Qualifier("userPaymentsQueryBus") QueryBus userPaymentsQueryBus,
            @Qualifier("userPaymentQueryBus") QueryBus userPaymentQueryBus) {
        this.userPaymentsQueryBus = userPaymentsQueryBus;
        this.userPaymentQueryBus = userPaymentQueryBus;
    }

    @GetMapping(value = "/userPayments", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserPaymentsDTO> getAll() {
        final List<UserPaymentDTO> userPayments = userPaymentsQueryBus.send(new RetrieveUserPayments());
        UserPaymentsDTO userPaymentsDTOResult = new UserPaymentsDTO();
        userPaymentsDTOResult.userPayments = userPayments;
        return ResponseEntity.ok(userPaymentsDTOResult);
    }

    @GetMapping(value = "/userPayment", params = "userId", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserPaymentDTO> getUserPayment(@RequestParam(value = "userId") int userId) {
        final UserPaymentDTO userPayment = userPaymentQueryBus.send(new RetrieveUserPayment(UserId.of(userId)));
        return ResponseEntity.ok(userPayment);
    }

    @Override
    public String toString() {
        return "UserPaymentController{" +
                "userPaymentsQueryBus=" + userPaymentsQueryBus +
                ", userPaymentQueryBus=" + userPaymentQueryBus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPaymentController that = (UserPaymentController) o;
        return Objects.equals(userPaymentsQueryBus, that.userPaymentsQueryBus) && Objects.equals(userPaymentQueryBus, that.userPaymentQueryBus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userPaymentsQueryBus, userPaymentQueryBus);
    }
}

package fr.moleroq.al.exposition;

import fr.moleroq.al.application.CreatePayment;
import fr.moleroq.al.application.RetrievePayment;
import fr.moleroq.al.application.RetrievePayments;
import fr.moleroq.al.domain.PaymentId;
import fr.moleroq.al.domain.UserId;
import fr.moleroq.al.kernel.CommandBus;
import fr.moleroq.al.kernel.QueryBus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@RestController
public class PaymentController {

    private final QueryBus paymentsQueryBus;
    private final QueryBus paymentQueryBus;
    private final CommandBus commandBus;

    public PaymentController(
            @Qualifier("paymentsQueryBus") QueryBus paymentsQueryBus,
            @Qualifier("paymentQueryBus") QueryBus paymentQueryBus,
            @Qualifier("paymentCommandBus") CommandBus commandBus) {
        this.paymentsQueryBus = paymentsQueryBus;
        this.paymentQueryBus = paymentQueryBus;
        this.commandBus = commandBus;
    }

    @GetMapping(value = "/payments", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PaymentsDTO> getAll() {
        final List<PaymentDTO> payments = paymentsQueryBus.send(new RetrievePayments());
        PaymentsDTO paymentsDTOResult = new PaymentsDTO();
        paymentsDTOResult.payments = payments;
        return ResponseEntity.ok(paymentsDTOResult);
    }

    @GetMapping(value = "/payment", params = "paymentId", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<PaymentDTO> getPayment(@RequestParam(value = "paymentId") int paymentId) {
        final PaymentDTO paymentDTOResult = paymentQueryBus.send(new RetrievePayment(PaymentId.of(paymentId)));
        return ResponseEntity.ok(paymentDTOResult);
    }

    @PostMapping(path = "/payment", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody @Valid PaymentRequest request) {
        CreatePayment createPayment = new CreatePayment(Date.from(Instant.now()), request.amount, UserId.of(Integer.parseInt(request.userId)));
        PaymentId paymentId = commandBus.send(createPayment);
        return ResponseEntity.created(URI.create("/payments/" + paymentId.getValue())).build();
    }
}

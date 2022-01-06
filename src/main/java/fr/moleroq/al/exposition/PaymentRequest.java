package fr.moleroq.al.exposition;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PaymentRequest {

    @NotNull
    @NotBlank
    @DecimalMin("1")
    public long amount;

    @NotNull
    @NotBlank
    public String userId;
}

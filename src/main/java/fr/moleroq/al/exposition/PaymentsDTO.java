package fr.moleroq.al.exposition;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Objects;

@XmlRootElement
final class PaymentsDTO {
    public List<PaymentDTO> payments;

    @Override
    public String toString() {
        return "PaymentsDTO{" +
                "payments=" + payments +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentsDTO that = (PaymentsDTO) o;
        return Objects.equals(payments, that.payments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(payments);
    }
}

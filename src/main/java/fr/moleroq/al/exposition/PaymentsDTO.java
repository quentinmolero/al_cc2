package fr.moleroq.al.exposition;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class PaymentsDTO {
    public List<PaymentDTO> payments;
}

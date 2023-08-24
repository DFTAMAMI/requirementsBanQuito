package ec.edu.espe.banquito.requirements.controller.DTO;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InterestAccrueRS {

    private Integer id;
    private BigDecimal interestRate;
    private String interestType;
    private BigDecimal spread;
    private String chargeFrecuency;
    
}

package ec.edu.espe.banquito.requirements.controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GuarantorRS {

    private Integer id;
    private String code;
    private String type;
    private String name;

}

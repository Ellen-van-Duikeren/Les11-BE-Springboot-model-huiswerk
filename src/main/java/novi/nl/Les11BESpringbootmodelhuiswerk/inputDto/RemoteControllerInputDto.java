package novi.nl.Les11BESpringbootmodelhuiswerk.inputDto;

import lombok.Getter;
import lombok.Setter;
import novi.nl.Les11BESpringbootmodelhuiswerk.models.Television;

@Getter
@Setter

public class RemoteControllerInputDto {
    private String compatibleWith;
    private String batteryType;
    private String name;
    private String brand;
    private Double price;
    private Integer originalStock;
    private Television television;



}

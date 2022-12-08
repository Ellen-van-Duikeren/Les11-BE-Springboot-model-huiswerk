package novi.nl.Les11BESpringbootmodelhuiswerk.inputDto;

import lombok.Getter;
import lombok.Setter;
import novi.nl.Les11BESpringbootmodelhuiswerk.models.Television;

import java.util.List;

@Getter
@Setter
public class WallBracketInputDto {
    private String size;
    private Boolean ajustable;
    private String name;
    private Double price;
    private List<Television> televisions;


}

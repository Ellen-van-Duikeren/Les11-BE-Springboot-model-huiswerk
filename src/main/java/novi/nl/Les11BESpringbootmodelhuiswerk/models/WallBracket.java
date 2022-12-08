package novi.nl.Les11BESpringbootmodelhuiswerk.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "wallbrackets")
public class WallBracket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String size;
    private Boolean adjustable;
    private String name;
    private Double price;

    //    Een ManyToMany relatie tussen Television en WallBracket
    @ManyToMany(mappedBy = "wallbrackets")
    private List<Television> televisions;


    //    getters & setters ...................................
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Boolean getAdjustable() {
        return adjustable;
    }

    public void setAdjustable(Boolean adjustable) {
        this.adjustable = adjustable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Television> getTelevisions() {
        return televisions;
    }

    public void setTelevisions(List<Television> televisions) {
        this.televisions = televisions;
    }
}

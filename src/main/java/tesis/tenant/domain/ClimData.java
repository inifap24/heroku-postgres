package tesis.tenant.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class ClimData implements Serializable {

    @Id
    @Size(max = 8)
    private String fecha;
    private double temp;
    private double eto;

}
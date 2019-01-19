package tesis.tenant.domain;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ClimData implements Serializable {

    @Id
    private Date fecha;
    private double temp;
    private double eto;

}
package tesis.app.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Datalink implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Size(max = 10)
    private String tenantId;

    @Size(max = 30)
    private String name;
    
    @Version
    private int version = 0;

}

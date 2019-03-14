package examplejava.model;

import lombok.Data;
import org.apache.commons.net.ntp.TimeStamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@Table(name = "actor")
public class actorModel {
    @Id
    @NotNull
    @Column(name = "actor_id")
    private Integer actor_id;
    @NotNull
    @Column(name = "first_name")
    private String first_name;
    @NotNull
    @Column(name = "last_name")
    private String last_name;
    @NotNull
    @Column(name = "last_update")
    private Date last_update;
}

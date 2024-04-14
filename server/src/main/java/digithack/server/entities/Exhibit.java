package digithack.server.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "exhibit")
public class Exhibit {

    @Id
    @Column(name = "object_id")
    private Long exhibitId;

    @Column(name = "name")
    private String exhibitName;

    @Column(name = "description")
    private String exhibitDescription;

    @Column(name = "group")
    private String exhibitGroup;

//    @OneToMany
//    private List<Image> imgPaths;
}

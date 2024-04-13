package digithack.server.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Exhibit {

    @Id
    private Long exhibitId;

    private String exhibitName;

    private String exhibitDescription;

    private String exhibitGroup;

    @OneToMany
    private List<Image> imgPaths;
}

package digithack.server.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Image {

    @Id
    private String imageName;

    private String coords;

    @ManyToOne
    private Exhibit exhibit;
}

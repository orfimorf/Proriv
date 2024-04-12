package digithack.server.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Image {

    @Id
    private String imageName;
}

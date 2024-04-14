package digithack.server.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "images")
public class Image {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "object_id")
    private Long objectId;

    @Column(name = "image")
    private String imageName;

    @Column(name = "coords")
    private String coords;

//    @ManyToOne
//    @Column(name="object_id")
//    private Exhibit exhibit;
}

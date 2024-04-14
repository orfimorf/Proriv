package digithack.server.models;

import digithack.server.entities.Exhibit;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Getter
@Setter
public class ExhibitModel {
    private String exhibitName;

    private String exhibitDescription;

    private String exhibitGroup;

    private byte[] image;

    public ExhibitModel(
            ImageModel imageModel, Long exhibitId, String exhibitName, String exhibitDescription, String exhibitGroup
    ) throws IOException {
        this.exhibitName = exhibitName;
        this.exhibitDescription = exhibitDescription;
        this.exhibitGroup = exhibitGroup;

        Resource resource = new ClassPathResource("static/images/" + imageModel.getImageName());
        Path path = Paths.get(resource.getURI());

        this.image = Files.readAllBytes(path);
    }
}

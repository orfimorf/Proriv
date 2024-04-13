package digithack.server.configurations;

import digithack.server.entities.Image;
import digithack.server.models.ImageModel;
import digithack.server.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataBaseConfiguration {
    @Bean
    @Autowired
    public List<ImageModel> getImageModels(ImageRepository imageRepository) {
        var images = imageRepository.findAll();
        List<ImageModel> imageModels = new ArrayList<>();
        for (Image image : images) {
            imageModels.add(new ImageModel(image));
        }
        return imageModels;
    }
}

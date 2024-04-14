package digithack.server.configurations;

import digithack.server.entities.Image;
import digithack.server.models.ImageModel;
import digithack.server.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataBaseConfiguration {
    @Bean
    @Autowired
    public List<ImageModel> getImageModels(ImageRepository imageRepository) {

       // double test = Double.parseDouble("-8.69859238e+01");

        var images = imageRepository.findAll();
        List<ImageModel> imageModels = new ArrayList<>();
        for (Image image : images) {
            imageModels.add(new ImageModel(image));
        }
        return imageModels;
    }
}

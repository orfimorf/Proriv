package digithack.server.configurations;

import digithack.server.entities.Exhibit;
import digithack.server.models.ExhibitModel;
import digithack.server.repositories.ExhibitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataBaseConfiguration {

    @Bean
    @Autowired
    public List<ExhibitModel> getExhibitModels(ExhibitRepository exhibitRepository) {
        var exhibits = exhibitRepository.findAll();
        List<ExhibitModel> exhibitModels = new ArrayList<>();
        for (Exhibit exhibit : exhibits) {
            exhibitModels.add(new ExhibitModel(exhibit));
        }
        return exhibitModels;
    }
}

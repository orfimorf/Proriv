package digithack.server.controllers;

import digithack.server.entities.Exhibit;
import digithack.server.k_nighbours.MyKNearestNeighbours;
import digithack.server.models.CoordsModel;
import digithack.server.models.ExhibitModel;
import digithack.server.models.ImageModel;

import digithack.server.repositories.ExhibitRepository;
import digithack.server.runner.ScriptRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/main")
public class MainController {
    private final ResourceLoader resourceLoader;
    private final ScriptRunner scriptRunner;

    private final ExhibitRepository exhibitRepository;

    private final MyKNearestNeighbours nearestNeighbours;

    @Autowired
    public MainController(ResourceLoader resourceLoader, ScriptRunner scriptRunner, ExhibitRepository exhibitRepository, MyKNearestNeighbours nearestNeighbours) {
        this.resourceLoader = resourceLoader;
        this.scriptRunner = scriptRunner;
        this.exhibitRepository = exhibitRepository;
        this.nearestNeighbours = nearestNeighbours;
    }

    @PostMapping("/analyzeString")
    public ResponseEntity<String[]> analyzeString(@RequestBody CoordsModel coordsModel) throws IOException {
        ImageModel imageModer = new ImageModel(coordsModel.getInputString());
        List<ImageModel> images = nearestNeighbours.findKNearestNeighbours(imageModer, 10);

//        ExhibitModel[] exhibitModels = new ExhibitModel[images.size()];
//        for (int i = 0; i < images.size(); i++) {
//            Exhibit exhibit = exhibitRepository.findById(images.get(i).getExhibitId()).orElse(null);
//            exhibitModels[i] = new ExhibitModel(
//                    images.get(i), exhibit.getExhibitId(), exhibit.getExhibitName(), exhibit.getExhibitDescription(), exhibit.getExhibitGroup()
//            );
//        }

        String[] results = new String[images.size()];
        for (int i = 0; i < images.size(); i++) {
            results[i] = images.get(i).getImageName();
        }

        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @PostMapping("/analyze")
    public ResponseEntity<ExhibitModel[]> analyze(@RequestBody MultipartFile image) throws IOException, InterruptedException {
        System.out.println("ЖЫЖА");

        String uploadDir = "static/images";
        Path uploadPath = Paths.get(
                resourceLoader.getResource("classpath:").getFile().getAbsolutePath(),
                uploadDir
        );

        Path filePath = uploadPath.resolve(image.getOriginalFilename());
        Files.copy(image.getInputStream(), filePath);

        List<ImageModel> images = scriptRunner.findNeighbours(filePath);

        ExhibitModel[] exhibitModels = new ExhibitModel[images.size()];
        for (int i = 0; i < images.size(); i++) {
            Exhibit exhibit = exhibitRepository.findById(images.get(i).getExhibitId()).orElse(null);
            exhibitModels[i] = new ExhibitModel(
                    images.get(i), exhibit.getExhibitId(), exhibit.getExhibitName(), exhibit.getExhibitDescription(), exhibit.getExhibitGroup()
            );
        }

        return new ResponseEntity<>(exhibitModels, HttpStatus.OK);
    }
}

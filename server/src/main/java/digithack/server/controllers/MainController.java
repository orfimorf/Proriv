package digithack.server.controllers;

import digithack.server.models.ExhibitModel;
import digithack.server.models.ImageModel;

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

    @Autowired
    public MainController(ResourceLoader resourceLoader, ScriptRunner scriptRunner) {
        this.resourceLoader = resourceLoader;
        this.scriptRunner = scriptRunner;
    }

    @PostMapping("/analyze")
    public ResponseEntity<ExhibitModel[]> analyze(@RequestBody MultipartFile image) throws IOException, InterruptedException {
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
            exhibitModels[i] = new ExhibitModel(images.get(i));
        }

        return new ResponseEntity<>(exhibitModels, HttpStatus.OK);
    }
}

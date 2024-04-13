package digithack.server.controllers;

import digithack.server.entities.Exhibit;
import digithack.server.repositories.ExhibitRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/main")
public class MainController {
    private final ExhibitRepository exhibitRepo;
    private final ResourceLoader resourceLoader;

    @Autowired
    public MainController(ExhibitRepository exhibitRepo, ResourceLoader resourceLoader) {
        this.exhibitRepo = exhibitRepo;
        this.resourceLoader = resourceLoader;
    }

    @PostMapping("/testPostImage")
    public ResponseEntity<String> testSaveImage(@RequestBody MultipartFile image) throws IOException {
        String uploadDir = "static/images";
        Path uploadPath = Paths.get(
                resourceLoader.getResource("classpath:").getFile().getAbsolutePath(),
                uploadDir
        );

        Path filePath = uploadPath.resolve(image.getOriginalFilename());

        Files.copy(image.getInputStream(), filePath);

        return ResponseEntity.ok("Ok");
    }

    @GetMapping("/testGetImage")
    public ResponseEntity<byte[]> testGetImage() throws IOException {
        Resource resource = new ClassPathResource("static/images/cool-Ilya.jpg");
        Path path = Paths.get(resource.getURI());

        byte[] imageBytes = Files.readAllBytes(path);
        System.out.println(path);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return ResponseEntity.ok().headers(headers).body(imageBytes);
    }
}

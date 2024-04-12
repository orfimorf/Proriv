package digithack.server.controllers;

import digithack.server.entities.Exhibit;
import digithack.server.repositories.ExhibitRepository;

import digithack.server.runner.ScriptRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/main")
public class MainController {
    private final ExhibitRepository exhibitRepo;

    @Autowired
    public MainController(ExhibitRepository exhibitRepo) {
        this.exhibitRepo = exhibitRepo;
    }

    @PostMapping("/analyze")
    public ResponseEntity<List<Exhibit>> analyze(@RequestBody MultipartFile image) {
        return ResponseEntity.ok(ScriptRunner.run(image));
    }
}

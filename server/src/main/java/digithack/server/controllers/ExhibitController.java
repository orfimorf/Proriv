package digithack.server.controllers;

import digithack.server.entities.Exhibit;
import digithack.server.repositories.ExhibitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exhibit")
public class ExhibitController {
    private final ExhibitRepository exhibitRepo;

    @Autowired
    public ExhibitController(ExhibitRepository exhibitRepo) {
        this.exhibitRepo = exhibitRepo;
    }

    @PostMapping("/create")
    public ResponseEntity<Exhibit> createExhibit(@RequestBody Exhibit exhibit) {
        exhibitRepo.save(exhibit);
        return ResponseEntity.ok(exhibit);
    }

    @GetMapping("/getById")
    public ResponseEntity<Exhibit> getById(@RequestParam Long id) {
        var exhibit = exhibitRepo.findById(id).orElse(null);
        return ResponseEntity.ok(exhibit);
    }

    @GetMapping("/getByIds")
    public ResponseEntity<Iterable<Exhibit>> getByIds(@RequestParam List<Long> ids) {
        var exhibits = exhibitRepo.findAllById(ids);
        return ResponseEntity.ok(exhibits);
    }
}

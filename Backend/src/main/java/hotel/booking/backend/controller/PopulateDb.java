package hotel.booking.backend.controller;

import hotel.booking.backend.exceptions.CustomException;
import hotel.booking.backend.service.PopulateDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@CrossOrigin
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class PopulateDb {
    private final PopulateDbService populateDbService;

    @GetMapping("/populate_db")
    public ResponseEntity<String> populateDatabase() {

        File file = new File("hotels.json");
        try {
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            populateDbService.populateDatabase(content);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CustomException e) {
            return ResponseEntity.ok("Something went wrong: " + e.getMessage());
        }

        return ResponseEntity.ok("Data from the json was added successfully!");
    }

    @PostMapping("/populate_db")
    public ResponseEntity<String> populateDatabase(@RequestBody String content) {
        try {
            populateDbService.populateDatabase(content);
        } catch (CustomException e) {
            return ResponseEntity.ok("Something went wrong: " + e.getMessage());
        }
        return ResponseEntity.ok("Data from the json was added successfully!");
    }
}

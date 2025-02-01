package com.msb.journalr.controller;

import com.msb.journalr.entity.JournalEntry;
import com.msb.journalr.service.JournalEntryService;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/journal")
@AllArgsConstructor
public class JournalEntryController {
    final JournalEntryService journalEntryService;

    @GetMapping
    public ResponseEntity<?> getJournalEntries() {
        if (journalEntryService.getAll().isEmpty()) {
            return new ResponseEntity<>(Map.of("message", "no entries found."), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(journalEntryService.getAll());
    }

    @PostMapping
    public ResponseEntity<?> createJournalEntry(@RequestBody JournalEntry journalEntry) {
        ObjectId id = journalEntryService.addEntry(journalEntry);
        if (id == null) {
            return new ResponseEntity<>(Map.of("message", "An Error occurred while creating JournalEntry."),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(Map.of("message", "Entry created successfully.", "id", id.toString()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getJournalEntryById(@PathVariable ObjectId id) {
        var entry = journalEntryService.getById(id);
        if (entry.isEmpty()) {
            return new ResponseEntity<>(Map.of("message", "Journal Entry with id:" + id.toString() + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(entry.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateJournalEntry(@PathVariable ObjectId id, @RequestBody JournalEntry newJournalEntry) {
        if (!journalEntryService.update(id, newJournalEntry)) {
            return new ResponseEntity<>(Map.of("message", "failed to update JournalEntry for id:" + id.toString()),
                    HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(Map.of("message", "Entry updated successfully."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJournalEntry(@PathVariable ObjectId id) {
        if (!journalEntryService.delete(id)) {
            return new ResponseEntity<>(Map.of("message",
                    "Error while deleting JournalEntry with id:" + id.toString()), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(Map.of("message", "JournalEntry with id:" + id + " deleted successfully."));
    }
}

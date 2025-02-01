package com.msb.journalr.controller;

import com.msb.journalr.entity.JournalEntry;
import com.msb.journalr.service.JournalEntryService;
import com.msb.journalr.util.Response;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    final JournalEntryService journalEntryService;

    public JournalEntryController(JournalEntryService journalEntryService) {
        this.journalEntryService = journalEntryService;
    }

    @GetMapping
    public List<JournalEntry> getJournalEntries() {
        return journalEntryService.getAll();
    }

    @PostMapping
    public Response createJournalEntry(@RequestBody JournalEntry journalEntry) {
        ObjectId id = journalEntryService.addEntry(journalEntry);
        if (id == null) {
            return new Response(Map.of("message", "An Error occurred while creating JournalEntry."));
        }
        return new Response(Map.of("message", "Entry created successfully.", "id", id.toString()));
    }

    @GetMapping("/{id}")
    public JournalEntry getJournalEntryById(@PathVariable ObjectId id) {
        return journalEntryService.getById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Response updateJournalEntry(@PathVariable ObjectId id, @RequestBody JournalEntry newJournalEntry) {
        if (journalEntryService.update(id, newJournalEntry)) {
            return new Response(Map.of("message", "Entry updated successfully."));
        }
        return new Response(Map.of("message", "failed to update JournalEntry for id:" + id.toString()));
    }

    @DeleteMapping("/{id}")
    public Response deleteJournalEntry(@PathVariable ObjectId id) {
        if (journalEntryService.delete(id)) {
            return new Response(Map.of("message", "JournalEntry with id:" + id + " deleted successfully."));
        }
        return new Response(Map.of("message", "Error while deleting JournalEntry with id:" + id.toString()));
    }
}

package com.msb.journalr.controller;

import com.msb.journalr.entity.JournalEntry;
import com.msb.journalr.util.Response;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    Map<Long, JournalEntry> journalEntries = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getJournalEntries() {
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping
    public Response createJournalEntry(@RequestBody JournalEntry journalEntry) {
        journalEntries.put(journalEntry.getId(), journalEntry);
        return new Response(Map.of("message", "Entry created successfully."));
    }

    @GetMapping("/{id}")
    public JournalEntry getJournalEntryById(@PathVariable Long id) {
        return journalEntries.get(id);
    }

    @PutMapping("/{id}")
    public Response updateJournalEntry(@PathVariable Long id, @RequestBody JournalEntry newJournalEntry) {
        journalEntries.put(id, newJournalEntry);
        return new Response(Map.of("message", "Entry updated successfully."));
    }

    @DeleteMapping("/{id}")
    public Response deleteJournalEntry(@PathVariable Long id) {
        journalEntries.remove(id);
        return new Response(Map.of("message", "JournalEntry with id:" + id + " deleted successfully."));
    }
}

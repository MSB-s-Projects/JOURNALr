package com.msb.journalr.controller;

import com.msb.journalr.entity.JournalEntry;
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
    public Map<String, String> createJournalEntry(@RequestBody JournalEntry journalEntry) {
        journalEntries.put(journalEntry.getId(), journalEntry);
        Map<String, String> res = new HashMap<>();
        res.put("message", "Entry created successfully.");
        return res;
    }

    @GetMapping("/{id}")
    public JournalEntry getJournalEntryById(@PathVariable Long id) {
        return journalEntries.get(id);
    }

    @PutMapping("/{id}")
    public Map<String, String> updateJournalEntry(@PathVariable Long id, @RequestBody JournalEntry newJournalEntry) {
        journalEntries.put(id, newJournalEntry);
        var res = new HashMap<String, String>();
        res.put("message", "Entry updated successfully.");
        return res;
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deleteJournalEntry(@PathVariable Long id) {
        journalEntries.remove(id);
        var res = new HashMap<String, String>();
        res.put("message", "JournalEntry with id:" + id + " deleted successfully.");
        return res;
    }
}

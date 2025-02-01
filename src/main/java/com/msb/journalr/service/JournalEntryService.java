package com.msb.journalr.service;

import com.msb.journalr.entity.JournalEntry;
import com.msb.journalr.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {
    private static final Logger log = LoggerFactory.getLogger(JournalEntryService.class);
    JournalEntryRepository journalEntryRepository;

    public JournalEntryService(JournalEntryRepository journalEntryRepository) {
        this.journalEntryRepository = journalEntryRepository;
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public ObjectId addEntry(JournalEntry newEntry) {
        var journalEntry = new JournalEntry();
        journalEntry.setDate(LocalDateTime.now());
        BeanUtils.copyProperties(newEntry, journalEntry, "id", "date");

        try {
            return journalEntryRepository.save(journalEntry).getId();
        } catch (Exception e) {
            log.error("Error while adding {}: {}", journalEntry.getId(), e.getMessage());
            return null;
        }
    }

    public Optional<JournalEntry> getById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    public boolean update(ObjectId id, JournalEntry newEntry) {
        var journalEntry = getById(id).orElse(null);
        if (journalEntry == null) {
            return false;
        }
        journalEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isBlank() ? newEntry.getTitle() :
                journalEntry.getTitle());
        journalEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().isBlank() ?
                newEntry.getContent() : journalEntry.getContent());
        try {
            journalEntryRepository.save(journalEntry);
            return true;
        } catch (Exception e) {
            log.error("Error while updating JournalEntry id:{} : {}", journalEntry.getId().toString(), e.getMessage());
            return false;
        }
    }

    public boolean delete(ObjectId id) {
        try {
            journalEntryRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("Error while deleting JournalEntry id:{} : {}", id.toString(), e.getMessage());
            return false;
        }
    }
}

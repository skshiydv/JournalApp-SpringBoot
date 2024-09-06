package io.github.skshiydv.journalapp.Controller;

import io.github.skshiydv.journalapp.entity.Journal_entry;
import io.github.skshiydv.journalapp.entity.User;
import io.github.skshiydv.journalapp.services.JournalService;
import io.github.skshiydv.journalapp.services.userService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    private final userService userService;
    private final JournalService journalService;

//    @GetMapping
//    public ResponseEntity<List<Journal_entry>> getEntries() {
//        return new ResponseEntity<>(journalService.getAllJournals(), HttpStatus.OK);
//    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Journal_entry> getEntryById(@PathVariable ObjectId id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        List<Journal_entry> collect=user.getJournalEntries().stream().filter(x -> x.getId().equals(id)).toList();
        if (!collect.isEmpty()) {
            Journal_entry entry=journalService.getJournalById(id);
            return new ResponseEntity<>(entry, HttpStatus.OK);
        }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }

    @GetMapping
    public ResponseEntity<List<Journal_entry>> getEntryByUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        List<Journal_entry> entries = user.getJournalEntries();
        return new ResponseEntity<>(entries, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Journal_entry> createEntry(@RequestBody Journal_entry entry) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            journalService.addJournal(entry, username);
            return new ResponseEntity<>(entry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/id/{username}/{id}")
    public ResponseEntity<String> deleteEntry(@PathVariable ObjectId id, @PathVariable String username) {
        Journal_entry journal_entry = journalService.getJournalById(id);
        journalService.deleteJournalById(id, username);
        return new ResponseEntity<>("Successfully deleted", HttpStatus.NO_CONTENT);


    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Journal_entry> updateEntry(@PathVariable ObjectId id, @RequestBody Journal_entry entry) {
        Journal_entry oldEntry = journalService.getJournalById(id);
        if (oldEntry!=null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            oldEntry.setTitle(!entry.getTitle().isEmpty() ? entry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(entry.getContent() != null && !entry.getContent().isEmpty() ? entry.getContent() : oldEntry.getContent());
            oldEntry.setAuthor(entry.getAuthor() != null && !entry.getAuthor().isEmpty() ? entry.getAuthor() : oldEntry.getAuthor());
            journalService.addJournal(oldEntry, username);
            return new ResponseEntity<>(oldEntry, HttpStatus.OK);

        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

}

package io.github.skshiydv.journalapp.services;

import io.github.skshiydv.journalapp.entity.Journal_entry;
import io.github.skshiydv.journalapp.entity.User;
import io.github.skshiydv.journalapp.repositories.JournalRepository;
import io.github.skshiydv.journalapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JournalService {
    private final JournalRepository journalRepository;
    private final UserRepository userRepository;
    private final userService userService;

    public List<Journal_entry> getAllJournals() {
        return journalRepository.findAll();
    }

    @Transactional public void deleteJournalById(ObjectId id, String username) {
        try {
            User user = userRepository.findByUsername(username);
            boolean removed=user.getJournalEntries().removeIf(entry -> entry.getId().equals(id));
            if (removed) {
                userService.save(user);
                journalRepository.deleteById(id);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("An error occurred while deleting journal");
        }


    }
    @Transactional
    public void addJournal(Journal_entry journal, String userName) {
        try{
            User user = userRepository.findByUsername(userName);
            journal.setDate(LocalDateTime.now());
            Journal_entry save = journalRepository.save(journal);
            user.getJournalEntries().add(save);
            userRepository.save(user);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException("OOPS GOT AN ERROR");
        }


    }

    public void addJournal(Journal_entry journal) {
        journalRepository.save(journal);
    }

    public Journal_entry getJournalById(ObjectId id) {
        if (journalRepository.existsById(id)) {
            return journalRepository.findById(id).get();
        }
        return null;
    }

}

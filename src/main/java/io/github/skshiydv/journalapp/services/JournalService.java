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

    public List<Journal_entry> getAllJournals() {
        return journalRepository.findAll();
    }

    public void deleteJournalById(ObjectId id, String username) {
        User user = userRepository.findByUsername(username);
        user.getJournalEntries().removeIf(entry -> entry.getId().equals(id));
        userRepository.save(user);
        journalRepository.deleteById(id);
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

    public Optional<Journal_entry> getJournalById(ObjectId id) {
        if (journalRepository.existsById(id)) {
            return journalRepository.findById(id);
        }
        return Optional.empty();
    }

}

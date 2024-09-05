package io.github.skshiydv.journalapp.repositories;


import io.github.skshiydv.journalapp.Controller.JournalEntryController;
import io.github.skshiydv.journalapp.entity.Journal_entry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Document(collection = "journal_entries")
public interface JournalRepository extends MongoRepository<Journal_entry, ObjectId> {
}

package io.github.skshiydv.journalapp.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Document(collection = "journal_entries")
@Data
@NoArgsConstructor
public class Journal_entry {
    @Id
    private ObjectId id;
    @NonNull
    private String title;
    private String author;
    private String content;
    private LocalDateTime date;
}

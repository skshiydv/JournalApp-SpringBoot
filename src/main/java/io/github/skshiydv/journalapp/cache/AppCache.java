package io.github.skshiydv.journalapp.cache;

import io.github.skshiydv.journalapp.entity.ConfigJournalAppEntity;
import io.github.skshiydv.journalapp.repositories.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
@RequiredArgsConstructor
public class AppCache {
    public enum url{
        QUOTES_URL;
    }
    private final ConfigJournalAppRepository configJournalAppRepository;
    private static final Logger logger= LoggerFactory.getLogger(AppCache.class);
    public Map<String,String> APP_CACHE ;
    @PostConstruct
    public void init() {
        APP_CACHE = new HashMap<>();
        List<ConfigJournalAppEntity> all=configJournalAppRepository.findAll();
        logger.info(all.toString());
        for (ConfigJournalAppEntity configJournaLAppEntity : all) {
            APP_CACHE.put(configJournaLAppEntity.getKey(),configJournaLAppEntity.getValue());
        }


    }
}

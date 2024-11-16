package io.github.skshiydv.journalapp.services;

import io.github.skshiydv.journalapp.ExternalAPI.response;
import io.github.skshiydv.journalapp.cache.AppCache;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class quotesService {
    @Value("${quotes.api.key}")
   private String API_KEY;
   private final   RestTemplate restTemplate;

   private final AppCache appCache;

   public response getQuote(String category) {
      HttpHeaders headers = new HttpHeaders();
      headers.set("X-Api-Key", API_KEY);
      HttpEntity<String> entity = new HttpEntity<>(headers);
      String API=appCache.APP_CACHE.get(AppCache.url.QUOTES_URL.toString()).replace("<category>", category);
      ResponseEntity<List<response>> res=restTemplate.exchange(API, HttpMethod.GET, entity, new ParameterizedTypeReference<List<response>>() {});
      List<response> response = res.getBody();
       if(response!=null){
          return response.get(0);
       }
        else{
           return null;
       }
   }



}

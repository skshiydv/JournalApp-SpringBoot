package io.github.skshiydv.journalapp.services;

import io.github.skshiydv.journalapp.ExternalAPI.response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class quotesService {
   private static final String API_KEY="GQ1XYdJAXpoySgeejQEQig==Y32eFgBNbYiCOqTy";
   private static final String api_url = "https://api.api-ninjas.com/v1/quotes?category=CATEGORY";
   private final RestTemplate restTemplate;
   public quotesService(RestTemplate restTemplate) {
      this.restTemplate = restTemplate;
   }

   public response getQuote(String category) {
      HttpHeaders headers = new HttpHeaders();
      headers.set("X-Api-Key", API_KEY);
      HttpEntity<String> entity = new HttpEntity<>(headers);

      String API=api_url.replace("CATEGORY", category);
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

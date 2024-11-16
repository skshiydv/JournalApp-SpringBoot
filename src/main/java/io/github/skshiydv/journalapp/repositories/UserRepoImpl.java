package io.github.skshiydv.journalapp.repositories;

import io.github.skshiydv.journalapp.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@RequiredArgsConstructor
public class UserRepoImpl {
    private  MongoTemplate mongoTemplate;
    public List<User> getUserForSentiment(){
        Query query = new Query();
        query.addCriteria(Criteria.where("email").regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$\n"));
//        query.addCriteria(Criteria.where("email").exists(true).ne(null).ne(""));
        query.addCriteria(Criteria.where("sentiment").is(true));
        List<User> users = mongoTemplate.find(query, User.class);
        return users;
    }
}

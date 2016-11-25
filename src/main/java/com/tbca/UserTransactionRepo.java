package com.tbca;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.Document;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by jamieli on 11/24/16
 */
@SuppressWarnings("Duplicates")
public class UserTransactionRepo {
    String collectionName = "user-transaction";
    MongoClient mongo = new MongoClient();
    MongoDatabase userDb = mongo.getDatabase("userDb");

    public void drop() {
        MongoCollection col = userDb.getCollection(collectionName);
        col.drop();
    }

    public void insert(UserTransaction utx) {
        MongoCollection col = userDb.getCollection(collectionName);
        col.insertOne(utx.toDocument());
    }

    public UserTransaction findById(String id) {
        BsonDocument filter = new BsonDocument();
        filter.put("id", new BsonString(id));

        // We now incorrectly assume that Id is unique
        return findByFilter(filter).get(0);
    }

    public UserTransaction findByHash(String hash) {
        BsonDocument filter = new BsonDocument();
        filter.put("hash", new BsonString(hash));
        // We now incorrectly assume that hash is unique
        return findByFilter(filter).get(0);
    }

    public List<UserTransaction> findByUserId(String userId) {
        BsonDocument filter = new BsonDocument();
        filter.put("userId", new BsonString(userId));

        return findByFilter(filter);
    }

    public List<UserTransaction> findAll() {
        return findByFilter(new BsonDocument());
    }

    private List<UserTransaction> findByFilter(BsonDocument filter) {
        MongoCollection collection = userDb.getCollection(collectionName);
        List<UserTransaction> transactions = new LinkedList<>();

        collection.find(filter).forEach((Block) block -> {
            transactions.add(UserTransaction.fromDocument((Document) block));
        });

        return transactions;
    }

}
package com.tbca;

import org.bson.Document;

/**
 * Created by jamieli on 11/24/16
 */
public class UserTransaction {
    private String id;
    private String userId;
    private String hash;
    private String transaction;

    public UserTransaction() {

    }

    public UserTransaction(String id, String userId, String hash, String transaction) {
        this.id = id;
        this.userId = userId;
        this.hash = hash;
        this.transaction = transaction;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Document toDocument() {
        Document doc = new Document();
        doc.put("id", getId());
        doc.put("userId", getUserId());
        doc.put("hash", getHash());
        doc.put("transaction", getTransaction());

        return doc;
    }

    public static UserTransaction fromDocument(Document doc) {
        UserTransaction tx = new UserTransaction();

        tx.setId(doc.getString("id"));
        tx.setUserId(doc.getString("userId"));
        tx.setHash(doc.getString("hash"));
        tx.setTransaction(doc.getString("transaction"));
        return tx;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserTransaction that = (UserTransaction) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (hash != null ? !hash.equals(that.hash) : that.hash != null) return false;
        return transaction != null ? transaction.equals(that.transaction) : that.transaction == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (hash != null ? hash.hashCode() : 0);
        result = 31 * result + (transaction != null ? transaction.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserTransaction{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", hash='" + hash + '\'' +
                ", transaction='" + transaction + '\'' +
                '}';
    }
}
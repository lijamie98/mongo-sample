package com.tbca;

import org.junit.Test;

import static junit.framework.Assert.assertTrue;

public class AppTest {

    @Test
    public void basicTest() {
        UserTransactionRepo repo = new UserTransactionRepo();

        System.out.print("Dropping... ");
        repo.drop();
        System.out.print("Collection contains " + repo.findAll().size() + " items\n");


        UserTransaction tx1 = getMockTx1();
        UserTransaction tx2 = getMockTx2();
        UserTransaction tx3 = getMockTx3();
        UserTransaction tx4 = getMockTx4();
        UserTransaction tx5 = getMockTx5();

        repo.insert(tx1);
        repo.insert(tx2);
        repo.insert(tx3);
        repo.insert(tx4);
        repo.insert(tx5);

        assertTrue(repo.findById(tx1.getId()).equals(tx1));
        assertTrue(repo.findByHash(tx1.getHash()).equals(tx1));
        assertTrue(repo.findByUserId("UserId-1").size() == 3);

        long t = System.currentTimeMillis();

        for (int i = 0; i < 10000; i++) {
            repo.findById(tx1.getId());
            repo.findById(tx2.getId());
            repo.findById(tx3.getId());
            repo.findById(tx4.getId());
            repo.findById(tx5.getId());
            repo.findByHash(tx1.getHash());
            repo.findByHash(tx2.getHash());
            repo.findByHash(tx3.getHash());
            repo.findByHash(tx4.getHash());
            repo.findByHash(tx5.getHash());
            repo.findByUserId("UserId-1");
            repo.findByUserId("UserId-2");
        }

        System.out.println(System.currentTimeMillis() - t);

    }

    public static UserTransaction getMockTx1() {
        return new UserTransaction(
                "ID-1",
                "UserId-1",
                "Hash-1",
                "Contents-1");
    }
    public static UserTransaction getMockTx2() {
        return new UserTransaction(
                "ID-2",
                "UserId-1",
                "Hash-2",
                "Contents-2");
    }
    public static UserTransaction getMockTx3() {
        return new UserTransaction(
                "ID-3",
                "UserId-1",
                "Hash-3",
                "Contents-3");
    }
    public static UserTransaction getMockTx4() {
        return new UserTransaction(
                "ID-4",
                "UserId-2",
                "Hash-4",
                "Contents-4");
    }

    public static UserTransaction getMockTx5() {
        return new UserTransaction(
                "ID-5",
                "UserId-2",
                "Hash-5",
                "Contents-5");
    }

}
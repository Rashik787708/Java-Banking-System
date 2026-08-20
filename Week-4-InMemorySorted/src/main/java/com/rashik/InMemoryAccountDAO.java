package com.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class InMemoryAccountDAO implements AccountDAO {

    private TreeMap<Integer, Account> accounts = new TreeMap<>();

    @Override
    public void save(Account account) {
        accounts.put(account.getId(), account);
    }

    @Override
    public Account findById(Integer id) {
        return accounts.get(id);
    }

    @Override
    public List<Account> findAll() {
        return new ArrayList<>(accounts.values());
    }

    @Override
    public void deleteById(Integer id) {
        accounts.remove(id);
    }
}
package com.bank;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class InMemoryAccountDAO implements AccountDAO {

    private LinkedHashMap<Integer, Account> accounts = new LinkedHashMap<>();

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
package com.example.demo.models;

/**
 * Created by student on 6/23/17.
 */

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by student on 6/20/17.
 */

import com.example.demo.models.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Integer> {

    public List<Account> findAllByAcctNum(Integer acctNum);

    public Account findOneByUserName(String userName);



}

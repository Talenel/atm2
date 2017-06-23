package com.example.demo.models;

/**
 * Created by student on 6/20/17.
 */

import com.example.demo.models.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AtmRepository extends CrudRepository<Transaction, Integer> {

    public List<Transaction> findAllByAcctNum(Integer acctNum);



}

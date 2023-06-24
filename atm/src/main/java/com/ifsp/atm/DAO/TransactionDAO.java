package com.ifsp.atm.DAO;


import com.ifsp.atm.model.TransactionModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TransactionDAO {
    ResponseEntity insert(TransactionModel transaction);
    ResponseEntity<List<TransactionModel>> findById(int id);
}

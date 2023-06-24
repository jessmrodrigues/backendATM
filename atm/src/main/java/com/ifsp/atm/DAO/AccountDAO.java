package com.ifsp.atm.DAO;


import com.ifsp.atm.model.AccountModel;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;
import java.util.List;

public interface AccountDAO {
    ResponseEntity insert(AccountModel usuario);
    AccountModel findById(int id);
    ResponseEntity<String> Authentication(String name , String pin);

    ResponseEntity<List<AccountModel>> findAll() throws SQLException;
    ResponseEntity<AccountModel> update(AccountModel account, int id) throws SQLException;
}

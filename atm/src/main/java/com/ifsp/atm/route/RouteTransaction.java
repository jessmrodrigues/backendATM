package com.ifsp.atm.route;

import com.ifsp.atm.DTO.MySqlTransaction;
import com.ifsp.atm.model.TransactionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class RouteTransaction {
    private final MySqlTransaction serviceTransaction;

    public RouteTransaction(MySqlTransaction serviceTransaction) {
        this.serviceTransaction = serviceTransaction;
    }
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity createTransaction(@RequestBody TransactionModel transaction) {
        return serviceTransaction.insert(transaction);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<TransactionModel>> getTransaction(@PathVariable("id") int id) {
        return serviceTransaction.findById(id);
    }
}

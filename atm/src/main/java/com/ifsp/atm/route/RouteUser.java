package com.ifsp.atm.route;

import com.ifsp.atm.DTO.MySqlAccount;
import com.ifsp.atm.model.AccountModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class RouteUser {
    private final MySqlAccount serviceUsuario;

    public RouteUser(MySqlAccount serviceUsuario) {
        this.serviceUsuario = serviceUsuario;
    }
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity createAccount(@RequestBody AccountModel account) {
        return serviceUsuario.insert(account);
    }

    @GetMapping
    public ResponseEntity<List<AccountModel>> getAccount() {
        return serviceUsuario.findAll();
    }

    @GetMapping("/{id}")
    public AccountModel getAccountById(@PathVariable("id") int id) {
        return serviceUsuario.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountModel> updateAccount(@RequestBody AccountModel user, @PathVariable("id") int id) {
        return serviceUsuario.update(user, id);
    }
}

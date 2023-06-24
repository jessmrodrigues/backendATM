package com.ifsp.atm.route;

import com.ifsp.atm.DTO.MySqlAccount;
import com.ifsp.atm.model.AccountModel;
import com.ifsp.atm.model.AuthModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class RouteAuthentication {
    private final MySqlAccount serviceUsuario;

    public RouteAuthentication(MySqlAccount serviceUsuario) {
        this.serviceUsuario = serviceUsuario;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public ResponseEntity auth(@RequestBody AuthModel authModel) {
        return serviceUsuario.Authentication(authModel.getName(), authModel.getPin());
    }

}

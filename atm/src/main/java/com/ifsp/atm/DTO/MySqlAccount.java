package com.ifsp.atm.DTO;

import com.ifsp.atm.Conexao;
import com.ifsp.atm.DAO.AccountDAO;
import com.ifsp.atm.model.AccountModel;
import com.ifsp.atm.model.AuthModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MySqlAccount implements AccountDAO {
    private Connection connection;
    public MySqlAccount() {
        this.connection = new Conexao().getConexao();
    }

    public ResponseEntity insert(AccountModel account) {
        if (this.connection != null) {
            try {
                PreparedStatement stmt = connection.prepareStatement("INSERT INTO Account(id, name, pin, balance) VALUES(?,?,?,?)");

                stmt.setInt(1, account.getId());
                stmt.setString(2, account.getName());
                stmt.setString(3, account.getPin());
                stmt.setString(4, account.getBalance());
                stmt.execute();
                stmt.close();
                return ResponseEntity.ok("Criado");
            } catch (Exception e) {
                e.getMessage();
            }
            return ResponseEntity.badRequest().body("Falha na request");
        }
        return null;
    }

    public ResponseEntity<List<AccountModel>> findAll() {
        if (this.connection != null) {
            try {
                List<AccountModel> dados = new ArrayList<>();

                PreparedStatement ps = connection.prepareStatement("SELECT * FROM Account");
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    AccountModel accountModel = new AccountModel();
                    accountModel.setId(rs.getInt("id"));
                    accountModel.setName(rs.getString("name"));
                    accountModel.setPin(rs.getString("pin"));
                    accountModel.setBalance(rs.getString("balance"));
                    dados.add(accountModel);
                }
                return ResponseEntity.ok(dados);
            } catch (SQLException e) {
                e.getMessage();
            }
        }
        return null;
    }

    @Override
    public AccountModel findById(int id) {
        if (this.connection != null) {
            try {
                AccountModel accountModel = new AccountModel();
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM Account WHERE id = " + id);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    accountModel.setId(rs.getInt("id"));
                    accountModel.setName(rs.getString("name"));
                    accountModel.setPin(rs.getString("pin"));
                    accountModel.setBalance(rs.getString("balance"));
                    return accountModel;
                }
            } catch (SQLException e) {
                e.getMessage();
            }
        }
        return null;
    }
    public ResponseEntity<AccountModel> update(AccountModel balance, int id) {
        if (this.connection != null) {
            try {
                PreparedStatement stmt =
                        connection.prepareStatement("UPDATE Account SET balance = ? WHERE id = " + id);

                stmt.setString(1, balance.getBalance());

                stmt.execute();
                return ResponseEntity.ok(balance);
            } catch (SQLException e) {
                e.getMessage();
            }
        }
        return null;
    }
    @Override
    public ResponseEntity<String> Authentication(String name, String pin) {
        if (this.connection != null) {
            try {
                AuthModel authModel = new AuthModel();
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM Account WHERE name = ? and pin = ?");
                ps.setString(1, name);
                ps.setString(2, pin);

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    authModel.setName(rs.getString("name"));
                    authModel.setPin(rs.getString("pin"));
                    return ResponseEntity.ok("Autenticado!");
                }
            } catch (SQLException e) {
                e.getMessage();
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha na autenticação!");
    }
}

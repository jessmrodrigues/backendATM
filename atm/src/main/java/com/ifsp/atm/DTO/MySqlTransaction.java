package com.ifsp.atm.DTO;

import com.ifsp.atm.Conexao;
import com.ifsp.atm.DAO.TransactionDAO;
import com.ifsp.atm.model.TransactionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MySqlTransaction implements TransactionDAO {
    private Connection connection;
    public MySqlTransaction() {
        this.connection = new Conexao().getConexao();
    }

    public ResponseEntity insert(TransactionModel transaction) {
        if (this.connection != null) {
            try {
                PreparedStatement stmt = connection.prepareStatement("INSERT INTO Transactions(id, accountId, type, balance) VALUES(?,?,?,?)");

                stmt.setInt(1, transaction.getId());
                stmt.setInt(2, transaction.getAccountId());
                stmt.setString(3, transaction.getType());
                stmt.setString(4, transaction.getBalance());
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

    @Override
    public ResponseEntity<List<TransactionModel>> findById(int id) {
        if (this.connection != null) {
            try {
                List<TransactionModel> dados = new ArrayList<>();
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM Transactions WHERE accountId = ?");
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    TransactionModel transactionModel = new TransactionModel();
                    transactionModel.setId(rs.getInt("id"));
                    transactionModel.setAccountId(rs.getInt("accountId"));
                    transactionModel.setType(rs.getString("type"));
                    transactionModel.setBalance(rs.getString("balance"));
                    dados.add(transactionModel);
                }
                return ResponseEntity.ok(dados);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ResponseEntity.notFound().build();
    }

}

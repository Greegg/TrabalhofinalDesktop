/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sc.senac.br.modelo.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sc.senac.br.interfaces.DaoI;
import sc.senac.br.modelo.Cliente;
import sc.senac.factory.Dao;

/**
 *
 * @author hvb
 */
public class ClienteDao extends Dao implements DaoI<Cliente> {

    @Override
    public boolean salvar(Cliente obj) {
        sql = "INSERT INTO cliente (nome, data_nascimento, email, telefone) VALUES(?,?,?)";
        try {
            stmt = conexao.prepareStatement(this.sql);
            stmt.setString(1, obj.getNome());
            stmt.setDate(2, (Date) obj.getDataNascimento());
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getTelefone());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    @Override
    public boolean atualizar(Cliente obj) {
        try {
            stmt = conexao.prepareStatement("UPDATE cliente SET nome = ?, data_nascimento=?, email=?, telefone=? WHERE id=?");

            stmt.setString(1, obj.getNome());
            stmt.setDate(2, obj.getDataNascimento());
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getTelefone());
            stmt.setInt(4, obj.getId());

            if (stmt.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean excluir(Cliente obj) {
        try {
            stmt = conexao.prepareStatement("UPDATE cliente SET status = 0 WHERE id= ?");
            stmt.setInt(1, obj.getId());
            return (stmt.executeUpdate() > 0);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public List<Cliente> listar() {
        try {
            this.sql = "SELECT id, nome, data_nascimento, email, telefone FROM cliente WHERE status = 1 ORDER BY id ASC";
            stmt = conexao.prepareStatement(sql);
            res = stmt.executeQuery();

            List<Cliente> lista = new ArrayList<>();
            while (res.next()) {
                cliente = new Cliente();

                cliente.setId(res.getInt("id"));
                cliente.setNome(res.getString("nome"));
                cliente.setDataNascimento(res.getDate("data_nascimento"));
                cliente.setEmail(res.getString("email"));
                cliente.setTelefone(res.getString("telefone"));

                lista.add(cliente);
            }
            return lista;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Cliente lerPorId(int id) {
        try {
            stmt = conexao.prepareStatement("SELECT id, nome, data_nascimento, email, telefone FROM cliente"); //seleciona o cliente pelo ID
            stmt.setInt(1, id);
            res = stmt.executeQuery();
            
            if (res.next()) { 
                cliente.setId(res.getInt("id"));
                cliente.setNome(res.getString("nome"));
                cliente.setNome(res.getString("nome"));
                cliente.setDataNascimento(res.getDate("data_nascimento"));
                cliente.setEmail(res.getString("email"));
                cliente.setTelefone(res.getString("telefone"));
            }
            
            return cliente;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Cliente> pesquisarNome(String nome) {
        try {
            stmt = conexao.prepareStatement("SELECT id, nome, dataNascimento, cpf FROM cliente WHERE nome LIKE?");
            stmt.setString(1, nome+"%");
            res = stmt.executeQuery();
            List<Cliente> lista = new ArrayList<>();
            while (res.next()) {
                cliente.setId(res.getInt("id"));
                cliente.setNome(res.getString("nome"));
                cliente.setNome(res.getString("nome"));
                cliente.setDataNascimento(res.getDate("data_nascimento"));
                cliente.setEmail(res.getString("email"));
                cliente.setTelefone(res.getString("telefone"));
                lista.add(cliente);
            }
            return lista;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}

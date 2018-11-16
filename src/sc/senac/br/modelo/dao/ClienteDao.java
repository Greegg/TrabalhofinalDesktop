/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sc.senac.br.modelo.dao;

import java.sql.Date;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean excluir(Cliente obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cliente> listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cliente lerPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cliente> pesquisarNome(String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

package cadastrocliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ClienteDAO {

    private Connection conexao;

    //construtor
    public ClienteDAO() {
        conexao = ConnectionFactory.getConnection();
    }

    //Método inserir
    public boolean inserirCliente(Cliente cliente) {
        boolean status = false;
        String sql = "INSERT INTO clientes (nome, nascimento, sexo, senha, cep, "
                + "logradouro, bairro, complemento, numero, telefone)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt;

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getNascimento());
            stmt.setString(3, cliente.getSexo());
            stmt.setString(4, cliente.getSenha());
            stmt.setString(5, cliente.getcep());
            stmt.setString(6, cliente.getLogradouro());
            stmt.setString(7, cliente.getBairro());
            stmt.setString(8, cliente.getComplemento());
            stmt.setString(9, cliente.getNumero());
            stmt.setString(10, cliente.getTelefone());
            stmt.execute();
            stmt.close();
            status = true;

            JOptionPane.showMessageDialog(null, "Salvo com Sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao salvar" + ex);
        }
        return status;
    }

    //Método listar 
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();

        String sql = "SELECT * FROM clientes ORDER BY nome";
        PreparedStatement stmt;

        try {
            stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setNascimento(rs.getString("nascimento"));
                cliente.setSexo(rs.getString("sexo"));
                cliente.setSenha(rs.getString("senha"));
                cliente.setcep(rs.getString("cep"));
                cliente.setLogradouro(rs.getString("logradouro"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setComplemento(rs.getString("complemento"));
                cliente.setNumero(rs.getString("numero"));
                cliente.setTelefone(rs.getString("telefone"));
                clientes.add(cliente);
            }
            stmt.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return clientes;
    }

    //Método pesquisar
    public List<Cliente> pesquisarClientes(String telefone) {
        List<Cliente> clientes = new ArrayList<>();

        String sql = "SELECT * FROM clientes WHERE telefone = ?";
        PreparedStatement stmt;

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, telefone);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setNascimento(rs.getString("nascimento"));
                cliente.setSexo(rs.getString("sexo"));
                cliente.setSenha(rs.getString("senha"));
                cliente.setcep(rs.getString("cep"));
                cliente.setLogradouro(rs.getString("logradouro"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setComplemento(rs.getString("complemento"));
                cliente.setNumero(rs.getString("numero"));
                cliente.setTelefone(rs.getString("telefone"));
                clientes.add(cliente);
            }
            stmt.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return clientes;
    }

    //Método update
    public void updateCliente(Cliente cliente) {
        String sql = "UPDATE clientes SET nome = ?, nascimento = ?, sexo = ?, senha = ?, cep = ?, "
                + "logradouro = ?, bairro = ?, complemento = ?, numero = ? WHERE telefone = ? ";
        PreparedStatement stmt;

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getNascimento());
            stmt.setString(3, cliente.getSexo());
            stmt.setString(4, cliente.getSenha());
            stmt.setString(5, cliente.getcep());
            stmt.setString(6, cliente.getLogradouro());
            stmt.setString(7, cliente.getBairro());
            stmt.setString(8, cliente.getComplemento());
            stmt.setString(9, cliente.getNumero());
            stmt.setString(10, cliente.getTelefone());
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Atualizado com Sucesso!");

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao atualizar" + ex);
        }
        try {
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Método excluir
    public void excluirCliente(Cliente cliente) {
        String sql = "DELETE  FROM clientes WHERE telefone = ?";
        PreparedStatement stmt;

        try {
            stmt = conexao.prepareStatement(sql);

            stmt.setString(1, cliente.getTelefone());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

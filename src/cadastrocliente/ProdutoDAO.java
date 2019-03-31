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

public class ProdutoDAO {

    private Connection conexao;

    public ProdutoDAO() {
        conexao = ConnectionFactory.getConnection();

    }

    public boolean inserirProduto(Produto produto) {
        boolean status = false;
        String sql = "INSERT INTO produtos (cod, nome, preco, ingredientes)values(?,?,?)";
        PreparedStatement stmt;

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, produto.getCod());
            stmt.setString(2, produto.getNome());
            stmt.setDouble(3, produto.getPreco());
            stmt.setString(4, produto.getIngredientes());
            stmt.execute();
            stmt.close();
            status = true;

            JOptionPane.showMessageDialog(null, "Salvo com Sucesso!");

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao Salvar" + ex);
        }
        return status;
    }

    public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();

        String sql = "SELECT * FROM produtos ORDER BY nome";
        PreparedStatement stmt;

        try {
            stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setCod(rs.getInt("cod"));
                produto.setNome(rs.getString("nome"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setIngredientes(rs.getString("ingredientes"));
                produtos.add(produto);

            }
            stmt.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return produtos;

    }

    public List<Produto> pesquisarProduto(String nome) {
        List<Produto> produtos = new ArrayList<>();

        String sql = "SELECT * FROM produtos WHERE nome = ?";
        PreparedStatement stmt;

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setCod(rs.getInt("cod"));
                produto.setNome(rs.getString("nome"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setIngredientes(rs.getString("ingredientes"));
                produtos.add(produto);
            }
            stmt.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return produtos;
    }

    public void updateProduto(Produto produto) {
        String sql = "UPDATE produtos SET cod = ?, nome = ?, preco = ?, ingredientes = ? WHERE cod = ?";
        PreparedStatement stmt;

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, produto.getCod());
            stmt.setString(2, produto.getNome());
            stmt.setDouble(3, produto.getPreco());
            stmt.setString(4, produto.getIngredientes());
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Atualizado com Sucesso!");

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao atualizar" + ex);
        }

        try {
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void excluirProduto(Produto produto) {
        String sql = "DELETE FROM produtos WHERE cod = ?";
        PreparedStatement stmt;

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, produto.getCod());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao excluir" + ex);
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

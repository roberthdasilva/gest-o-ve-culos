package negocio;

import dados.VeiculoAbstrato;
import excecao.*;
import excecao.RepositorioVazio;
import excecao.NaoEMoto;
import java.sql.SQLException;
import java.util.ArrayList;

public interface InterfaceCadastroVeiculoAbstrato {
    
   public void conectar() throws SQLException;
   public void fechar() throws SQLException;
   public void commit() throws SQLException;
   public void rollback() throws SQLException;
   public void inserir(VeiculoAbstrato va) throws SQLException, ChassiInvalido;
   public ArrayList<VeiculoAbstrato> consultarLike(String chassi) throws SQLException, VeiculoNaoEncontrado, ChassiVazio;
   public VeiculoAbstrato consultar(String chassi) throws SQLException, VeiculoNaoEncontrado;
   public void remover(String chassi) throws SQLException, VeiculoNaoEncontrado;
   public void atualizar(VeiculoAbstrato va) throws SQLException, VeiculoNaoEncontrado;
   public ArrayList<VeiculoAbstrato> listar() throws SQLException, RepositorioVazio;
   public void SomDoMotor(String chassi) throws SQLException, VeiculoNaoEncontrado;
}
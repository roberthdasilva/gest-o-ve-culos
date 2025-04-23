package repositorio;

import dados.VeiculoAbstrato;
import excecao.*;
import java.sql.SQLException;
import java.util.ArrayList;

public interface InterfaceRepositorioVeiculoAbstrato {

public void conectar() throws SQLException;
public void fechar() throws SQLException;
public void commit() throws SQLException;
public void rollback() throws SQLException;
public void inserir(VeiculoAbstrato fa) throws SQLException, ChassiInvalido;
public ArrayList<VeiculoAbstrato> consultarLike(String chassi) throws SQLException, VeiculoNaoEncontrado, ChassiVazio;
public VeiculoAbstrato consultar(String chassi) throws SQLException, VeiculoNaoEncontrado;
public void remover(String cpf) throws SQLException, VeiculoNaoEncontrado;
public void atualizar(VeiculoAbstrato fa) throws SQLException, VeiculoNaoEncontrado;
public ArrayList<VeiculoAbstrato> listar() throws SQLException, RepositorioVazio;
public void SomDoMotor() throws SQLException, NaoEMoto, VeiculoNaoEncontrado;
}
package fachada;

import dados.VeiculoAbstrato;
import excecao.*;
import java.sql.SQLException;
import java.util.ArrayList;

public interface InterfaceVeiculos {
    void conectar() throws SQLException;
    void fechar() throws SQLException;
    void commit() throws SQLException;
    void rollback() throws SQLException;
    void VeiculoAbstratoInserir(VeiculoAbstrato va) throws SQLException, ChassiInvalido;
    ArrayList<VeiculoAbstrato> VeiculoAbstratoConsultarLike(String chassi) throws SQLException, VeiculoNaoEncontrado, ChassiVazio;
    VeiculoAbstrato VeiculoAbstratoConsultar(String chassi) throws SQLException, VeiculoNaoEncontrado;
    void VeiculoAbstratoRemover(String chassi) throws SQLException, VeiculoNaoEncontrado;
    void VeiculoAbstratoAtualizar(VeiculoAbstrato va) throws SQLException, VeiculoNaoEncontrado;
    ArrayList<VeiculoAbstrato> VeiculoAbstratoListar() throws SQLException, RepositorioVazio;
}

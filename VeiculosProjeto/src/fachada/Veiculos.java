package fachada;

import dados.*;
import excecao.*;
import java.sql.SQLException;
import java.util.ArrayList;
import negocio.CadastroVeiculoAbstrato;

public class Veiculos implements InterfaceVeiculos {

    CadastroVeiculoAbstrato cadastroVeiculo = new CadastroVeiculoAbstrato();

    @Override
    public void conectar() throws SQLException {
        this.cadastroVeiculo.conectar();
    }

    @Override
    public void fechar() throws SQLException {
        this.cadastroVeiculo.fechar();
    }

    @Override
    public void commit() throws SQLException {
        this.cadastroVeiculo.commit();
    }

    @Override
    public void rollback() throws SQLException {
        this.cadastroVeiculo.rollback();
    }

    @Override
    public void VeiculoAbstratoInserir(VeiculoAbstrato va) throws SQLException, ChassiInvalido {
        this.cadastroVeiculo.inserir(va);
    }

    @Override
    public ArrayList<VeiculoAbstrato> VeiculoAbstratoConsultarLike(String chassi) throws SQLException, VeiculoNaoEncontrado, ChassiVazio {
        return this.cadastroVeiculo.consultarLike(chassi);
    }

    @Override
    public VeiculoAbstrato VeiculoAbstratoConsultar(String chassi) throws SQLException, VeiculoNaoEncontrado {
        return this.cadastroVeiculo.consultar(chassi);
    }

    @Override
    public void VeiculoAbstratoRemover(String chassi) throws SQLException, VeiculoNaoEncontrado {
        this.cadastroVeiculo.remover(chassi);
    }

    @Override
    public void VeiculoAbstratoAtualizar(VeiculoAbstrato va) throws SQLException, VeiculoNaoEncontrado {
        this.cadastroVeiculo.atualizar(va);
    }

    @Override
    public ArrayList<VeiculoAbstrato> VeiculoAbstratoListar() throws SQLException, RepositorioVazio {
        return this.cadastroVeiculo.listar();
    }
}
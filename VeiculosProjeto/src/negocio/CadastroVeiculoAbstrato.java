package negocio;

import dados.*;
import excecao.*;
import java.sql.SQLException;
import java.util.ArrayList;
import repositorio.RepositorioVeiculoAbstrato;

public class CadastroVeiculoAbstrato implements InterfaceCadastroVeiculoAbstrato{

    RepositorioVeiculoAbstrato repositorioVeiculoAbstrato = new RepositorioVeiculoAbstrato();
    
    @Override
    public void conectar() throws SQLException {
        this.repositorioVeiculoAbstrato.conectar();
    }

    @Override
    public void fechar() throws SQLException {
        this.repositorioVeiculoAbstrato.fechar();
    }

    @Override
    public void commit() throws SQLException {
        this.repositorioVeiculoAbstrato.commit();
    }

    @Override
    public void rollback() throws SQLException {
        this.repositorioVeiculoAbstrato.rollback();
    }

    @Override
    public void inserir(VeiculoAbstrato va) throws SQLException, ChassiInvalido{
        this.repositorioVeiculoAbstrato.inserir(va);
    }

    @Override
    public ArrayList<VeiculoAbstrato> consultarLike(String chassi) throws SQLException, VeiculoNaoEncontrado, ChassiVazio {
        return this.repositorioVeiculoAbstrato.consultarLike(chassi);
    }

    @Override
    public VeiculoAbstrato consultar(String chassi) throws SQLException, VeiculoNaoEncontrado {
        return this.repositorioVeiculoAbstrato.consultar(chassi);
    }

    @Override
    public void remover(String chassi) throws SQLException, VeiculoNaoEncontrado {
        this.repositorioVeiculoAbstrato.remover(chassi);
    }

    @Override
    public void atualizar(VeiculoAbstrato va) throws SQLException, VeiculoNaoEncontrado {
        this.repositorioVeiculoAbstrato.atualizar(va);
    }

    @Override
    public ArrayList<VeiculoAbstrato> listar() throws SQLException, RepositorioVazio {
        return this.repositorioVeiculoAbstrato.listar();
    }
    
    @Override
    public void SomDoMotor(String chassi) throws SQLException, VeiculoNaoEncontrado {
        VeiculoAbstrato veiculo = this.repositorioVeiculoAbstrato.consultar(chassi);
        veiculo.SomDoMotor();
    }
}
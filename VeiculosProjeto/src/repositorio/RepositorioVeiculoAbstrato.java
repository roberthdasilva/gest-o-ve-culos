    package repositorio;

    import conexao.FabricaConexao;
    import dados.*;
    import excecao.*;
    import java.sql.SQLException;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.Connection;
    import java.util.ArrayList;

    public class RepositorioVeiculoAbstrato implements InterfaceRepositorioVeiculoAbstrato{
        private Connection conexao;

        @Override
        public void conectar() throws SQLException{
            this.conexao = FabricaConexao.getConexao();
            this.conexao.setAutoCommit(false);
        }

        @Override
        public void fechar() throws SQLException{
            this.conexao.close();
        }

        @Override
        public void commit() throws SQLException{
            this.conexao.commit();
        }

        @Override
        public void rollback() throws SQLException{
            this.conexao.rollback();
        }

        @Override
        public void inserir(VeiculoAbstrato va) throws SQLException, ChassiInvalido{
            if (va.getChassi() == null || va.getChassi().isEmpty()){
                throw new ChassiInvalido();
            } else{
                String sql = "INSERT INTO VeiculoAbstrato (marca, modelo, ano, cor, chassi, quilometragem, tipo_combustivel, cilindradas) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement statement = this.conexao.prepareStatement(sql);
                statement.setString(1, va.getMarca());
                statement.setString(2, va.getModelo());
                statement.setInt(3, va.getAno());
                statement.setString(4, va.getCor());
                statement.setString(5, va.getChassi());
                statement.setDouble(6, va.getQuilometragem());
                if (va instanceof Carro){
                    statement.setString(7, ((Carro) va).getTipoCombustivel());
                    statement.setNull(8, java.sql.Types.INTEGER);
                } else{
                    statement.setNull(7, java.sql.Types.VARCHAR);
                    statement.setInt(8, ((Moto) va).getCilindradas());
                }
                statement.executeUpdate();
                statement.close();
            }
        }

        @Override
        public ArrayList<VeiculoAbstrato> consultarLike(String chassi) throws SQLException, VeiculoNaoEncontrado, ChassiVazio{
            if (chassi == null || chassi.isEmpty()) {
                throw new ChassiVazio();
            } else {
                ArrayList<VeiculoAbstrato> vas = new ArrayList<>();
                String sql = "SELECT * FROM VeiculoAbstrato WHERE chassi LIKE ?";
                PreparedStatement statement = this.conexao.prepareStatement(sql);
                statement.setString(1, "%" + chassi + "%");
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    String marca = resultSet.getString("marca");
                    String modelo = resultSet.getString("modelo");
                    int ano = resultSet.getInt("ano");
                    String cor = resultSet.getString("cor");
                    double quilometragem = resultSet.getDouble("quilometragem");
                    String tipoCombustivel = resultSet.getString("tipo_combustivel");
                    int cilindradas = resultSet.getInt("linguagem_principal");

                    VeiculoAbstrato veiculo;
                    if (cilindradas > 0) {
                        veiculo = new Moto(marca, modelo, ano, cor, resultSet.getString("chassi"), quilometragem, cilindradas);
                    } else {
                        veiculo = new Carro(marca, modelo, ano, cor, resultSet.getString("chassi"), quilometragem, tipoCombustivel);
                    }

                    vas.add(veiculo);
                }

                statement.close();

                if (vas.isEmpty()) {
                    throw new VeiculoNaoEncontrado();
                } else {
                    return vas;
                }
            }
        }

        @Override
        public VeiculoAbstrato consultar(String chassi) throws SQLException, VeiculoNaoEncontrado{
            String sql = "SELECT * FROM VeiculoAbstrato WHERE chassi=?";
            PreparedStatement statement = this.conexao.prepareStatement(sql);
            statement.setString(1, chassi);
            ResultSet resultSet = statement.executeQuery();
            VeiculoAbstrato veiculo = null;

            if (resultSet.next()){
                String marca = resultSet.getString("marca");
                String modelo = resultSet.getString("modelo");
                int ano = resultSet.getInt("ano");
                String cor = resultSet.getString("cor");
                double quilometragem = resultSet.getDouble("quilometragem");
                String tipoCombustivel = resultSet.getString("tipo_combustivel");
                int cilindradas = resultSet.getInt("cilindradas");

                if (cilindradas > 0){
                    veiculo = new Moto(marca, modelo, ano, cor, chassi, quilometragem, cilindradas);
                } else{
                    veiculo = new Carro(marca, modelo, ano, cor, chassi, quilometragem, tipoCombustivel);
                }
            } else{
                statement.close();
                throw new VeiculoNaoEncontrado();
            }

            statement.close();
            return veiculo;
        }

        @Override
        public void remover(String chassi) throws SQLException, VeiculoNaoEncontrado{
            String sql  = "DELETE FROM VeiculoAbstrato WHERE chassi=?";
            PreparedStatement statement = this.conexao.prepareStatement(sql);
            statement.setString(1, chassi);
            if (statement.executeUpdate() == 0){
                statement.close();
                throw new VeiculoNaoEncontrado();
            }
            statement.close();
        }

        @Override
        public void atualizar(VeiculoAbstrato va) throws SQLException, VeiculoNaoEncontrado {
            String sql = "UPDATE VeiculoAbstrato SET marca=?, modelo=?, ano=?, cor=?, quilometragem=?, tipo_combustivel=?, cilindradas=? WHERE chassi=?";
            PreparedStatement statement = this.conexao.prepareStatement(sql);
            statement.setString(1, va.getMarca());
            statement.setString(2, va.getModelo());
            statement.setInt(3, va.getAno());
            statement.setString(4, va.getCor());
            statement.setDouble(5, va.getQuilometragem());

            if (va instanceof Moto) {
                statement.setInt(6, ((Moto) va).getCilindradas());
                statement.setNull(7, java.sql.Types.VARCHAR);
            } else {
                statement.setNull(6, java.sql.Types.INTEGER);
                statement.setString(7, ((Carro) va).getTipoCombustivel());
            }
            
            statement.setString(8, va.getChassi());
            
            int linhasAfetadas = statement.executeUpdate();
            statement.close();

            if (linhasAfetadas == 0) {
                throw new VeiculoNaoEncontrado();
            }
        }


        @Override
        public ArrayList<VeiculoAbstrato> listar() throws SQLException {
            ArrayList<VeiculoAbstrato> veiculos = new ArrayList<>();
            String sql = "SELECT * FROM VeiculoAbstrato";
            PreparedStatement statement = this.conexao.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String marca = resultSet.getString("marca");
                String modelo = resultSet.getString("modelo");
                int ano = resultSet.getInt("ano");
                String cor = resultSet.getString("cor");
                String chassi = resultSet.getString("chassi");
                Double quilometragem = resultSet.getDouble("quilometragem");
                String tipoCombustivel = resultSet.getString("tipo_combustivel");
                int cilindradas = resultSet.getInt("cilindradas");

                VeiculoAbstrato veiculo;
                if (cilindradas > 0) {
                    veiculo = new Moto(marca, modelo, ano, cor, chassi, quilometragem, cilindradas);
                } else {
                    veiculo = new Carro(marca, modelo, ano, cor, chassi, quilometragem, tipoCombustivel);
                }

                veiculos.add(veiculo);
            }

            statement.close();
            return veiculos;
        }

        @Override
        public void SomDoMotor() throws SQLException, NaoEMoto, VeiculoNaoEncontrado{
            
        }
    }
   

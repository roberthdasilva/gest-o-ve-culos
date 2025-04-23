package excecao;

public class RepositorioVazio extends Exception {
    public RepositorioVazio() {
        super("Repositório vazio! Nenhum veículo cadastrado.");
    }
}
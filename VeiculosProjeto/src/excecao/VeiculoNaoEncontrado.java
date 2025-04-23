package excecao;

public class VeiculoNaoEncontrado extends Exception {
    public VeiculoNaoEncontrado() {
        super("Veículo não encontrado no repositório.");
    }
}
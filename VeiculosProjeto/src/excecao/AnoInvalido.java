package excecao;

public class AnoInvalido extends Exception {
    public AnoInvalido() {
        super("Ano inválido! Deve ser entre 1886 (invenção do carro) e o ano atual.");
    }
}
package excecao;

public class CilindradasInvalida extends Exception {
    public CilindradasInvalida() {
        super("Cilindradas inválidas! Deve ser entre 50cc e 3000cc.");
    }
}
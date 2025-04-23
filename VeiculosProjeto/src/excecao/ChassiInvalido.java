package excecao;

public class ChassiInvalido extends Exception {
    public ChassiInvalido() {
        super("Chassi inválido! Deve ser um número positivo.");
    }
}
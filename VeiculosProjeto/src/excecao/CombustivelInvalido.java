package excecao;

public class CombustivelInvalido extends Exception {
    public CombustivelInvalido() {
        super("Combustível inválido! Use: Gasolina, Álcool, Diesel, Elétrico ou Flex.");
    }
}
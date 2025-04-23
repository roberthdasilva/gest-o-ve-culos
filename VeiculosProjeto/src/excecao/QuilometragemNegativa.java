package excecao;

public class QuilometragemNegativa extends Exception {
    public QuilometragemNegativa() {
        super("Quilometragem não pode ser negativa!");
    }
}
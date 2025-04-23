package dados;

public class Moto extends VeiculoAbstrato{
    private int cilindradas;

    public Moto(String marca, String modelo, int ano, String cor, String chassi, double quilometragem, int cilindradas) {
        super(marca, modelo, ano, cor, chassi, quilometragem);
        this.cilindradas = cilindradas;
    }

    public int getCilindradas(){
        return this.cilindradas;
    } 
    
    public void setCilindradas(int cilindradas){
        this.cilindradas = cilindradas;
    }
    
    @Override
    public void SomDoMotor(){
        System.out.println("Som típico de Moto. Vruuuum!");
    }
}

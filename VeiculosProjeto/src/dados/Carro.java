package dados;

public class Carro extends VeiculoAbstrato{
    String tipoCombustivel;

    public Carro(String marca, String modelo, int ano, String cor, String chassi, double quilometragem, String tipoCombustivel) {
        super(marca, modelo, ano, cor, chassi, quilometragem);
        this.tipoCombustivel = tipoCombustivel;
    }
    
    public String getTipoCombustivel(){
        return this.tipoCombustivel;
    }
    
    public void setTipoCombustivel(String tipoCombustivel){
        this.tipoCombustivel = tipoCombustivel;
    }
    
    @Override
    public void SomDoMotor(){
        System.out.println("Som típico de carro. Vruum Vruum");
    }
}

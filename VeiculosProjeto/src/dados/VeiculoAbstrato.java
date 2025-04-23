package dados;

public abstract class VeiculoAbstrato{
    private String marca;
    private String modelo;
    private int ano;
    private String cor;
    private String chassi;
    private double quilometragem;
    
    public VeiculoAbstrato()  {
    }
    
    public VeiculoAbstrato(String marca, String modelo, int ano, String cor,  String chassi, double quilometragem){
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.cor = cor;
        this.chassi = chassi;
        this.quilometragem = quilometragem;
    }   
    
    public String getMarca() {
        return this.marca;
    }
    
    public String getModelo() {
        return this.modelo;
    }
    
    public int getAno() {
        return this.ano;
    }
    
    public String getCor() {
        return this.cor;
    }
    public double getQuilometragem() {
        return this.quilometragem;
    }
    public String getChassi(){
        return this.chassi;
    }
    
    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    
    public void setAno(int ano) {
        this.ano = ano;
    }
    
    public void setCor(String cor) {
        this.cor = cor;
    }
    
    public void setQuilometragem(double quilometragem) {
        this.quilometragem = quilometragem;
    }
    public void setChassi(String chassi){
        this.chassi = chassi;
    }
    
    public abstract void SomDoMotor();
}


public class Vaga {
    private int numero;
    private String tipoVeiculo;
    private String  status;
    private String placaVeiculo;

    // Criar um metodo inicial para quando um novo objeto for criado
    public Vaga (int numero, String tipoVeiculo){
        this.numero = numero;
        this.tipoVeiculo = tipoVeiculo;
        this.status = "livre";
        this.placaVeiculo = "";
    }
    
    //Get para acessar os atributos fora da classe
    public int getNumero() {
        return numero;
    }

    public String getTipoVeiculo() {
        return tipoVeiculo;
    }

    public String getStatus() {
        return status;
    }
    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    // Metodo para quando um carro for registrado na vaga
    public void ocuparVaga (String placa){
        this.status = "ocupada";
        this.placaVeiculo = placa;
    }
    // Metodo para quando um carro sair da vaga
    public void liberarVaga () {
        this.status = "livre";
        this.placaVeiculo = "";
    }   
}

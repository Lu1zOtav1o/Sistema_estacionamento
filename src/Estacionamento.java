import java.util.ArrayList;
import java.util.List;

public class Estacionamento {
        private List<Vaga> vagas;
    
    //Construtor para estacionamento, com vagas que será uma ArrayList
    public Estacionamento() {
        vagas = new ArrayList<>();
    }
    public List<Vaga> getVagas() {
        return new ArrayList<>(vagas); 
    }
    public void setVagas(List<Vaga> vagas) {
        this.vagas = vagas;
    }

    // Metodo para cadastrar uma vaga
    public void cadastrarVaga(int numero, String tipoVeiculo) {
        for (Vaga vaga : vagas){
            if (vaga.getNumero() == numero){
                System.out.println("O número da vaga já é de uma vaga existente, digite um número diferente.");
                return;
            }
        }
        if (tipoVeiculo.equalsIgnoreCase("moto") || 
        tipoVeiculo.equalsIgnoreCase("carro") || 
        tipoVeiculo.equalsIgnoreCase("caminhão")) {
            Vaga vaga = new Vaga (numero, tipoVeiculo);
            vagas.add(vaga);
            System.out.println("Vaga cadastrada com sucesso"); 
    }
    else {
        System.out.println("Digite um tipo de veículo válido!");
    }
}
    //Registrar entrada dos veiculos e saber se a vaga esta ocupada
public void registrarEntrada(int numero, String placaVeiculo, String tipoEntrada){
    for (Vaga vaga : vagas){
        if(vaga.getNumero() == numero){
            vaga.ocuparVaga(placaVeiculo);
            System.out.println("Veículo registrado com sucesso na vaga " + numero + ".");
            return;
        }
        
    }
    System.out.println("Vaga não encontrada ou já ocupada.");
}
//registrar saida de um veiculo, liberando a vaga
public void registrarSaida(int numero) {
    for (Vaga vaga : vagas) {
        if (vaga.getNumero() == numero && vaga.getStatus().equals("ocupada")) {
            vaga.liberarVaga();
            System.out.println("Saída registrada e vaga " + numero + " liberada.");
                return;
        }
    }
System.out.println("Vaga não encontrada ou já está livre.");
}
//consultar as vagas que estao disponiveis
public void consultarVagasLivres(String tipoVeiculo) {
    if(tipoVeiculo.equalsIgnoreCase("carro") || tipoVeiculo.equalsIgnoreCase("moto") || tipoVeiculo.equalsIgnoreCase("caminhão")) {
        System.out.println("Vagas disponíveis para " + tipoVeiculo + ":");
        boolean encontrouVaga = false; // Variável para verificar se encontrou alguma vaga
        for (Vaga vaga : vagas) {
            if (vaga.getTipoVeiculo().equalsIgnoreCase(tipoVeiculo) && vaga.getStatus().equals("livre")) {
                System.out.println("Vaga número " + vaga.getNumero());
                encontrouVaga = true; // Marca que encontrou ao menos uma vaga
            }
        }
        
        if (!encontrouVaga) {
            System.out.println("Não há vagas disponíveis para " + tipoVeiculo + ".");
        }
    } else {
        System.out.println("Não existe um veículo com esse nome.");
    }
}


}

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Estacionamento estacionamento = new Estacionamento();
        List<Vaga> vagas = carregar(estacionamento);

        while (true) {
            System.out.println("\n----------------------------------------------------------------");
            System.out.println("Sistema de Gerenciamento de Estacionamento, escolha uma opção:");
            System.out.println("1. Cadastrar Vaga");
            System.out.println("2. Registrar Entrada de Veículo");
            System.out.println("3. Registrar Saída de Veículo");
            System.out.println("4. Consultar Vagas Disponíveis por Tipo de Veículo");
            System.out.println("5. Listar Todas as Vagas");
            System.out.println("0. Sair");

            int opcao = 7;
            try {
                opcao = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Digite um número válido!");
                scanner.nextLine(); // Limpar entrada inválida
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.print("Número da vaga: ");
                    int numero = 0;
                    try {
                        numero = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Tipo de veículo (carro, moto, caminhão): ");
                        String tipoVeiculo = scanner.nextLine();
                        estacionamento.cadastrarVaga(numero, tipoVeiculo);
                        vagas = estacionamento.getVagas();
                        salvar(vagas);
                    } catch (Exception e) {
                        System.out.println("Erro ao cadastrar vaga. Digite um número válido!");
                        scanner.nextLine(); // Limpar entrada inválida
                    }
                    break;

                case 2:
                    System.out.print("Digite o número da vaga: ");
                    int numeroVaga = 9;
                    try {
                        numeroVaga = scanner.nextInt();
                        scanner.nextLine();
                    } catch (Exception e) {
                        System.out.println("Digite um número inteiro.");
                        scanner.nextLine(); // Limpar entrada inválida
                        break;
                    }

                    boolean entradaRegistrada = false; // Controle para evitar fluxo indesejado
                    for (Vaga vaga : estacionamento.getVagas()) {
                        if (vaga.getNumero() == numeroVaga && vaga.getStatus().equals("livre")) {
                            String tipoVeiculo = "";
                            System.out.println("Digite o tipo de veículo que deseja cadastrar:");
                            try {
                                tipoVeiculo = scanner.nextLine();
                            } catch (Exception e) {
                                System.out.println("Tipo de veículo inválido.");
                                break;
                            }

                            if (!vaga.getTipoVeiculo().equalsIgnoreCase(tipoVeiculo)) {
                                System.out.println("Digite o tipo de veículo igual ao que a vaga aceita para confirmação!");
                                break;
                            }

                            System.out.println("Digite a placa do veículo:");
                            String placaVeiculo;
                            try {
                                placaVeiculo = scanner.nextLine();
                            } catch (Exception e) {
                                System.out.println("Digite uma placa válida!");
                                break;
                            }
                            estacionamento.registrarEntrada(numeroVaga, placaVeiculo, tipoVeiculo);
                            entradaRegistrada = true; // Marca que a entrada foi registrada
                            vagas = estacionamento.getVagas();
                            salvar(vagas);
                            break;
                        } else {
                            System.out.println("Vaga não existente ou ocupada!");
                        }
                    }

                    if (!entradaRegistrada) {
                        System.out.println("Não foi possível registrar a entrada.");
                    }
                    break;

                case 3:
                    System.out.print("Número da vaga para liberar: ");
                    try {
                        int numeroSaida = scanner.nextInt();
                        estacionamento.registrarSaida(numeroSaida);
                        vagas = estacionamento.getVagas();
                        salvar(vagas);
                    } catch (Exception e) {
                        System.out.println("Erro ao registrar saída. Digite um número válido!");
                        scanner.nextLine(); 
                    }
                    break;

                case 4:
                String tipoConsulta;
                    System.out.print("Tipo de veículo (carro, moto, caminhão): ");
                    try {
                     tipoConsulta = scanner.nextLine();
                    } catch (Exception e) {
                        System.out.println("Digite um tipo de veículo válido!");
                        break;
                    }
                    estacionamento.consultarVagasLivres(tipoConsulta);
                    break;

                case 5:
                System.out.println("Lista de todas as vagas:");
        for (Vaga vaga : vagas) {
        System.out.println("Vaga: " + vaga.getNumero() + " - Status: " + vaga.getStatus() + " - Tipo de veículo: " + vaga.getTipoVeiculo());
        if ("ocupada".equals(vaga.getStatus())){
            System.out.println("Placa do veículo: " + vaga.getPlacaVeiculo() + "\n");
        }
    }
                    break;

                case 0:
                    System.out.println("Saindo do sistema.");
                    scanner.close();
                    vagas = estacionamento.getVagas();
                    salvar(vagas);
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
    private static void salvar(List<Vaga> vagas) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("estacionamento.bin"))) {
            oos.writeObject(vagas); // Serializa e salva a lista de vagas
        } catch (IOException e) {
            System.out.println("Erro ao salvar as vagas: " + e.getMessage());
        }
    }
    
    private static List<Vaga> carregar(Estacionamento estacionamento) {
        List<Vaga> vagas;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("estacionamento.bin"))) {
            vagas = (ArrayList<Vaga>) ois.readObject();
            estacionamento.setVagas(vagas); 
        } catch (IOException | ClassNotFoundException e) {
            vagas = new ArrayList<>(); // Cria lista vazia se o arquivo não existir ou ocorrer erro
            estacionamento.setVagas(vagas);  // Garante que a lista seja inicializada no Estacionamento
        }
        return vagas;
    }

}

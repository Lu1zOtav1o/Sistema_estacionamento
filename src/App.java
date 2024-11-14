import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Estacionamento estacionamento = new Estacionamento();

    while (true) {
        System.out.println("\nSistema de Gerenciamento de Estacionamento, escolha uma opção:");
        System.out.println("1. Cadastrar Vaga");
        System.out.println("2. Registrar Entrada de Veículo");
        System.out.println("3. Registrar Saída de Veículo");
        System.out.println("4. Consultar Vagas Disponíveis por Tipo de Veículo");
        System.out.println("5. Listar Todas as Vagas");
        System.out.println("0. Sair");
        
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                System.out.print("Número da vaga: ");
                int numero = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Tipo de veículo (carro, moto, caminhão): ");
                    String tipoVeiculo = scanner.nextLine();
                    estacionamento.cadastrarVaga(numero, tipoVeiculo);
                    break;

                case 2:
                    System.out.print("Número da vaga para ocupar: ");
                    int numeroEntrada = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Placa do veículo: ");
                    String placa = scanner.nextLine();
                    estacionamento.registrarEntrada(numeroEntrada, placa);
                    break;

                case 3:
                    System.out.print("Número da vaga para liberar: ");
                    int numeroSaida = scanner.nextInt();
                    estacionamento.registrarSaida(numeroSaida);
                    break;

                case 4:
                    System.out.print("Tipo de veículo (carro, moto, caminhão): ");
                    String tipoConsulta = scanner.nextLine();
                    estacionamento.consultarVagasLivres(tipoConsulta);
                    break;

                case 5:
                    estacionamento.listarTodasVagas();
                    break;

                case 0:
                    System.out.println("Saindo do sistema.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
}
}
}
}
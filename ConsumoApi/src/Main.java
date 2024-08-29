import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException, SQLException {
        Scanner scanner = new Scanner(System.in);
        Connection connect = null;
        connect = DB.connect();
        System.out.println("Conexão bem sucedida!");
        Gson gson = new Gson();

        while (true) {
            System.out.println("\n1. Consultar CEP");
            System.out.println("2. Listar CEPs Consultados");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (opcao) {
                    case 1 -> {
                        var consomeApi = new ConsomeApi();
                        System.out.print("Cep: ");
                        String cep = scanner.nextLine();
                        String json = consomeApi.retornaEnd(cep);
                        Endereco endereco = gson.fromJson(json, Endereco.class);

                        if (endereco.getCep() != null) {
                            EnderecoBd endBd = new EnderecoBd();
                            endBd.AdicionarEnd(endereco);
                            registrarLog(endereco, consomeApi);
                            System.out.println("Endereço listado no arquivo: log.txt!");
                        } else {
                            System.out.println("CEP inválido!");
                        }
                    }
                    case 2 -> {
                        List<Endereco> enderecos = EnderecoBd.ListarEnd();
                        enderecos.forEach(System.out::println);
                    }
                    case 3 -> {
                        System.out.println("Saindo...");
                        return;
                    }
                    default -> System.out.println("Opção inválida.");
                }
            } catch (SQLException e) {
                System.err.println("Erro: " + e.getMessage());
            }
        }
    }

    private static void registrarLog(Endereco endereco, ConsomeApi consomeApi) throws IOException, InterruptedException {
        String dataHoraJson = consomeApi.DataeHora();
        Gson gson = new Gson();

        DataHora dataHora = gson.fromJson(dataHoraJson, DataHora.class);

        try (FileWriter fileWriter = new FileWriter("log.txt", true);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.println("Data e Hora: " + dataHora.getDatetime() + " " + dataHora.getTimezone());
            printWriter.println("CEP: " + endereco.getCep());
            printWriter.println("Rua: " + endereco.getRua());
            printWriter.println("Bairro: " + endereco.getBairro());
            printWriter.println("Cidade: " + endereco.getCidade());
            printWriter.println("Estado: " + endereco.getEstado());
            printWriter.println("----------");
        }
    }
}

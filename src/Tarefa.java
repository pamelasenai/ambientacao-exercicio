import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Tarefa {
    private ArrayList<String> listaTarefas = new ArrayList<>();

    private void adicionar(){
        Scanner scanner = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        String continuar;

        do {
            System.out.print("Digite uma nova tarefa: ");
            String tarefa = scanner.nextLine();
            String novaTarefa = "[ ] " + tarefa;
            listaTarefas.add(novaTarefa);

            System.out.print("Deseja adicionar uma nova tarefa? (s / n) ");
            continuar = scanner2.next();
        } while (continuar.equals("s"));
        exibirTodasTarefas();
    }

    private void remover(){
        Scanner scanner = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        String continuar;

        do {
            System.out.print("Informe o número da tarefa a ser excluida: ");
            int tarefa = scanner.nextInt();
            int indexTarefa = tarefa - 1;

            if (indexTarefa < 0 || indexTarefa > listaTarefas.size()){
                System.out.println("Item inexistente!");
                break;
            }

            listaTarefas.remove(indexTarefa);

            System.out.print("Deseja excluir outra tarefa? (s / n) ");
            continuar = scanner2.next();
        } while (continuar.equals("s"));
        exibirTodasTarefas();
    }

    private void concluir(){
        Scanner scanner = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        String continuar;
        do {
            System.out.print("Informe a tarefa que foi concluida: ");
            int tarefa = scanner.nextInt();
            int indexTarefa = tarefa - 1;

            if (indexTarefa < 0 || indexTarefa > listaTarefas.size()){
                System.out.println("Item inexistente!");
                break;
            }

            String tarefaConcluida = listaTarefas.get(indexTarefa).replace("[ ] ", "[X] ");
            listaTarefas.set(indexTarefa, tarefaConcluida);

            System.out.print("Deseja concluir outra tarefa? (s / n) ");
            continuar = scanner2.next();
        } while (continuar.equals("s"));
        exibirTodasTarefas();
    }

    private void exibirTodasTarefas(){
        System.out.println("Sua lista de tarefas no momento está assim: ");
        exibirTarefas(listaTarefas);
    }

    private void filtrarTarefas(){
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> tarefasPendentes = new ArrayList<>(listaTarefas);
        ArrayList<String> tarefasconcluidas = new ArrayList<>(listaTarefas);

        tarefasPendentes.removeIf( s -> !s.contains("[ ]"));
        tarefasconcluidas.removeIf( s -> !s.contains("[X]"));

        System.out.print("Deseja filtrar as tarefas por concluidas ou pendentes? (concluidas / pendentes) ");
        String filtro = scanner.next();

        switch (filtro){
            case "concluidas":
                exibirTarefas(tarefasconcluidas);
                break;
            case "pendentes":
                exibirTarefas(tarefasPendentes);
                break;
            default:
                System.out.println("Filtro invalido.");
                exibirTodasTarefas();
        }
    }

    private void exibirTarefas(ArrayList<String> lista){
        for (int i = 0; i != lista.size(); i++){
            System.out.println( i+1 + " - " + lista.get(i));
        }
    }

    private void salvarArquivo() {
        String nomeDoArquivo = "to-do-list.txt";
        Path caminhoRaiz = Paths.get("").toAbsolutePath();
        Path path = caminhoRaiz.resolve(nomeDoArquivo);

        try {
            if (Files.notExists(path)) {
                Files.createFile(path);
            }
            String texto = "";
            for (int i = 0; i != listaTarefas.size(); i++){
                String item = i+1 + " - " + listaTarefas.get(i) + "\n";
                String concat = texto.concat(item);
                texto = concat;
            }
            Files.writeString(path, texto);
            System.out.println("Arquivo criado com sucesso!");
            abrirArquivo(path.toFile());
        } catch (IOException e){
            System.err.println("Erro ao criar e abrir o arquivo: " + e.getMessage());
        }
    }

    private void abrirArquivo(File arquivo) {
        if(Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.open(arquivo);
            } catch (IOException e) {
                System.err.println("Erro ao abrir o arquivo: " + e.getMessage());
            }
        } else {
            System.out.println("A abertura automática de arquivos não é suportada neste ambiente.");
        }
    }

    public void iniciarToDoList() {
        Scanner scanner = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        String continuar;
        do {
            this.exibirTodasTarefas();

            System.out.print(
                    "Ações permitidas\n" +
                    "1 - adicionar tarefa \n" +
                    "2 - remover tarefa \n" +
                    "3 - concluir tarefa \n" +
                    "4 - filtrar tarefa \n" +
                    "5 - salvar lista de tarefas em arquivo txt \n" +
                    "Informe qual ação você deseja executar: "
            );
            int acao = scanner.nextInt();
            switch (acao) {
                case 1:
                    this.adicionar();
                    break;
                case 2:
                    this.remover();
                    break;
                case 3:
                    this.concluir();
                    break;
                case 4:
                    this.filtrarTarefas();
                    break;
                case 5:
                    this.salvarArquivo();
                    break;
                default:
                    System.out.println("Opção invalida.");
            }

            System.out.print("Deseja fechar o programa? (s / n) ");
            continuar = scanner2.next();
        } while (continuar.equals("n"));
    }
}

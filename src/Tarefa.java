import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tarefa {
    private List<String> listaTarefas = new ArrayList<>();
    private final Scanner entrada = new Scanner(System.in);

    private void adicionar(){
        String tarefa;

        do {
            System.out.print("\nDigite uma nova tarefa ou N para sair: ");
            tarefa = entrada.nextLine();
            if (!tarefa.equals("n")){
                String novaTarefa = "[ ] " + tarefa;
                listaTarefas.add(novaTarefa);
            }
        } while (!tarefa.equals("n"));

        exibirTodasTarefas();
    }

    private void remover(){
        int tarefa;

        do {
            System.out.print("\nInforme o número da tarefa a ser excluída ou 0 para sair: ");
            tarefa = entrada.nextInt();
            int indiceTarefa = tarefa - 1;

            if (indiceTarefa < 0 || indiceTarefa > listaTarefas.size()){
                System.out.println("\nItem inexistente!");
                continue;
            }

            if (tarefa != 0){
                listaTarefas.remove(indiceTarefa);
                System.out.println("\nTarefa na posição " + tarefa + " removida da lista!");
                exibirTodasTarefas();
            }
        } while (tarefa != 0);

        exibirTodasTarefas();
    }

    private void concluir(){
        int tarefa;

        do {
            System.out.print("\nInforme a tarefa a ser concluída ou 0 para sair: ");
            tarefa = entrada.nextInt();
            int indiceTarefa = tarefa - 1;

            if (indiceTarefa < 0 || indiceTarefa > listaTarefas.size()){
                System.out.println("\nItem inexistente!");
                break;
            }

            if (tarefa != 0) {
                String tarefaConcluida = listaTarefas.get(indiceTarefa).replace("[ ] ", "[X] ");
                listaTarefas.set(indiceTarefa, tarefaConcluida);
                System.out.println("\nTarefa na posição " + tarefa + " concluida.");
                exibirTodasTarefas();
            }
        } while (tarefa != 0);

        exibirTodasTarefas();
    }

    private void exibirTodasTarefas(){
        System.out.println("\nSua lista de tarefas no momento está assim: ");
        for(int contador = 0; contador < this.listaTarefas.size(); contador ++){
            String texto = (contador + 1) + "- "+ this.listaTarefas.get(contador);
            System.out.println(texto);
        }
    }

    private void filtrarTarefas(){
        List<String> tarefas = new ArrayList<>(listaTarefas);

        System.out.print("\nDeseja filtrar as tarefas por concluídas ou pendentes? (1 - concluídas / 2 - pendentes) ");
        int filtro = entrada.nextInt();

        switch (filtro){
            case 1:
                tarefas.removeIf( tarefa -> !tarefa.contains("[X]"));
                exibirTarefas(tarefas);
                break;
            case 2:
                tarefas.removeIf( tarefa -> !tarefa.contains("[ ]"));
                exibirTarefas(tarefas);
                break;
            default:
                System.out.println("\nFiltro invalido.");
                exibirTodasTarefas();
        }
    }

    private void exibirTarefas(List<String> lista){
        for (String item : lista){
            System.out.println(item);
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
            for (String tarefa : listaTarefas){
                texto = texto.concat(tarefa + "\n");
            }
            Files.writeString(path, texto);
            System.out.println("\nArquivo criado com sucesso!");
            abrirArquivo(path.toFile());
        } catch (IOException e){
            System.err.println("\nErro ao criar e abrir o arquivo: " + e.getMessage());
        }
    }

    private void abrirArquivo(File arquivo) {
        if(Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.open(arquivo);
            } catch (IOException e) {
                System.err.println("\nErro ao abrir o arquivo: " + e.getMessage());
            }
        } else {
            System.out.println("\nA abertura automática de arquivos não é suportada neste ambiente.");
        }
    }

    public void iniciarToDoList() {
        String continuar;
        do {
            this.exibirTodasTarefas();
            System.out.print(
                    "\n----------------------------------------------\n" +
                    "| Ações permitidas                           |\n" +
                    "| 1 - adicionar tarefa                       |\n" +
                    "| 2 - remover tarefa                         |\n" +
                    "| 3 - concluir tarefa                        |\n" +
                    "| 4 - filtrar tarefa                         |\n" +
                    "| 5 - salvar lista de tarefas em arquivo txt |\n" +
                    "---------------------------------------------\n" +
                    " \nInforme qual ação você deseja executar: "
            );
            byte acao = entrada.nextByte();

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
                    System.out.println("\nOpção invalida.");
            }

            System.out.print("\nDeseja fechar o programa? (s / n) ");
            continuar = entrada.next();
        } while (continuar.equals("n"));

        entrada.close();
    }
}

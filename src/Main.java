public class Main {
    public static void main(String[] args) {
        Calculadora calculadora = new Calculadora();
        Tarefa tarefa = new Tarefa();
        Forca forca = new Forca();
        Banco banco = new Banco();
        Imc imc = new Imc();

        System.out.println("Módulo 1 - Semana 02 - Exercícios!");
        System.out.println("\nEx 1 - Calculadora Simples \n");
        calculadora.iniciarCalculadora();

        System.out.println("\nEx 2 - Lista de Tarefas \n");
        tarefa.iniciarToDoList();

        System.out.println("\nEx 3 - Jogo da Forca");
        forca.adicionarPalavras("ferramenta");
        forca.adicionarPalavras("instrumento");
        forca.adicionarPalavras("conhecimento");
        forca.iniciarJogo();

        System.out.println("\nEx 4 -  Simulador de Banco");
        banco.iniciar();

        System.out.println("\nEx 5 -  Calculadora de IMC (Índice de Massa Corporal)");
        imc.iniciar();
    }
}
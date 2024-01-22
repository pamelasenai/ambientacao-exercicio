import java.util.*;

public class Forca {
    private int errosAcumulados = 0;
    private int maximoErros = 6;
    private final List<String> listaPalavras = Arrays.asList("tecnologia", "computador", "programacao", "desenvolvedor");
    private ArrayList<String> palavraOcultada = new ArrayList<>();
    private String palavraEscolhida = this.obterPalavraAleatoria();
    private ArrayList<String> letrasEscolhidas = new ArrayList<>();
    private boolean venceu = false;
    private String novaPartida = "";

    public void adicionarPalavras(String novaPalavra) {
        listaPalavras.add(novaPalavra);
        System.out.println("Palavra adicionada com sucesso.");
    }

    private String obterPalavraAleatoria() {
        Random random = new Random();
        int indexAleatorio = random.nextInt(listaPalavras.size());
        String palavra = listaPalavras.get(indexAleatorio);
        for (int i = 0; i < palavra.length(); i++){
            this.palavraOcultada.add("_ ");
        }
        return palavra;
    }

    private void verificarLetra(String letra) {
        this.letrasEscolhidas.add(letra);
        if (!palavraEscolhida.contains(letra)){
            errosAcumulados = errosAcumulados + 1;
            return;
        }
        List<String> arrayPalavraEscolhida = Arrays.asList(this.palavraEscolhida.split(""));

        for (int i = 0; i < palavraEscolhida.length(); i++){
            if (letra.equals(arrayPalavraEscolhida.get(i))) {
                this.palavraOcultada.set(i, letra.toUpperCase() + " ");
            };
        }
        boolean contains = !this.palavraOcultada.contains("_ ");
        this.venceu = contains;
    }

    private void obterForca(){
        switch (errosAcumulados){
            case 1:
                obterForcaErro1();
                break;
            case 2:
                obterForcaErro2();
                break;
            case 3:
                obterForcaErro3();
                break;
            case 4:
                obterForcaErro4();
                break;
            case 5:
                obterForcaErro5();
                break;
            case 6:
                obterForcaErro6();
                break;
            default:
                obterForcaErro0();
        }
        if (this.errosAcumulados == this.maximoErros) {
            Scanner scanner = new Scanner(System.in);
            System.out.println(this.palavraEscolhida.toUpperCase());
            System.out.println("FORCA! Você perdeu.");
            this.iniciarNovaPartida();
        } else {
            System.out.println(String.join("", this.palavraOcultada));
        }
    }

    private void obterForcaErro0() {
        System.out.println(
                "  ______\n" +
                " |      |\n" +
                " |\n" +
                " |\n" +
                " |\n" +
                "---   \n" +
                "Letras escolhidas: " + this.letrasEscolhidas
        );
    }
    private void obterForcaErro1() {
        System.out.println(
                "  ______\n" +
                " |      |\n" +
                " |      O \n" +
                " |\n" +
                " |\n" +
                "---   \n" +
                "Letras escolhidas: " + this.letrasEscolhidas
        );
    }

    private void obterForcaErro2() {
        System.out.println(
                "  ______\n" +
                " |      |\n" +
                " |      O \n" +
                " |     /\n" +
                " |\n" +
                "---   \n" +
                "Letras escolhidas: " + this.letrasEscolhidas
        );
    }
    private void obterForcaErro3() {
        System.out.println(
                "  ______\n" +
                " |      |\n" +
                " |      O \n" +
                " |     /|\n" +
                " |\n" +
                "---   \n" +
                "Letras escolhidas: " + this.letrasEscolhidas
        );
    }
    private void obterForcaErro4() {
        System.out.println(
                "  ______\n" +
                " |      |\n" +
                " |      O \n" +
                " |     /|\\ \n" +
                " |\n" +
                "---   \n" +
                "Letras escolhidas: " + this.letrasEscolhidas
        );
    }
    private void obterForcaErro5() {
        System.out.println(
                "  ______\n" +
                " |      |\n" +
                " |      O \n" +
                " |     /|\\ \n" +
                " |     /\n" +
                "---   \n" +
                "Letras escolhidas: " + this.letrasEscolhidas
        );
    }
    private void obterForcaErro6() {
        System.out.println(
                "  ______\n" +
                " |      |\n" +
                " |      O \n" +
                " |     /|\\ \n" +
                " |     / \\ \n" +
                "--- \n" +
                "Letras escolhidas: " + this.letrasEscolhidas
        );
    }

    public void iniciarJogo() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Iniciar jogo...");
        do {
            this.obterForca();
            if (this.venceu){
                System.out.println("PARABÉNS, você venceu!!");
                break;
            }
            System.out.println("\nMáximo de erros possíveis: " + this.maximoErros);
            System.out.println("Erros acumulados: " + this.errosAcumulados);
            System.out.print("\nDigite uma letra para verificar se a palavra contém a letra: ");
            String letra = String.valueOf(scanner.next().toLowerCase().charAt(0));
            this.verificarLetra(letra);
            if (this.errosAcumulados == this.maximoErros) {
                this.obterForca();
            }
        } while (this.errosAcumulados < this.maximoErros);
        this.iniciarNovaPartida();
    }

    private void iniciarNovaPartida() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Deseja jogar uma nova partida? (s / n) ");
        this.novaPartida = scanner.next();

        if(this.novaPartida.equals("s")) {
            this.errosAcumulados = 0;
            this.palavraOcultada = new ArrayList<>();
            this.letrasEscolhidas = new ArrayList<>();
            this.venceu = false;
            this.palavraEscolhida = this.obterPalavraAleatoria();
            this.iniciarJogo();
        };
    }
}

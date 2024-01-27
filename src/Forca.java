import java.util.*;

public class Forca {
    private byte errosAcumulados = 0;
    private final byte MAXIMOERROS = 6;
    private List<String> listaPalavras = new ArrayList<>();
    private List<String> palavraOcultada = new ArrayList<>();
    private String palavraEscolhida = this.obterPalavraAleatoria();
    private List<String> letrasEscolhidas = new ArrayList<>();
    private boolean venceu = false;

    public void adicionarPalavras(String novaPalavra) {
        listaPalavras.add(novaPalavra);
        System.out.println("Palavra " + novaPalavra.toUpperCase() + " adicionada a lista com sucesso.\n");
    }

    private void popularListaPalavras() {
        listaPalavras.add("tecnologia");
        listaPalavras.add("computador");
        listaPalavras.add("programacao");
        listaPalavras.add("desenvolvedor");
    }

    private String obterPalavraAleatoria() {
        Random random = new Random();
        this.popularListaPalavras();
        int indexAleatorio = random.nextInt(this.listaPalavras.size());
        String palavra = this.listaPalavras.get(indexAleatorio);

        for (int i = 0; i < palavra.length(); i++){
            this.palavraOcultada.add("_ ");
        }

        return palavra;
    }

    private void verificarLetra(String letra) {
        this.letrasEscolhidas.add(letra);

        if (!palavraEscolhida.contains(letra)){
            this.errosAcumulados = (byte) (this.errosAcumulados + 1);
            return;
        }

        List<String> arrayPalavraEscolhida = Arrays.asList(this.palavraEscolhida.split(""));
        for (int i = 0; i < palavraEscolhida.length(); i++){
            if (letra.equals(arrayPalavraEscolhida.get(i))) {
                this.palavraOcultada.set(i, letra.toUpperCase() + " ");
            }
        }

        this.venceu = !this.palavraOcultada.contains("_ ");
    }

    private void obterForca(){
        String linha1 = "\n";
        String linha2 = "  ______\n";
        String linha3 = " |      |\n";
        String linha4 = this.errosAcumulados >= 1 ? " |      O \n" : " |\n";
        String linha5 = " |\n";
        String linha6 = " |\n";
        String linha7 = "---   \n";
        String letras = "Letras escolhidas: " + this.letrasEscolhidas;

        if (this.errosAcumulados == 2){
            linha5 = " |     /\n";
        } else if (this.errosAcumulados == 3) {
            linha5 = " |     /|\n";
        } else if (this.errosAcumulados >= 4) {
            linha5 = " |     /|\\ \n";
        }

        if (this.errosAcumulados == 5) {
            linha6 = " |     /\n";
        } else if (this.errosAcumulados == 6) {
            linha6 = " |     / \\ \n";
        }

        System.out.println( linha1 + linha2 + linha3 + linha4 + linha5 + linha6 + linha7 + letras);

        if (this.errosAcumulados == this.MAXIMOERROS) {
            System.out.println(this.palavraEscolhida.toUpperCase());
            System.out.println("\nFORCA! Você perdeu.");
        } else {
            System.out.println(String.join("", this.palavraOcultada));
        }
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
            System.out.println("\nMáximo de erros possíveis: " + this.MAXIMOERROS);
            System.out.println("Erros acumulados: " + this.errosAcumulados);
            System.out.print("\nDigite uma letra para verificar se a palavra contém a letra: ");
            String letra = String.valueOf(scanner.next().toLowerCase().charAt(0));
            this.verificarLetra(letra);
            if (this.errosAcumulados == this.MAXIMOERROS) {
                this.obterForca();
            }
        } while (this.errosAcumulados < this.MAXIMOERROS);
        this.iniciarNovaPartida();
    }

    private void iniciarNovaPartida() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nDeseja jogar uma nova partida? (s / n) ");
        String novaPartida = scanner.next();

        if(novaPartida.equals("s")) {
            this.errosAcumulados = 0;
            this.palavraOcultada = new ArrayList<>();
            this.letrasEscolhidas = new ArrayList<>();
            this.venceu = false;
            this.palavraEscolhida = this.obterPalavraAleatoria();
            this.iniciarJogo();
        }
    }
}

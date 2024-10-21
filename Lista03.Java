/*Nome: Lucas da Silva Lucio
 Disciplina: Desenvolvimento de aplicações
 TURMA: TCTG241CNTDEV
 */
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Atividades {

    public static void main(String[] args) {
        // Questão 1. FUPQ insira 10 números em um vetor e apresente.
        // a. a lista com os números obtidos;
        // b. A lista com os números ordenados em ordem crescente;
        // c. A lista com os números ordenados em ordem decrescente;

        Scanner scanner = new Scanner(System.in);
        int[] numeros = new int[10];

        for (int i = 0; i < 10; i++) {
            System.out.print("Digite o número " + (i + 1) + ": ");
            numeros[i] = scanner.nextInt();
        }

        System.out.println("Números obtidos: " + Arrays.toString(numeros));

        int[] numerosCrescente = numeros.clone();
        Arrays.sort(numerosCrescente);
        System.out.println("Números em ordem crescente: " + Arrays.toString(numerosCrescente));

        int[] numerosDecrescente = numeros.clone();
        Arrays.sort(numerosDecrescente);
        for (int i = 0; i < numerosDecrescente.length / 2; i++) {
            int temp = numerosDecrescente[i];
            numerosDecrescente[i] = numerosDecrescente[numerosDecrescente.length - 1 - i];
            numerosDecrescente[numerosDecrescente.length - 1 - i] = temp;
        }
        System.out.println("Números em ordem decrescente: " + Arrays.toString(numerosDecrescente));

        //Questão  2. FUPQ crie uma matriz para armazenar os dados de um cadastro de pessoas com os dados.
        // a. ID pessoa
        // b. Nome Pessoa
        // c. Data nascimento da pessoa;

        String[][] cadastro = new String[10][3];
        int contador = 0;
        int opcao;

        do {
            System.out.println("Escolha uma opção: 1) Inserir 2) Alterar 3) Consultar 4) Excluir 5) Sair");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1:
                    if (contador < 10) {
                        System.out.print("Digite o ID da pessoa: ");
                        cadastro[contador][0] = scanner.nextLine();
                        System.out.print("Digite o nome da pessoa: ");
                        cadastro[contador][1] = scanner.nextLine();
                        System.out.print("Digite a data de nascimento: ");
                        cadastro[contador][2] = scanner.nextLine();
                        contador++;
                    } else {
                        System.out.println("Cadastro cheio.");
                    }
                    break;
                case 2:
                    System.out.print("Digite o ID da pessoa a ser alterada: ");
                    String idAlterar = scanner.nextLine();
                    for (int i = 0; i < contador; i++) {
                        if (cadastro[i][0].equals(idAlterar)) {
                            System.out.print("Novo nome: ");
                            cadastro[i][1] = scanner.nextLine();
                            System.out.print("Nova data de nascimento: ");
                            cadastro[i][2] = scanner.nextLine();
                            break;
                        }
                    }
                    break;
                case 3:
                    System.out.print("Digite o ID da pessoa a ser consultada: ");
                    String idConsultar = scanner.nextLine();
                    for (int i = 0; i < contador; i++) {
                        if (cadastro[i][0].equals(idConsultar)) {
                            System.out.println("ID: " + cadastro[i][0] + ", Nome: " + cadastro[i][1] + ", Data de Nascimento: " + cadastro[i][2]);
                            break;
                        }
                    }
                    break;
                case 4:
                    System.out.print("Digite o ID da pessoa a ser excluída: ");
                    String idExcluir = scanner.nextLine();
                    for (int i = 0; i < contador; i++) {
                        if (cadastro[i][0].equals(idExcluir)) {
                            cadastro[i] = null;
                            System.arraycopy(cadastro, i + 1, cadastro, i, contador - i - 1);
                            cadastro[contador - 1] = null;
                            contador--;
                            break;
                        }
                    }
                    break;
            }
        } while (opcao != 5);

        // Questão 3. FUPQ simule o jogo da velha contra o programa de computador.
        // a. Normal, que o programa escolha a opção de jogo aleatória
        // b. Difícil: que o programa faça as jogadas para ganhar ou empatar o jogo;

        char[][] tabuleiro = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
        boolean jogoAtivo = true;
        Random rnd = new Random();

        while (jogoAtivo) {
            imprimirTabuleiro(tabuleiro);
            System.out.print("Digite a linha (0, 1, 2) e a coluna (0, 1, 2) da sua jogada: ");
            int linha = scanner.nextInt();
            int coluna = scanner.nextInt();
            tabuleiro[linha][coluna] = 'X';

            // Jogada do computador
            while (true) {
                int linhaComp = rnd.nextInt(3);
                int colunaComp = rnd.nextInt(3);
                if (tabuleiro[linhaComp][colunaComp] == ' ') {
                    tabuleiro[linhaComp][colunaComp] = 'O';
                    break;
                }
            }

            // Verificar se o jogo terminou
            if (verificarVencedor(tabuleiro, 'X')) {
                imprimirTabuleiro(tabuleiro);
                System.out.println("Você ganhou!");
                jogoAtivo = false;
            } else if (verificarVencedor(tabuleiro, 'O')) {
                imprimirTabuleiro(tabuleiro);
                System.out.println("O computador ganhou!");
                jogoAtivo = false;
            } else if (isEmpate(tabuleiro)) {
                imprimirTabuleiro(tabuleiro);
                System.out.println("Empate!");
                jogoAtivo = false;
            }
        }
        scanner.close();
    }

    private static void imprimirTabuleiro(char[][] tabuleiro) {
        for (char[] linha : tabuleiro) {
            for (char c : linha) {
                System.out.print(c + "|");
            }
            System.out.println();
        }
    }

    private static boolean verificarVencedor(char[][] tabuleiro, char jogador) {
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i][0] == jogador && tabuleiro[i][1] == jogador && tabuleiro[i][2] == jogador) {
                return true;
            }
            if (tabuleiro[0][i] == jogador && tabuleiro[1][i] == jogador && tabuleiro[2][i] == jogador) {
                return true;
            }
        }
        if (tabuleiro[0][0] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][2] == jogador) {
            return true;
        }
        if (tabuleiro[0][2] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][0] == jogador) {
            return true;
        }
        return false;
    }

    private static boolean isEmpate(char[][] tabuleiro) {
        for (char[] linha : tabuleiro) {
            for (char c : linha) {
                if (c == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}

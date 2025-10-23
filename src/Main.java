import java.util.Scanner;

public class Main {
    private static ArvoreBinariaMorse arvore = new ArvoreBinariaMorse();

    public static void main(String[] args) {
        arvore.inicializar();
        arvore.carregarPadrao();
        Scanner sc = new Scanner(System.in);

        while (true) {
            println("\n=== MORSE TREE ===");
            println("1) Codificar TEXTO -> MORSE");
            println("2) Decodificar MORSE -> TEXTO");
            println("3) Buscar codigo por simbolo (A-Z/0-9)");
            println("4) Buscar simbolo por codigo (.-)");
            println("5) Inserir simbolo manualmente (codigo + simbolo)");
            println("6) Remover por codigo");
            println("7) Remover por simbolo");
            println("8) Exibir arvore");
            println("0) Sair");
            print("Opcao: ");

            String linha = sc.nextLine();
            int op = parseIntSeguro(linha);

            if (op == 0) {
                println("Saindo...");
                break;
            } else if (op == 1) {
                print("Texto (A-Z, 0-9, espacos): ");
                String txt = sc.nextLine();
                String morse = arvore.codificarTexto(txt);
                println("Morse: " + morse);
            } else if (op == 2) {
                print("Morse (sep. por espacos; 2 espacos = separa palavras): ");
                String m = sc.nextLine();
                String dec = arvore.decodificarMensagemMorse(m);
                println("Texto: " + dec);
            } else if (op == 3) {
                print("Simbolo (1 char): ");
                String s = sc.nextLine();
                if (s != null && s.length() > 0) s = s.substring(0, 1).toUpperCase();
                String codigo = arvore.buscarCodigoPorSimbolo(s);
                if (codigo.equals("")) println("Nao encontrado.");
                else println("Codigo: " + codigo);
            } else if (op == 4) {
                print("Codigo (.-): ");
                String c = sc.nextLine();
                if (!codigoValido(c)) {
                    println("Codigo invalido (use apenas . e -).");
                } else {
                    String simb = arvore.buscarPorCodigo(c);
                    if (simb.equals("")) println("Nao encontrado.");
                    else println("Simbolo: " + simb);
                }
            } else if (op == 5) {
                print("Codigo (.-): ");
                String c = sc.nextLine();
                if (!codigoValido(c)) {
                    println("Codigo invalido (use apenas . e -).");
                } else {
                    print("Simbolo (1 char): ");
                    String s = sc.nextLine();
                    if (s == null || s.length() == 0) {
                        println("Simbolo invalido.");
                    } else {
                        String one = s.substring(0, 1).toUpperCase();
                        arvore.inserir(c, one);
                        println("Inserido.");
                    }
                }
            } else if (op == 6) {
                print("Codigo (.-) a remover: ");
                String c = sc.nextLine();
                if (!codigoValido(c)) {
                    println("Codigo invalido (use apenas . e -).");
                } else {
                    boolean ok = arvore.removerPorCodigo(c);
                    println(ok ? "Removido (valor limpo)" : "Nao encontrado.");
                }
            } else if (op == 7) {
                print("Simbolo (1 char) a remover: ");
                String s = sc.nextLine();
                if (s == null || s.length() == 0) {
                    println("Simbolo invalido.");
                } else {
                    String one = s.substring(0, 1).toUpperCase();
                    boolean ok = arvore.removerPorSimbolo(one);
                    println(ok ? "Removido (valor limpo)" : "Nao encontrado.");
                }
            } else if (op == 8) {
                println(arvore.exibirHierarquia());
            } else {
                println("Opcao invalida.");
            }
        }

        sc.close();
    }

    private static void println(String s) { System.out.println(s); }
    private static void print(String s)   { System.out.print(s); }

    private static boolean codigoValido(String codigo) {
        int i = 0;
        while (i < codigo.length()) {
            String c = codigo.substring(i, i + 1);
            if (!(c.equals(".") || c.equals("-"))) return false;
            i = i + 1;
        }
        return true;
    }

    private static int parseIntSeguro(String s) {
        if (s == null || s.length() == 0) return -1;
        int i = 0, sign = 1, val = 0;
        String first = s.substring(0, 1);
        if (first.equals("-")) { sign = -1; i = 1; }
        while (i < s.length()) {
            String ch = s.substring(i, i + 1);
            if (ch.length() != 1) return -1;
            int d = digito(ch);
            if (d < 0) return -1;
            val = val * 10 + d;
            i = i + 1;
        }
        return sign * val;
    }

    private static int digito(String ch) {
        if (ch.equals("0")) return 0;
        if (ch.equals("1")) return 1;
        if (ch.equals("2")) return 2;
        if (ch.equals("3")) return 3;
        if (ch.equals("4")) return 4;
        if (ch.equals("5")) return 5;
        if (ch.equals("6")) return 6;
        if (ch.equals("7")) return 7;
        if (ch.equals("8")) return 8;
        if (ch.equals("9")) return 9;
        return -1;
    }
}
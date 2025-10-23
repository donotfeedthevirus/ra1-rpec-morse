public class ArvoreBinariaMorse {
    private Node raiz;

    public void inicializar() {
        raiz = new Node("");
    }

    public void carregarPadrao() {
        inserir(".-",   "A");
        inserir("-...", "B");
        inserir("-.-.", "C");
        inserir("-..",  "D");
        inserir(".",    "E");
        inserir("..-.", "F");
        inserir("--.",  "G");
        inserir("....", "H");
        inserir("..",   "I");
        inserir(".---", "J");
        inserir("-.-",  "K");
        inserir(".-..", "L");
        inserir("--",   "M");
        inserir("-.",   "N");
        inserir("---",  "O");
        inserir(".--.", "P");
        inserir("--.-", "Q");
        inserir(".-.",  "R");
        inserir("...",  "S");
        inserir("-",    "T");
        inserir("..-",  "U");
        inserir("...-", "V");
        inserir(".--",  "W");
        inserir("-..-", "X");
        inserir("-.--", "Y");
        inserir("--..", "Z");

        inserir("-----", "0");
        inserir(".----", "1");
        inserir("..---", "2");
        inserir("...--", "3");
        inserir("....-", "4");
        inserir(".....", "5");
        inserir("-....", "6");
        inserir("--...", "7");
        inserir("---..", "8");
        inserir("----.", "9");
    }

    public void inserir(String codigo, String simbolo) {
        if (raiz == null) inicializar();
        Node atual = raiz;
        int i = 0;
        while (i < codigo.length()) {
            String c = codigo.substring(i, i + 1);
            if (c.equals(".")) {
                if (atual.esq == null) atual.esq = new Node("");
                atual = atual.esq;
            } else if (c.equals("-")) {
                if (atual.dir == null) atual.dir = new Node("");
                atual = atual.dir;
            } else {
                return;
            }
            i = i + 1;
        }
        atual.valor = simbolo;
    }

    public String buscarPorCodigo(String codigo) {
        if (raiz == null) return "";
        Node atual = raiz;
        int i = 0;
        while (i < codigo.length()) {
            String c = codigo.substring(i, i + 1);
            if (c.equals(".")) {
                if (atual.esq == null) return "";
                atual = atual.esq;
            } else if (c.equals("-")) {
                if (atual.dir == null) return "";
                atual = atual.dir;
            } else {
                return "";
            }
            i = i + 1;
        }
        if (atual != null && atual.temValor()) return atual.valor;
        return "";
    }

    public String buscarCodigoPorSimbolo(String simbolo) {
        if (raiz == null) return "";
        return dfsCodigo(raiz, simbolo, "");
    }

    private String dfsCodigo(Node no, String simbolo, String caminho) {
        if (no == null) return "";
        if (no.temValor() && no.valor.equals(simbolo)) return caminho;

        String achouEsq = dfsCodigo(no.esq, simbolo, caminho + ".");
        if (!achouEsq.equals("")) return achouEsq;

        String achouDir = dfsCodigo(no.dir, simbolo, caminho + "-");
        if (!achouDir.equals("")) return achouDir;

        return "";
    }

    public boolean removerPorCodigo(String codigo) {
        if (raiz == null) return false;
        Node atual = raiz;
        int i = 0;
        while (i < codigo.length()) {
            String c = codigo.substring(i, i + 1);
            if (c.equals(".")) {
                if (atual.esq == null) return false;
                atual = atual.esq;
            } else if (c.equals("-")) {
                if (atual.dir == null) return false;
                atual = atual.dir;
            } else {
                return false;
            }
            i = i + 1;
        }
        if (atual != null && atual.temValor()) {
            atual.valor = "";
            return true;
        }
        return false;
    }

    public boolean removerPorSimbolo(String simbolo) {
        String caminho = buscarCodigoPorSimbolo(simbolo);
        if (caminho.equals("")) return false;
        return removerPorCodigo(caminho);
    }

    public String decodificarMensagemMorse(String mensagem) {
        Tokenizer tk = new Tokenizer(mensagem);
        String resultado = "";
        int consecutivosVazios = 0;

        while (true) {
            String t = tk.proximoToken();
            if (t == null) break;

            if (t.equals("")) {
                consecutivosVazios = consecutivosVazios + 1;
                if (consecutivosVazios >= 2) {
                    if (resultado.length() == 0) {
                        resultado = resultado + " ";
                    } else {
                        String ultimo = resultado.substring(resultado.length() - 1, resultado.length());
                        if (!ultimo.equals(" ")) resultado = resultado + " ";
                    }
                }
            } else {
                consecutivosVazios = 0;
                String s = buscarPorCodigo(t);
                if (!s.equals("")) resultado = resultado + s;
            }
        }
        return resultado;
    }

    public String codificarTexto(String texto) {
        String res = "";
        int i = 0;
        while (i < texto.length()) {
            String ch = texto.substring(i, i + 1);
            String upper = ch.toUpperCase();

            if (upper.equals(" ")) {
                if (res.length() == 0) {
                    res = res + "  ";
                } else {
                    String ultimo = res.substring(res.length() - 1, res.length());
                    if (ultimo.equals(" ")) {
                        res = res + " ";
                    } else {
                        res = res + "  ";
                    }
                }
            } else {
                String codigo = buscarCodigoPorSimbolo(upper);
                if (!codigo.equals("")) {
                    if (res.length() > 0) {
                        String ultimo = res.substring(res.length() - 1, res.length());
                        if (!ultimo.equals(" ")) res = res + " ";
                    }
                    res = res + codigo;
                }
            }
            i = i + 1;
        }
        return res;
    }

    public String exibirHierarquia() {
        Acum a = new Acum();
        a.add("(raiz)\n");
        montar(raiz, "", false, a, true);
        return a.get();
    }

    private void montar(Node no, String prefixo, boolean isDir, Acum a, boolean ehRaiz) {
        if (no == null) return;

        if (!ehRaiz) {
            String marcador = isDir ? "-:" : ".:";
            String valorMostrar = no.temValor() ? no.valor : "Ø";
            a.add(prefixo + marcador + " " + valorMostrar + "\n");
        }

        String proxPrefixo = prefixo + (ehRaiz ? "" : (isDir ? "   " : "   "));
        if (no.esq != null) montar(no.esq, proxPrefixo + "│  ", false, a, false);
        if (no.dir != null) montar(no.dir, proxPrefixo + "   ", true, a, false);
    }

    private static class Acum {
        private String conteudo = "";
        void add(String t) { conteudo = conteudo + t; }
        String get() { return conteudo; }
    }
}

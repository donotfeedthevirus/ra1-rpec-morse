public class Node {
    String valor;
    Node esq;
    Node dir;

    public Node() {
        this.valor = "";
        this.esq = null;
        this.dir = null;
    }

    public Node(String v) {
        this.valor = v;
        this.esq = null;
        this.dir = null;
    }

    public boolean temValor() {
        return this.valor != null && !this.valor.equals("");
    }
}

public class Tokenizer {
    private String fonte;
    private int pos;

    public Tokenizer(String texto) {
        if (texto == null) texto = "";
        this.fonte = texto;
        this.pos = 0;
    }

    public boolean terminou() {
        return pos >= fonte.length();
    }

    public String proximoToken() {
        if (terminou()) return null;

        String c = fonte.substring(pos, pos + 1);
        if (c.equals(" ")) {
            pos = pos + 1;
            return "";
        }

        String token = "";
        while (!terminou()) {
            String ch = fonte.substring(pos, pos + 1);
            if (ch.equals(" ")) break;
            token = token + ch;
            pos = pos + 1;
        }
        return token;
    }
}

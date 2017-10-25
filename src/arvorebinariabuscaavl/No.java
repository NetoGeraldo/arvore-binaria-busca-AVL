package arvorebinariabuscaavl;

/**
 *
 * @author gsnet
 */
public class No<Chave extends Comparable<Chave>, Valor> {
    
    private Chave chave;
    private Valor valor;
    private No<Chave, Valor> pai;
    private No<Chave, Valor> filhoEsquerdo;
    private No<Chave, Valor> filhoDireito;

    public No() {   }
    
    public No(Chave chave, Valor valor) {
        this.chave = chave;
        this.valor = valor;
    }

    public Chave getChave() {
        return chave;
    }

    public void setChave(Chave chave) {
        this.chave = chave;
    }

    public Valor getValor() {
        return valor;
    }

    public void setValor(Valor valor) {
        this.valor = valor;
    }

    public No<Chave, Valor> getPai() {
        return pai;
    }

    public void setPai(No<Chave, Valor> pai) {
        this.pai = pai;
    }

    public No<Chave, Valor> getFilhoEsquerdo() {
        return filhoEsquerdo;
    }

    public void setFilhoEsquerdo(No<Chave, Valor> filhoEsquerdo) {
        this.filhoEsquerdo = filhoEsquerdo;
    }

    public No<Chave, Valor> getFilhoDireito() {
        return filhoDireito;
    }

    public void setFilhoDireito(No<Chave, Valor> filhoDireito) {
        this.filhoDireito = filhoDireito;
    }
    
    

    
    
}

package arvorebinariabuscaavl;

import java.util.Collection;

/**
 *
 * @author gsnet
 */
public interface IArvoreBinariaBusca<Chave extends Comparable<Chave>, Valor> {

    public boolean inserir(Chave chave, Valor valor);
    public Valor remover(Chave chave);
    public Valor buscar(Chave chave);
    public void limpar();
    public boolean ehVazia();
    public int tamanho();
    public Collection<Valor> obterTodos();  
    public Collection<Valor> obterTodosPreOrdem();
    public Collection<Valor> obterTodosPosOrdem();
    public Collection<Valor> obterTodosEmOrdem();

}

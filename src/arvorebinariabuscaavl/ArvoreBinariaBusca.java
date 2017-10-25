package arvorebinariabuscaavl;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author gsnet
 */
public class ArvoreBinariaBusca<Chave extends Comparable<Chave>, Valor> implements IArvoreBinariaBusca<Chave, Valor> {
    
    private No<Chave, Valor> raiz;
    private No<Chave, Valor> noCorrente; // responsavel por percorrer a arvore na hora de inserir
    private ArrayList<Valor> collection; // responsavel por retornar uma lista nos metodos de obter

    public ArvoreBinariaBusca() {
        this.collection = new ArrayList<>();
    }
    
    @Override
    public boolean inserir(Chave chave, Valor valor) {
        
        No<Chave, Valor> novoNo = new No<>(chave, valor);
        
        if (this.raiz == null) {
            this.raiz = novoNo;
            this.noCorrente = this.raiz;
            return true;
        } else {
            // A chave é menor que a chave do noCorrente
            if (novoNo.getChave().compareTo(this.noCorrente.getChave()) < 0) {
                
                if (this.noCorrente.getFilhoEsquerdo() != null) {
                    this.noCorrente = this.noCorrente.getFilhoEsquerdo();
                    inserir(chave, valor);
                } else {
                    novoNo.setPai(noCorrente);
                    noCorrente.setFilhoEsquerdo(novoNo);
                    this.noCorrente = this.raiz;
                }
                
                return true;
                
            // A chave é maior que a chave do noCorrente
            } else if (novoNo.getChave().compareTo(this.noCorrente.getChave()) > 0) {
                
                if (this.noCorrente.getFilhoDireito() != null) {
                    this.noCorrente = this.noCorrente.getFilhoDireito();
                    inserir(chave, valor);
                } else {
                    novoNo.setPai(noCorrente);
                    noCorrente.setFilhoDireito(novoNo);
                    this.noCorrente = this.raiz;
                }
                
                return true;
            
            // A chave é igual
            } else {
                return false;
            }
            
        }
        
    }
    
    @Override
    public Valor remover(Chave chave) {
        
        if (this.noCorrente == null) {
            this.noCorrente = this.raiz;
            return null;
        }
        
        if (chave.compareTo(this.noCorrente.getChave()) < 0) {
            this.noCorrente = this.noCorrente.getFilhoEsquerdo();
            return this.remover(chave);
        } else if (chave.compareTo(this.noCorrente.getChave()) > 0) {
            this.noCorrente = this.noCorrente.getFilhoDireito();
            return this.remover(chave);
        } else {

            // No nao apresenta subarvore a esquerda
            if (this.noCorrente.getFilhoEsquerdo() == null && this.noCorrente.getFilhoDireito() != null) {
                this.noCorrente.getFilhoDireito().setPai(this.noCorrente.getPai());

                if (this.noCorrente.getChave().compareTo(this.noCorrente.getPai().getChave()) > 0) {
                    this.noCorrente.getPai().setFilhoDireito(this.noCorrente.getFilhoDireito());
                } else {
                    this.noCorrente.getPai().setFilhoEsquerdo(this.noCorrente.getFilhoDireito());
                }
                
                No<Chave, Valor> noRetorno = this.noCorrente;
                this.noCorrente = this.raiz;
                return noRetorno.getValor();
                
            // No nao apresenta subarvore a direita
            } else if (this.noCorrente.getFilhoEsquerdo() != null && this.noCorrente.getFilhoDireito() == null) {
                this.noCorrente.getFilhoEsquerdo().setPai(this.noCorrente.getPai());
                
                if (this.noCorrente.getChave().compareTo(this.noCorrente.getPai().getChave()) > 0) {
                    this.noCorrente.getPai().setFilhoDireito(this.noCorrente.getFilhoEsquerdo());
                } else {
                    this.noCorrente.getPai().setFilhoEsquerdo(this.noCorrente.getFilhoEsquerdo());
                }
                
                No<Chave, Valor> noRetorno = this.noCorrente;
                this.noCorrente = this.raiz;
                return noRetorno.getValor();
                
            // No apresenta subarvore esquerda e direita
            } else if (this.noCorrente.getFilhoEsquerdo() != null && this.noCorrente.getFilhoDireito() != null) {
                No<Chave, Valor> noAux = retornarAntecessor(this.noCorrente);
                
                // removendo o antecessor do seu local de origem
                
                // noAux é folha
                if (noAux.getFilhoEsquerdo() == null && noAux.getFilhoDireito() == null) {
                    
                    if (noAux.getChave().compareTo(noAux.getPai().getChave()) < 0) {
                        noAux.getPai().setFilhoEsquerdo(null);
                    } else {
                        noAux.getPai().setFilhoDireito(null);
                    }
                
                // noAux possui subarvore a esquerda
                } else {
                    noAux.getFilhoEsquerdo().setPai(noAux.getPai());
                    
                    if (noAux.getChave().compareTo(noAux.getPai().getChave()) < 0) {
                        noAux.getPai().setFilhoEsquerdo(noAux.getFilhoEsquerdo());
                    } else {
                        noAux.getPai().setFilhoDireito(noAux.getFilhoEsquerdo());
                    }
                    
                }
                
                noAux.setFilhoDireito(this.noCorrente.getFilhoDireito());
                if (noCorrente.getFilhoDireito() != null) {
                    this.noCorrente.getFilhoDireito().setPai(noAux);
                }
                
                noAux.setFilhoEsquerdo(this.noCorrente.getFilhoEsquerdo());
                if (noCorrente.getFilhoEsquerdo() != null) {
                    this.noCorrente.getFilhoEsquerdo().setPai(noAux);
                }
                
                noAux.setPai(this.noCorrente.getPai());
                
                if (this.noCorrente.getChave().compareTo(this.noCorrente.getPai().getChave()) > 0) {
                    this.noCorrente.getPai().setFilhoDireito(noAux);
                } else {
                    this.noCorrente.getPai().setFilhoEsquerdo(noAux );
                }
                
                No<Chave, Valor> noRetorno = this.noCorrente;
                this.noCorrente = this.raiz;
                return noRetorno.getValor();
                
            // No eh folha
            } else {
                
                if (this.noCorrente == this.raiz) {
                    this.raiz = null;
                } else {
                    
                    if (this.noCorrente.getChave().compareTo(this.noCorrente.getPai().getChave()) > 0) {
                        this.noCorrente.getPai().setFilhoDireito(null);
                    } else {
                        this.noCorrente.getPai().setFilhoEsquerdo(null);
                    }

                    this.noCorrente.setPai(null);
                
                }
                
                
                No<Chave, Valor> noRetorno = this.noCorrente;
                this.noCorrente = this.raiz;
                return noRetorno.getValor();
            }
            
        }
        
    }
    
    private No<Chave, Valor> retornarAntecessor(No<Chave, Valor> no) {
        No<Chave, Valor> noCorrente = no.getFilhoEsquerdo();
        
        while (noCorrente.getFilhoDireito() != null) {            
            noCorrente = noCorrente.getFilhoDireito();
        }
        
        return noCorrente;
    }

    @Override
    public Valor buscar(Chave chave) {
        No<Chave, Valor> noRetorno = null;
        while (noCorrente != null && chave.compareTo(noCorrente.getChave()) != 0) {            
            
            if (chave.compareTo(noCorrente.getChave()) < 0) {
                noCorrente = noCorrente.getFilhoEsquerdo();
            } else if (chave.compareTo(noCorrente.getChave()) > 0) {
                noCorrente = noCorrente.getFilhoDireito();
            }
            
        }
        
        
        if (noCorrente == null) {
            this.noCorrente = this.raiz;
            return null;
        } else {
            noRetorno = noCorrente;
            noCorrente = this.raiz;
            return noRetorno.getValor();
        }
        
    }
    
    public ArrayList<Valor> navegacaoLargura() {
        
        if (this.ehVazia()) {
            return null;
        }
        
        this.collection.clear();
        Queue<No<Chave, Valor>> fila = new LinkedList<>();
        
        No<Chave, Valor> noCorrente = this.raiz;
        
        fila.add(noCorrente);
        
        while (!fila.isEmpty()) {

            noCorrente = fila.poll();

            this.collection.add(noCorrente.getValor());
            
            if (noCorrente.getFilhoEsquerdo() != null ) {
                fila.add(noCorrente.getFilhoEsquerdo());
            }
            
            if (noCorrente.getFilhoDireito() != null) {
                fila.add(noCorrente.getFilhoDireito());
            }
            
        }
        
        return this.collection;
        
    }

    @Override
    public void limpar() {
        this.raiz = null;
    }

    @Override
    public boolean ehVazia() {
        if (this.raiz == null) {
            return true;
        }
        
        return false;
    }

    @Override
    public int tamanho() {
        return this.obterTodos().size();
    }

    @Override
    public ArrayList<Valor> obterTodos() {
        return this.obterTodosEmOrdem();
    }

    @Override
    public ArrayList<Valor> obterTodosPreOrdem() {
        this.collection.clear();
        
        this.preOrdem(this.raiz);
        
        ArrayList<Valor> listaRetorno = this.collection;
        
        return listaRetorno;
    }

    @Override
    public ArrayList<Valor> obterTodosPosOrdem() {
        this.collection.clear();

        this.posOrdem(this.raiz);
        
        ArrayList<Valor> listaRetorno = this.collection;
        
        return listaRetorno;
    }

    @Override
    public ArrayList<Valor> obterTodosEmOrdem() {
        this.collection.clear();

        this.emOrdem(this.raiz);
        
        ArrayList<Valor> listaRetorno = this.collection;
        
        return listaRetorno;
    }
    
    private void preOrdem(No<Chave, Valor> no) {
        
        if (no == null) {
            return;
        }
        
        collection.add(no.getValor());
    
        if (no.getFilhoEsquerdo() != null) {
            this.preOrdem(no.getFilhoEsquerdo());
        }
        
        if (no.getFilhoDireito() != null) {
            this.preOrdem(no.getFilhoDireito());
        }
    
    }
    
    private void posOrdem(No<Chave, Valor> no) {
        if (no == null) {
            return;
        }
        
        if (no.getFilhoEsquerdo() != null) {
            this.posOrdem(no.getFilhoEsquerdo());
        }
        
        if (no.getFilhoDireito() != null) {
            this.posOrdem(no.getFilhoDireito());
        }
        
        this.collection.add(no.getValor());
    }
    
    private void emOrdem(No<Chave, Valor> no) {
        
        if (no == null) {
            return;
        }
        
        if (no.getFilhoEsquerdo() != null) {
            this.emOrdem(no.getFilhoEsquerdo());
        }
        
        this.collection.add(no.getValor());
        
        if (no.getFilhoDireito() != null) {
            this.emOrdem(no.getFilhoDireito());
        }
        
    }

    public No<Chave, Valor> getRaiz() {
        return raiz;
    }
    
}

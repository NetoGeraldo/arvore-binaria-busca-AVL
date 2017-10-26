package arvorebinariabuscaavl;

import arvorebinariabuscaavl.ArvoreBinariaBusca;

public class teste {
    
    public static void main(String[] args) {
        
        ArvoreBinariaBusca<Integer, Integer> arvore = new ArvoreBinariaBusca<>();
        
        arvore.inserir(40, 40);
        arvore.inserir(18, 18);
        arvore.inserir(11, 11);
        arvore.inserir(25, 25);
        arvore.inserir(5, 5);
        arvore.inserir(14, 14);
        arvore.inserir(52, 52);
        arvore.inserir(48, 48);
        arvore.inserir(67, 67);
        //arvore.inserir(7, 7);
        System.out.println(arvore.desbalanceada);
        System.out.println("funcionou?");
    }
    
}

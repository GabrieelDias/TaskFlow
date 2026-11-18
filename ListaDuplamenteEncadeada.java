package TaskFlow;

public class ListaDuplamenteEncadeada {
    
    No inicio;
    No fim;


    public int tamanhoLista() {
        No aux = inicio;
        int contador = 0;

        while (aux != null) {
            aux = aux.getProximo();
            contador ++;
        }
        return contador;
    }

    public void inserirFinal(Tarefa tarefa){

       No novo = new No(tarefa);

        if (inicio == null) {
            inicio = novo;
            fim = novo;
        }else{
            fim.setProximo(novo);
            novo.setAnterior(fim);

            fim = novo;
            
        }

    }


    public void inserirInicio(Tarefa tarefa){

        No novo = new No(tarefa);


        if (inicio == null) {
            inicio = novo;
            fim = novo;
        }else{

            novo.setProximo(inicio);
            inicio.setAnterior(novo);

            inicio = novo;
        }

    }


    public void inserirEspec(Tarefa tarefa, int posicao){


        if (posicao < 1) {
             System.out.println("Posição inválida!");
            return;
        }

        int capacidade = tamanhoLista();

        if (posicao > capacidade + 1) {
            System.out.println("Posição inválida!");
            return;
        }
        
        if (posicao == 1) {
            inserirInicio(tarefa);
            return;
        }

        
        if (posicao == capacidade + 1) {
            inserirFinal(tarefa);
            return;
        }
                
        No aux = inicio;
        int posiAtual = 1;
        No novo = new No(tarefa);
        

        while (posiAtual != posicao && aux != null) {
            aux = aux.getProximo();
            posiAtual ++;
        }


        if (aux == null) {
            System.out.println("Posição inválida!");
            return;
        }

        No anterior = aux.getAnterior();
        anterior.setProximo(novo);
        novo.setAnterior(anterior);
        novo.setProximo(aux);
        aux.setAnterior(novo);
    }








}

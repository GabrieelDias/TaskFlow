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




    public void imprimirInicio() {

        No aux = inicio;

        int posicao = 1;

        while (aux != null) {

            System.out.println("Posição: " + posicao);
            System.out.println("ID: " + aux.getTarefa().getId());
            System.out.println("Título: " + aux.getTarefa().getTitulo());
            System.out.println("Descrição: " + aux.getTarefa().getDescricao());
            System.out.println();

            aux = aux.getProximo();
            posicao++;
        }
    }


    public void imprimirFim() {

        No aux = fim;

        int posicao = tamanhoLista();

        while (aux != null) {

            System.out.println("Posição: " + posicao);
            System.out.println("ID: " + aux.getTarefa().getId());
            System.out.println("Título: " + aux.getTarefa().getTitulo());
            System.out.println("Descrição: " + aux.getTarefa().getDescricao());
            System.out.println();

            aux = aux.getAnterior();
            posicao--;
        }

    }
  
    
    public void removerPorId(int id) {

        No aux = inicio;

        while (aux != null && aux.getTarefa().getId() != id) {
            aux = aux.getProximo();
        }

        if (aux == null) {
            System.out.println("Tarefa não encontrada!");
            return;
        }

        if (aux == inicio) {

            inicio = aux.getProximo();

            if (inicio != null) {
                inicio.setAnterior(null);
            } else {
                fim = null;
            }

        } else if (aux == fim) {

            fim = aux.getAnterior();

            if (fim != null) {
                fim.setProximo(null);
            }

        } else {

            No anterior = aux.getAnterior();
            No proximo = aux.getProximo();

            anterior.setProximo(proximo);
            proximo.setAnterior(anterior);
        }

        System.out.println("Tarefa removida com sucesso!");
    }


    
    public void removerPorTitulo(String titulo) {
        No aux = inicio;

        while (aux != null && !aux.getTarefa().getTitulo().equals(titulo)) {
            aux = aux.getProximo();
        }

        if (aux == null) {
            System.out.println("Tarefa não encontrada!");
            return;
        }

        if (aux == inicio) {
            inicio = aux.getProximo();

            if (inicio != null) {
                inicio.setAnterior(null);
            } else {
                fim = null;
            }

        } else if (aux == fim) {
            fim = aux.getAnterior();

            if (fim != null) {
                fim.setProximo(null);
            }

        } else {
            No anterior = aux.getAnterior();
            No proximo = aux.getProximo();

            anterior.setProximo(proximo);
            proximo.setAnterior(anterior);
        }

        System.out.println("Tarefa removida com sucesso!");
    }





    public void moverTarefa(int id, int novaPosicao){
        int tamanho = tamanhoLista();

        if (novaPosicao < 1 || novaPosicao > tamanho) {
            System.out.println("Posição inválida!");
            return;
        }

        No aux = inicio;

        while (aux != null && aux.getTarefa().getId() != id) {
            aux = aux.getProximo();
        }

        if (aux == null) {
            System.out.println("Tarefa não encontrada!");
            return;
        }

        Tarefa tarefaMovida = aux.getTarefa();

        removerPorId(id);
        inserirEspec(tarefaMovida, novaPosicao);
    }


   public static void main(String[] args) {

    ListaDuplamenteEncadeada lista = new ListaDuplamenteEncadeada();

    Tarefa tarefa1 = new Tarefa(0, "Estudar", "Para ser alguém na vida");
    Tarefa tarefa2 = new Tarefa(1, "Tabalhar", "Ganhar dinheiro");
    Tarefa tarefa3 = new Tarefa(2, "Flamengo", "Campeão brasileiro");

    lista.inserirInicio(tarefa1);
    lista.inserirInicio(tarefa2);
    lista.inserirInicio(tarefa3);

    lista.imprimirInicio();
    System.out.println("===========================");
    lista.imprimirFim();



    lista.moverTarefa(2, 1);

    System.out.println("Após movimentação:");
    System.out.println();
    lista.imprimirInicio();
}

}



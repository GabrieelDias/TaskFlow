package TaskFlow;

public class FilaTriagem {
    
    private NoFila inicio;
    private NoFila fim;
    private int tamanho;

    public FilaTriagem() {
        this.inicio = null;
        this.fim = null;
        this.tamanho = 0;
    }

   
    public void enfileirar(Demanda demanda) {

        NoFila novo = new NoFila(demanda);

        if (estaVazia()) {
            inicio = novo;
            fim = novo;
        } else {
            fim.setProximo(novo);
            fim = novo;
        }

        tamanho++;

        System.out.println("Demanda adicionada à fila!");
    }

   
    public Demanda desenfileirar() {

        if (estaVazia()) {
            System.out.println("Não existem demandas na fila.");
            return null;
        }

        Demanda demandaAtendida = inicio.getDemanda();

        inicio = inicio.getProximo();

        tamanho--;

        if (inicio == null) {
            fim = null;
        }

        return demandaAtendida;
    }

  
    public Demanda consultarProxima() {

        if (estaVazia()) {
            return null;
        }

        return inicio.getDemanda();
    }

    
    public void exibirFila() {

        if (estaVazia()) {
            System.out.println("A fila de triagem está vazia.");
            return;
        }

        NoFila atual = inicio;

        System.out.println("\n===== FILA DE TRIAGEM =====");

        while (atual != null) {

            atual.getDemanda().exibirDados();

            System.out.println("--------------------------");

            atual = atual.getProximo();
        }
    }

    public boolean estaVazia() {
        return inicio == null;
    }

    public int getTamanho() {
        return tamanho;
    }
}

package TaskFlow;

public class ListaDuplamenteEncadeada {

    private No inicio;
    private No fim;
    private PilhaDesfazer historico;

    public ListaDuplamenteEncadeada(PilhaDesfazer historico) {
        this.inicio = null;
        this.fim = null;
        this.historico = historico;
    }

    public int tamanhoLista() {

        No aux = inicio;
        int contador = 0;

        while (aux != null) {
            aux = aux.getProximo();
            contador++;
        }

        return contador;
    }

    // =========================
    // INSERÇÕES
    // =========================

    public void inserirFinal(Tarefa tarefa) {
        inserirEspec(tarefa, tamanhoLista() + 1);
    }

    public void inserirInicio(Tarefa tarefa) {
        inserirEspec(tarefa, 1);
    }

    public void inserirEspec(Tarefa tarefa, int posicao) {

        if (posicao < 1 || posicao > tamanhoLista() + 1) {
            System.out.println("Posição inválida!");
            return;
        }

        inserirInterno(tarefa, posicao);

        // Registra a operação inversa.
        historico.empilhar(() -> removerInterno(tarefa.getId()));

        System.out.println("Tarefa inserida com sucesso!");
    }

    private void inserirInterno(Tarefa tarefa, int posicao) {

        No novo = new No(tarefa);

        if (inicio == null) {
            inicio = novo;
            fim = novo;
            return;
        }

        if (posicao == 1) {

            novo.setProximo(inicio);
            inicio.setAnterior(novo);
            inicio = novo;

            return;
        }

        if (posicao == tamanhoLista() + 1) {

            fim.setProximo(novo);
            novo.setAnterior(fim);
            fim = novo;

            return;
        }

        No aux = inicio;

        for (int i = 1; i < posicao; i++) {
            aux = aux.getProximo();
        }

        No anterior = aux.getAnterior();

        anterior.setProximo(novo);
        novo.setAnterior(anterior);

        novo.setProximo(aux);
        aux.setAnterior(novo);
    }

    // =========================
    // REMOÇÕES
    // =========================

    public void removerPorId(int id) {

        int posicao = buscarPosicao(id);

        if (posicao == -1) {
            System.out.println("Tarefa não encontrada!");
            return;
        }

        Tarefa removida = removerInterno(id);

        // Registra a operação inversa.
        historico.empilhar(() -> inserirInterno(removida, posicao));

        System.out.println("Tarefa removida com sucesso!");
    }

    public void removerPorTitulo(String titulo) {

        No aux = inicio;

        while (aux != null &&
               !aux.getTarefa().getTitulo().equalsIgnoreCase(titulo)) {

            aux = aux.getProximo();
        }

        if (aux == null) {
            System.out.println("Tarefa não encontrada!");
            return;
        }

        removerPorId(aux.getTarefa().getId());
    }

    private Tarefa removerInterno(int id) {

        No aux = inicio;

        while (aux != null && aux.getTarefa().getId() != id) {
            aux = aux.getProximo();
        }

        if (aux == null) {
            return null;
        }

        Tarefa removida = aux.getTarefa();

        if (aux == inicio) {

            inicio = aux.getProximo();

            if (inicio != null) {
                inicio.setAnterior(null);
            } else {
                fim = null;
            }

        } else if (aux == fim) {

            fim = aux.getAnterior();
            fim.setProximo(null);

        } else {

            No anterior = aux.getAnterior();
            No proximo = aux.getProximo();

            anterior.setProximo(proximo);
            proximo.setAnterior(anterior);
        }

        return removida;
    }

   // =========================
    // MOVIMENTAÇÃO
    // =========================

    public void moverTarefa(int id, int novaPosicao) {

        int tamanho = tamanhoLista();

        if (novaPosicao < 1 || novaPosicao > tamanho) {
            System.out.println("Posição inválida!");
            return;
        }

        int posicaoAntiga = buscarPosicao(id);

        if (posicaoAntiga == -1) {
            System.out.println("Tarefa não encontrada!");
            return;
        }

        if (posicaoAntiga == novaPosicao) {
            System.out.println("A tarefa já está nessa posição!");
            return;
        }

        moverInterno(id, novaPosicao);

        // Registra a posição anterior para o Undo.
        historico.empilhar(() -> moverInterno(id, posicaoAntiga));

        System.out.println("Tarefa movimentada com sucesso!");
    }

    private void moverInterno(int id, int novaPosicao) {

        Tarefa tarefa = removerInterno(id);

        inserirInterno(tarefa, novaPosicao);
    }

    private int buscarPosicao(int id) {

        No aux = inicio;
        int posicao = 1;

        while (aux != null) {

            if (aux.getTarefa().getId() == id) {
                return posicao;
            }

            aux = aux.getProximo();
            posicao++;
        }

        return -1;
    }

    // =========================
    // IMPRESSÃO
    // =========================

    public void imprimirInicio() {

        if (inicio == null) {
            System.out.println("O projeto não possui tarefas.");
            return;
        }

        No aux = inicio;
        int posicao = 1;

        System.out.println("\n===== TAREFAS DO PROJETO =====");

        while (aux != null) {

            System.out.println("Posição: " + posicao);
            System.out.println("ID: " + aux.getTarefa().getId());
            System.out.println("Título: " + aux.getTarefa().getTitulo());
            System.out.println("Descrição: " + aux.getTarefa().getDescricao());
            System.out.println("--------------------------");

            aux = aux.getProximo();
            posicao++;
        }
    }

    public void imprimirFim() {

        if (fim == null) {
            System.out.println("O projeto não possui tarefas.");
            return;
        }

        No aux = fim;
        int posicao = tamanhoLista();

        System.out.println("\n===== TAREFAS EM ORDEM INVERSA =====");

        while (aux != null) {

            System.out.println("Posição: " + posicao);
            System.out.println("ID: " + aux.getTarefa().getId());
            System.out.println("Título: " + aux.getTarefa().getTitulo());
            System.out.println("Descrição: " + aux.getTarefa().getDescricao());
            System.out.println("--------------------------");

            aux = aux.getAnterior();
            posicao--;
        }
    }
}
package TaskFlow;

public class PilhaDesfazer {
    
    private NoHistorico topo;
    private int tamanho;

    public PilhaDesfazer() {
        topo = null;
        tamanho = 0;
    }

    public void empilhar(Runnable acao) {

        NoHistorico novo = new NoHistorico(acao);

        novo.setProximo(topo);
        topo = novo;

        tamanho++;
    }

    public Runnable desempilhar() {

        if (estaVazia()) {
            return null;
        }

        Runnable acao = topo.getAcao();

        topo = topo.getProximo();
        tamanho--;

        return acao;
    }

    public boolean estaVazia() {
        return topo == null;
    }

    public int getTamanho() {
        return tamanho;
    }
}


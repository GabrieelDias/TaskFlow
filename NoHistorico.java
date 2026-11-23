package TaskFlow;

public class NoHistorico {

    private Runnable acao;
    private NoHistorico proximo;

    public NoHistorico(Runnable acao) {
        this.acao = acao;
        this.proximo = null;
    }

    public Runnable getAcao() {
        return acao;
    }

    public NoHistorico getProximo() {
        return proximo;
    }

    public void setProximo(NoHistorico proximo) {
        this.proximo = proximo;
    }
}
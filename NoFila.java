package TaskFlow;

public class NoFila {

    private Demanda demanda;
    private NoFila proximo;

    public NoFila(Demanda demanda) {
        this.demanda = demanda;
        this.proximo = null;
    }

    public Demanda getDemanda() {
        return demanda;
    }

    public NoFila getProximo() {
        return proximo;
    }

    public void setProximo(NoFila proximo) {
        this.proximo = proximo;
    }
}

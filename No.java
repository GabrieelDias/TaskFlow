package TaskFlow;

public class No {
    
    private Tarefa tarefa;
    private No anterior;
    private No proximo;


    public No(Tarefa tarefa){
        this.tarefa = tarefa;
        this.anterior = null;
        this.proximo = null;
    }


    public Tarefa getTarefa() {
        return tarefa;
    }

    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }

    public No getAnterior(){
        return anterior;
    }

    public void setAnterior(No anterior) {
        this.anterior = anterior;
    }

    public No getProximo() {
        return proximo;
    }

    public void setProximo(No proximo) {
        this.proximo = proximo;
    }


}

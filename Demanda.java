package TaskFlow;

public class Demanda {

    private static int proximoId = 1;

    private int id;
    private String titulo;
    private String descricao;

    public Demanda(String titulo, String descricao) {
        this.id = proximoId++;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void exibirDados() {
        System.out.println("ID da demanda: " + id);
        System.out.println("Título: " + titulo);
        System.out.println("Descrição: " + descricao);
    }
}

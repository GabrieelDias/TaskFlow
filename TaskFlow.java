
package TaskFlow;

import java.util.Scanner;

public class TaskFlow {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        PilhaDesfazer historico = new PilhaDesfazer();

        ListaDuplamenteEncadeada lista = new ListaDuplamenteEncadeada(historico);

        FilaTriagem fila = new FilaTriagem();

        int opcao;

        do {

            System.out.println("\n========== TASKFLOW ==========");
            System.out.println("1 - Cadastrar tarefa");
            System.out.println("2 - Inserir tarefa em posição específica");
            System.out.println("3 - Remover tarefa por ID");
            System.out.println("4 - Remover tarefa por título");
            System.out.println("5 - Listar tarefas (início ao fim)");
            System.out.println("6 - Listar tarefas (fim ao início)");
            System.out.println("7 - Mover tarefa");
            System.out.println("8 - Adicionar demanda à triagem");
            System.out.println("9 - Atender próxima demanda");
            System.out.println("10 - Exibir fila de triagem");
            System.out.println("11 - Desfazer última operação");
            System.out.println("12 - Consultar histórico pendente");
            System.out.println("0 - Sair");
            System.out.println("==============================");

            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(entrada.nextLine());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {

                case 1: {

                    System.out.print("Título: ");
                    String titulo = entrada.nextLine();

                    System.out.print("Descrição: ");
                    String descricao = entrada.nextLine();

                    Tarefa tarefa = new Tarefa(titulo, descricao);

                    lista.inserirFinal(tarefa);

                    break;
                }

                case 2: {

                    System.out.print("Título: ");
                    String titulo = entrada.nextLine();

                    System.out.print("Descrição: ");
                    String descricao = entrada.nextLine();

                    System.out.print("Posição desejada: ");

                    int posicao;

                    try {
                        posicao = Integer.parseInt(entrada.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Posição inválida!");
                        break;
                    }

                    Tarefa tarefa = new Tarefa(titulo, descricao);

                    lista.inserirEspec(tarefa, posicao);

                    break;
                }

                case 3: {

                    System.out.print("Digite o ID da tarefa: ");

                    try {
                        int id = Integer.parseInt(entrada.nextLine());
                        lista.removerPorId(id);
                    } catch (NumberFormatException e) {
                        System.out.println("ID inválido!");
                    }

                    break;
                }

                case 4: {

                    System.out.print("Digite o título da tarefa: ");

                    String titulo = entrada.nextLine();

                    lista.removerPorTitulo(titulo);

                    break;
                }

                case 5: {

                    lista.imprimirInicio();

                    break;
                }

                case 6: {

                    lista.imprimirFim();

                    break;
                }

                case 7: {

                    System.out.print("ID da tarefa: ");

                    int id;
                    int posicao;

                    try {
                        id = Integer.parseInt(entrada.nextLine());

                        System.out.print("Nova posição: ");

                        posicao = Integer.parseInt(entrada.nextLine());

                    } catch (NumberFormatException e) {
                        System.out.println("Valor inválido!");
                        break;
                    }

                    lista.moverTarefa(id, posicao);

                    break;
                }

                case 8: {

                    System.out.print("Título da demanda: ");
                    String titulo = entrada.nextLine();

                    System.out.print("Descrição da demanda: ");
                    String descricao = entrada.nextLine();

                    Demanda demanda = new Demanda(titulo, descricao);

                    fila.enfileirar(demanda);

                    break;
                }

                case 9: {

                    Demanda demanda = fila.desenfileirar();

                    if (demanda != null) {

                        Tarefa tarefa = new Tarefa(
                                demanda.getTitulo(),
                                demanda.getDescricao());

                        lista.inserirFinal(tarefa);

                        System.out.println(
                                "Demanda transformada em tarefa ativa!");

                    }

                    break;
                }

                case 10: {

                    fila.exibirFila();

                    break;
                }

                case 11: {

                    if (historico.estaVazia()) {

                        System.out.println(
                                "Não existem operações para desfazer.");

                    } else {

                        Runnable acao = historico.desempilhar();

                        acao.run();

                        System.out.println(
                                "Última operação desfeita com sucesso!");
                    }

                    break;
                }

                case 12: {

                    System.out.println(
                            "Operações disponíveis para desfazer: "
                                    + historico.getTamanho());

                    break;
                }

                case 0: {

                    System.out.println("Encerrando o TaskFlow...");

                    break;
                }

                default: {

                    System.out.println("Opção inválida!");

                    break;
                }
            }

        } while (opcao != 0);

        entrada.close();
    }
}

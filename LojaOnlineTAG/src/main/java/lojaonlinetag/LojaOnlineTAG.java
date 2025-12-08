package lojaonlinetag;

import com.mycompany.lojaonline.entity.Produto;
import com.mycompany.lojaonline.service.BancoDeDados;
import com.mycompany.lojaonline.service.Carrinho;
import pagamento.*; // IMPORTANTE: Importa todos os pagamentos
import java.util.List;
import java.util.Scanner;

public class LojaOnlineTAG {

    public static void main(String[] args) {
        BancoDeDados.inicializar();
        Carrinho carrinho = new Carrinho();
        Scanner scanner = new Scanner(System.in);
        boolean rodando = true;

        System.out.println("=== CONCESSIONÁRIA VEIGA DE ALMEIDA ===");

        while (rodando) {
            System.out.println("\n1. Ver Catálogo por Categoria");
            System.out.println("2. Adicionar ao Carrinho (ID)");
            System.out.println("3. Remover do Carrinho (ID)");
            System.out.println("4. Ver Carrinho e Total");
            System.out.println("5. Finalizar Compra"); // Opção Nova
            System.out.println("0. Sair");
            System.out.print("Escolha: ");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1: // LISTAR (Funcional: Filter)
                    System.out.println("\nEscolha a categoria:");
                    System.out.println("1- Carro | 2- Moto | 3- Peça Carro | 4- Peça Moto | 5- Ver Tudo");
                    int catOp = scanner.nextInt();

                    String filtro = "";
                    if(catOp == 1) filtro = "Carro";
                    else if(catOp == 2) filtro = "Moto";
                    else if(catOp == 3) filtro = "Peça Carro";
                    else if(catOp == 4) filtro = "Peça Moto";

                    List<Produto> listaExibicao;

                    if (catOp == 5) {
                        listaExibicao = BancoDeDados.getCatalogo();
                    } else {
                        listaExibicao = BancoDeDados.filtrarPorCategoria(filtro);
                    }

                    System.out.println("\n--- Produtos Disponíveis ---");
                    for (Produto p : listaExibicao) {
                        System.out.println(p);
                        System.out.println("   Desc: " + p.getDescricao());
                        System.out.println("-----------------------------");
                    }
                    break;

                case 2: // ADICIONAR
                    System.out.print("Digite o ID do produto para adicionar: ");
                    int idAdd = scanner.nextInt();
                    Produto prod = BancoDeDados.buscarPorId(idAdd);
                    if (prod != null) carrinho.adicionar(prod);
                    else System.out.println("Produto não encontrado!");
                    break;

                case 3: // REMOVER (Funcional: removeIf)
                    System.out.print("Digite o ID do produto para remover: ");
                    int idRem = scanner.nextInt();
                    carrinho.remover(idRem);
                    break;

                case 4: // TOTAL (Funcional: Map/Reduce)
                    carrinho.listarItens();
                    System.out.printf("TOTAL FINAL (com impostos/frete): R$ %.2f%n", carrinho.calcularTotal());
                    break;

                case 5: // FINALIZAR COMPRA
                    double total = carrinho.calcularTotal();
                    if (total <= 0) {
                        System.out.println("Seu carrinho está vazio!");
                        break;
                    }

                    // Resumo e Cálculo
                    double valorNoPix = total * 0.90;
                    double valorNoCartao = total;

                    System.out.println("\n--- RESUMO DA COMPRA ---");
                    System.out.printf("Valor dos produtos: R$ %.2f%n", total);

                    System.out.println("\nEscolha a forma de pagamento:");
                    System.out.printf("1 - PIX (R$ %.2f) - Economize 10%%%n", valorNoPix);
                    System.out.printf("2 - Cartão de Crédito (R$ %.2f) - À vista%n", valorNoCartao);

                    System.out.print("Opção: ");
                    int opPag = scanner.nextInt();

                    Pagamento pagamento = null;

                    if (opPag == 1) {
                        pagamento = new PagamentoPix();
                    } else if (opPag == 2) {
                        pagamento = new PagamentoCartao();
                    } else {
                        System.out.println("Opção inválida.");
                        break;
                    }

                    if (pagamento != null) {
                        pagamento.processarPagamento(total);

                        // --- NOVIDADE AQUI ---
                        // 1. Debita do estoque oficial
                        carrinho.baixarEstoque();

                        // 2. Limpa o carrinho para a próxima compra
                        carrinho.limpar();

                        System.out.println("\nObrigado pela preferência! Volte sempre.");
                        // Não vamos fechar o programa (rodando=false) para você poder
                        // consultar o catálogo novamente e ver que o estoque baixou!
                    }
                    break;

                case 0:
                    rodando = false;
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }
        scanner.close();
    }
}
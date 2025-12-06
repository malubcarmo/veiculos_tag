package com.mycompany.lojaonline.pagamento;

public class PagamentoCartao extends Pagamento {

    @Override
    public void processarPagamento(double valorTotal) {
        System.out.println("\n--- PROCESSANDO CARTÃO DE CRÉDITO ---");
        System.out.println("Conectando com a operadora...");

        // Simulação de parcelamento
        System.out.println("Opções de parcelamento:");
        System.out.printf("1x de R$ %.2f (sem juros)%n", valorTotal);

        System.out.println(">> Transação Aprovada!");
    }
}
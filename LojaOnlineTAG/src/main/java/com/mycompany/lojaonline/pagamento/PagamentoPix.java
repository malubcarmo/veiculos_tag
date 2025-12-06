package com.mycompany.lojaonline.pagamento;


public class PagamentoPix extends Pagamento {

    @Override
    public void processarPagamento(double valorTotal) {
        // Regra: Pix tem 10% de desconto
        double valorComDesconto = valorTotal * 0.90;

        System.out.println("\n--- PROCESSANDO PIX ---");
        System.out.println("Gerando chave aleatória...");
        System.out.println("Chave: 00020126580014BR.GOV.BCB.PIX...");
        System.out.printf("Valor original: R$ %.2f%n", valorTotal);
        System.out.printf("Valor com 10%% de desconto: R$ %.2f%n", valorComDesconto);
        System.out.println(">> Pagamento Confirmado!");
    }
}
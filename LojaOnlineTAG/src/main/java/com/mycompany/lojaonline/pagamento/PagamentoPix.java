package com.mycompany.lojaonline.pagamento;

import java.util.UUID; // Importação necessária para gerar o código

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
        
        // --- GERADOR DE CÓDIGO DE RASTREIO ---
        // Gera um código aleatório, pega os primeiros 8 caracteres e deixa maiúsculo
        String codigoRastreio = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        System.out.println(">> CÓDIGO DE RASTREIO: TRK-" + codigoRastreio);
        System.out.println("Guarde este código para acompanhar seu pedido.");
    }
}

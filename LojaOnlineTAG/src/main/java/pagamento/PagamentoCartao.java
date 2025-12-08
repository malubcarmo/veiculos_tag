package pagamento;

import java.util.UUID; //importação para o código de rastreio

public class PagamentoCartao extends Pagamento {

    @Override
    public void processarPagamento(double valorTotal) {
        System.out.println("\n--- PROCESSANDO CARTÃO DE CRÉDITO ---");
        System.out.println("Conectando com a operadora...");
        
        System.out.println("Opções de pagamento:");
        System.out.printf("1x de R$ %.2f (sem juros)%n", valorTotal);
        
        System.out.println(">> Transação Aprovada!");
        
        //gera um código aleatório para a compra
        String codigoRastreio = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        System.out.println(">> CÓDIGO DE RASTREIO: TRK-" + codigoRastreio);
        System.out.println("Guarde este código para acompanhar seu pedido.");
    }
}

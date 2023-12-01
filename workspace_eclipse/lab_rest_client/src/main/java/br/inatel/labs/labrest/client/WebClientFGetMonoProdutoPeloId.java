package br.inatel.labs.labrest.client;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import br.inatel.labs.labrest.client.model.dto.ProdutoDTO;
import reactor.core.publisher.Mono;

public class WebClientFGetMonoProdutoPeloId {

	public static void  main(String[] args) {
		
		try {
		
		Mono<ProdutoDTO> monoProduto = WebClient.create(Constantes.BASE_URL)
			.get()
			.uri("/produto/2")
			.retrieve()
			.bodyToMono( ProdutoDTO.class);
		
		monoProduto.subscribe();
	
		ProdutoDTO produtoRetornado = monoProduto.block();
		
		System.out.println("Produto encontrado");
		System.out.println(produtoRetornado);
		
		}catch(WebClientResponseException e) {
			System.out.println("Status code: " + e.getStatusCode());
			System.out.println("Mensagem: " + e.getMessage());
		}
	}
	
}

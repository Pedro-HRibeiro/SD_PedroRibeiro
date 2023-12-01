package br.inatel.labs.labrest.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.reactive.function.client.WebClient;

import br.inatel.labs.labrest.client.model.dto.ProdutoDTO;
import reactor.core.publisher.Flux;

public class WebClientGetFluxProduto {
	
	
	public static void main(String[] args) {
		
		ArrayList<ProdutoDTO> listaProduto = new ArrayList<ProdutoDTO>();
		
		Flux<ProdutoDTO> fluxProduto = WebClient.create(Constantes.BASE_URL)
				.get()
				.uri("/produto")
				.retrieve()
				.bodyToFlux(ProdutoDTO.class);
		
		fluxProduto.subscribe( p -> listaProduto.add( p ));
		
		fluxProduto.blockLast(); //bloqueia até o último produto chegar...
		
		System.out.println("Lista de produtos: ");
		System.out.println(listaProduto);
		
	}
	
}
 
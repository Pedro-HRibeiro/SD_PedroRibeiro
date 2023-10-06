package br.inatel.labs.labjpa.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.inatel.labs.labjpa.entity.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
@Transactional
public class ProdutoService {

	@PersistenceContext
	private EntityManager em;
	
	
	public Produto salvar(Produto p) {
		p = em.merge(p);
		return p;
	}
	
	
	public Produto buscarPeloId(Long id) {
		Produto produtoEncontrado = em.find(Produto.class, id);
		return produtoEncontrado;
	}
	
	
	public List<Produto> listar(){
		List<Produto> produtos = em.createQuery("select p from Produto p", Produto.class).getResultList();//JPQL
		return produtos;
	}
	
	
	public void remove(Produto p) {
		p = em.merge(p); //Precisamos entender o ciclo de vida de entidades
		em.remove(p);
	}
	
	
}

	

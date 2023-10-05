package br.inatel.labs.labjpa.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

@Entity
public class NotaCompra {

	@Id
	private Long id;
	
	@NotNull
	@Past
	private LocalDate dataEmissao;
	
	@ManyToOne
	private Fornecedor fornecedor;
	
	@OneToMany(mappedBy = "notaCompra")
	private List<NotaCompraItem> listaNotaCompraItem; 
	
	
	//Calcula o total da nota somando os totais de cada item
	public BigDecimal getCalculoTotalNota() {
		BigDecimal totalNota = listaNotaCompraItem.stream()
			.map( i -> i.getCalculoTotalItem() )
			.reduce( BigDecimal.ZERO, BigDecimal::add );
		
		return totalNota;
	}


	
	//acessores...
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public LocalDate getDataEmissao() {
		return dataEmissao;
	}


	public void setDataEmissao(LocalDate dataEmissao) {
		this.dataEmissao = dataEmissao;
	}


	public Fornecedor getFornecedor() {
		return fornecedor;
	}


	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}


	public List<NotaCompraItem> getListaNotaCompraItem() {
		return listaNotaCompraItem;
	}


	public void setListaNotaCompraItem(List<NotaCompraItem> listaNotaCompraItem) {
		this.listaNotaCompraItem = listaNotaCompraItem;
	}



	@Override
	public int hashCode() {
		return Objects.hash(id);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NotaCompra other = (NotaCompra) obj;
		return Objects.equals(id, other.id);
	}



	@Override
	public String toString() {
		return "NotaCompra [id=" + id + ", dataEmissao=" + dataEmissao + "]";
	}
	
	
	
}


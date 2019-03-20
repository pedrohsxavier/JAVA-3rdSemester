package modelo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Venda {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String vendedor;
	@OneToOne(cascade=CascadeType.ALL)
	private Produto produto;
	
	
	public Venda() {}
	public Venda(String vendedor, Produto produto) {
		super();
		this.vendedor = vendedor;
		this.produto = produto;
		produto.baixarEstoque();
	}
	public String getVendedor() {
		return vendedor;
	}
	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}

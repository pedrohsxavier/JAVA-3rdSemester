/**IFPB - Curso SI - Disciplina de POB
 * @author Prof Fausto Ayres
 */
package daojpa;


import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import modelo.Produto;

public class DAOProduto  extends DAO<Produto>{

	public Produto readByNome (String n){
		try{
			Query q = manager.createQuery("select p from Produto p where p.nome= '" + n +"'");
			return (Produto) q.getSingleResult();

		}catch(NoResultException e){			
			return null;
		}
	}

	//consultas
	@SuppressWarnings("unchecked")
	public  List<Produto> consultarProdutoSemPareteleira() {
		Query q = manager.createQuery(
				"select p from Produto p where p.prateleira is null");
		return q.getResultList();
	}


	@SuppressWarnings("unchecked")
	public List<Produto> consultarVizinhos(String nome){
		Query q = manager.createQuery(
				"select p from Produto p where p.prateleira in (select p.prateleira from Produto p where p.nome=:x) and p.nome<>:x ");
		q.setParameter("x", nome);
		return q.getResultList();
	}

	public long consultarTotalProdutos() {
		Query q = manager.createQuery(
				"select count(p) from Produto p");
		return (Long) q.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Produto> consultarProdutosDaPrateleira(int id){
		Query q = manager.createQuery(
				"select p from Prateleira prat JOIN prat.produtos p  where prat.id= :n");
		q.setParameter("n", id);
		return q.getResultList();
	}
}

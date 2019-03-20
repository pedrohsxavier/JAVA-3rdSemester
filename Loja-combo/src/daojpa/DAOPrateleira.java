/**IFPB - Curso SI - Disciplina de POB
 * @author Prof Fausto Ayres
 */
package daojpa;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import modelo.Prateleira;

public class DAOPrateleira  extends DAO<Prateleira>{

	@SuppressWarnings("unchecked")
	public List<Prateleira> consultarPrateleirasVazias(){
		Query q = manager.createQuery(
				"select p from Prateleira p where p.produtos is empty");
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Prateleira> consultarPrateleiraComDoisProdutos(){
		Query q = manager.createQuery(
				"select p from Prateleira p where SIZE(p.produtos) < 2");
		return q.getResultList();
	}


	public Prateleira consultarPrateleiraDoProduto(String nome) {
		try{
			Query q = manager.createQuery(
					"select p.prateleira from Produto p where p.nome= ?1 ");
			q.setParameter(1, nome);
			return (Prateleira) q.getSingleResult();
			
		}catch(NoResultException e){			
			return null;
		}
	}

}

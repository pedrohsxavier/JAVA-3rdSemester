package aplicacao;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.events.CancellableObjectEventArgs;
import com.db4o.events.CommitEventArgs;
import com.db4o.events.Event4;
import com.db4o.events.EventListener4;
import com.db4o.events.EventRegistry;
import com.db4o.events.EventRegistryFactory;
import com.db4o.query.Query;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Persistencia de Objetos
 * Prof. Fausto Maranhão Ayres
 **********************************/
public class IDControl {
	// Faz a geração automatica de IDs para qualquer classe que implementa
	// a interface IDInterface
	protected static ObjectContainer manager;
	private static RegistroID registro;		// descreve o id gerado para cada classe
	private static boolean gerounovoid;			


	//	=============================================	
	public static void registrarManager(ObjectContainer man){
		manager = man;
		EventRegistry eventRegistry = EventRegistryFactory.forObjectContainer(manager);

		// PRE-PERSIST - antes de persistir
		eventRegistry.creating().addListener(new EventListener4<CancellableObjectEventArgs>() {
			public void onEvent(Event4<CancellableObjectEventArgs> event4, CancellableObjectEventArgs args) {

				// verificar se o objeto persistido implementa a interface IDInterface
				Object objeto = args.object();
				if(objeto instanceof IDInterface){
					int novoid;
					String nomedaclasse = objeto.getClass().getName();

					// buscar a registro associada a classe do objeto persistido
					Query q = manager.query();
					q.constrain(RegistroID.class);
					q.descend("nomedaclasse").constrain(nomedaclasse);		
					List<RegistroID> resultados = q.execute();
					if (resultados.size()>0) {
						registro = resultados.get(0);
						novoid = registro.getUltimoid() + 1;
						registro.setUltimoid(novoid);
					}
					else{
						novoid=1;	
						registro = new RegistroID(nomedaclasse, novoid);
					}

					// atribuir o novo id gerado ao objeto que está sendo persistido
					((IDInterface) objeto).setId(novoid); 
//					System.out.println("--->gerando id="+novoid+" para a classe="+nomedaclasse);
					gerounovoid = true;
				}
			}});

		// POST-COMMIT  - apos commit
		eventRegistry.committed().addListener(new EventListener4<CommitEventArgs>() {
			public void onEvent(Event4<CommitEventArgs> commitEventArgsEvent4, CommitEventArgs args) {
				//Atualizar a RegistroID no banco
				if (gerounovoid) {
					gerounovoid = false;
					manager.store(registro);
					manager.ext().purge(registro);  //limpar cache do manager
				}
			}});     		
	}
}

//============================================================
class RegistroID{
//armazena o nome da classe e o ultimo id desta classe
//============================================================
	private String nomedaclasse;
	private int ultimoid;
	public RegistroID(String nomedaclasse, int ultimoid) {
		this.nomedaclasse = nomedaclasse;
		this.ultimoid = ultimoid;
	}		
	public int getUltimoid() {
		return ultimoid;
	}
	public void setUltimoid(int ultimoid) {
		this.ultimoid = ultimoid;
	}
	public String getNomedaclasse() {
		return nomedaclasse;
	}
	public void setNomedaclasse(String nomedaclasse) {
		this.nomedaclasse = nomedaclasse;
	}		
}

package fr.naf.hbjpa.hbjpadetached.notmergin;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DaoNotMergin {

	private static EntityManager em1;	
	private static EntityTransaction ts1;
	private static EntityManagerFactory emf;

	
	public DaoNotMergin() {
		 emf = Persistence.createEntityManagerFactory("persistence");    					         
		 em1 = emf.createEntityManager();
		 ts1= em1.getTransaction();
		 
	
	}
	
	public Pays ajouterPays() {
		Pays pays =new Pays();
		pays.setNom("France");
		pays.setSuperficie("30009km");
		ts1.begin();
        em1.persist(pays);                 
        ts1.commit(); 
		return pays;
	}
	
	public void ajouterVille(Pays pays) {
		Ville ville1 =new Ville();
		ville1.setNom("Paris");
		ville1.setNbrHabitant(4000);
		ville1.setPays(pays);
		Ville ville2 =new Ville();
		ville2.setNom("Bordeau");
		ville2.setNbrHabitant(6000);
		ville2.setPays(pays);
		ts1.begin();
        em1.persist(ville1);
        em1.persist(ville2);      
        ts1.commit(); 
	}
	
	public void modifierVilleAvecPays(long paysid) {
	
		em1 = emf.createEntityManager();
		ts1= em1.getTransaction();		
		
		Pays pays =em1.find(Pays.class, paysid);
		Set<Ville> villes = pays.getVilles();
		int nbrVilles = villes.size();
		
		Ville ville = null;
		for(Ville vil : villes) {			
				ville =vil;
			    break;
		}			
		
		
		//modification des objets en dehors du contexte c'est à dire des objets dectaches
		pays.setSuperficie("6700km");
		ville.setNbrHabitant(98700);
		
		
		//sotckage dans la base dans un autre contexte de persistence
		ts1.begin();
		em1.merge(pays);
		ts1.commit();
		em1.close();
	}
	
	
}

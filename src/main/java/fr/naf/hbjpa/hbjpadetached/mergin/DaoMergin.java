package fr.naf.hbjpa.hbjpadetached.mergin;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DaoMergin {

	private static EntityManagerFactory emf;
	private static EntityManager em1;
	private static EntityManager em2;
	private static EntityTransaction ts1;
	private static EntityTransaction ts2;
	
	public DaoMergin() {
		 emf = Persistence.createEntityManagerFactory("persistence");    	
		
		//premier contexte persistence		          
		 em1 = emf.createEntityManager();
		 ts1= em1.getTransaction();
		 
		 //dexieme contexte persistence		    	         
		 em2 = emf.createEntityManager();
		 ts2= em2.getTransaction();
	}
	
	public Societe ajouterSociete() {
		Societe societe =new Societe();
		societe.setNom("Natixis");
		societe.setType("G");
		ts1.begin();
        em1.persist(societe);                 
        ts1.commit(); 
		return societe;
	}
	
	public void ajouterSalarie(Societe societe) {
		Salarie salarie1 =new Salarie();
		salarie1.setNom("Tom");
		salarie1.setSalaire(300);
		salarie1.setSociete(societe);
		Salarie salarie2 =new Salarie();
		salarie2.setNom("Kim");
		salarie2.setSalaire(250);
		salarie2.setSociete(societe);
		ts1.begin();
        em1.persist(salarie1);
        em1.persist(salarie2);      
        ts1.commit();         
	}
	
	public void modifierSalarieAvecSociete(long societeid) {
		em1 = emf.createEntityManager();
		ts1= em1.getTransaction();
		
		//recupere l'objet dans le premier contexte persistence JPA em1
		Societe societe =em1.find(Societe.class, societeid);
		Set<Salarie> salaries = societe.getSalaries();
		int nbrSalaires = salaries.size();
		
		Salarie salarie = null;
		for(Salarie sal : salaries) {			
				salarie =sal;
			     break;
		}		
		em1.close();	
		
		
		//modification des objets en dehors du contexte c'est à dire des objets dectaches
		societe.setType("PME");
		salarie.setSalaire(4500);
		
		
		//sotckage dans la base dans un autre contexte de persistence
		ts2.begin();
		em2.merge(societe);
		ts2.commit();
	}
	
	
}

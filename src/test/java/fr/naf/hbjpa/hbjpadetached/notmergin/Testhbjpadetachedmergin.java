package fr.naf.hbjpa.hbjpadetached.notmergin;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;


import fr.naf.hbjpa.hbjpadetached.mergin.DaoMergin;
import fr.naf.hbjpa.hbjpadetached.mergin.Societe;



public class Testhbjpadetachedmergin {

	@Test
	public void testDaoMergin() {
		DaoMergin dao= new DaoMergin();
		Societe ajouterSociete = dao.ajouterSociete();
		dao.ajouterSalarie(ajouterSociete);
		dao.modifierSalarieAvecSociete(ajouterSociete.getId());
		assertNotNull(ajouterSociete.getId());
	}
}

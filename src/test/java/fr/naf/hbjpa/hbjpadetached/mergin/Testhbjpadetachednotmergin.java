package fr.naf.hbjpa.hbjpadetached.mergin;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;


import fr.naf.hbjpa.hbjpadetached.notmergin.DaoNotMergin;
import fr.naf.hbjpa.hbjpadetached.notmergin.Pays;


public class Testhbjpadetachednotmergin {

	@Test
	public void testDaoNotMergin() {
		DaoNotMergin dao= new DaoNotMergin();
		Pays ajouterPays = dao.ajouterPays();
		dao.ajouterVille(ajouterPays);
		dao.modifierVilleAvecPays(ajouterPays.getId());
		assertNotNull(ajouterPays.getId());
	}
}

package mg.itu.joharisoa.tpbanquejoharisoa.service;

import mg.itu.joharisoa.tpbanquejoharisoa.entity.CompteBancaire;
import java.util.List;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

/**
 * Gère la persistance des Comptes Bancaire.
 */
@RequestScoped
public class GestionnaireCompte {

    @PersistenceContext(unitName = "compteBancairePU")
    private EntityManager em;

    public List<CompteBancaire> getAllComptesBancaire() {
       Query query = em.createNamedQuery("CompteBancaire.findAll");
       return query.getResultList();
    }

    @Transactional
    public CompteBancaire update(CompteBancaire compte) {
       return em.merge(compte);
    }

    @Transactional
    public void persist(CompteBancaire compte) {
       em.persist(compte);
    }
}
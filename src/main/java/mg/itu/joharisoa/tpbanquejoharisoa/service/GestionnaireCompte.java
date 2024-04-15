package mg.itu.joharisoa.tpbanquejoharisoa.service;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.enterprise.context.ApplicationScoped;
import mg.itu.joharisoa.tpbanquejoharisoa.entity.CompteBancaire;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

/**
 * Gère la persistance des Comptes Bancaire.
 */
@ApplicationScoped
@DataSourceDefinition(
        className = "com.mysql.cj.jdbc.MysqlDataSource",
        name = "java:app/jdbc/banque",
        serverName = "localhost",
        portNumber = 3306,
        user = "root", // nom et
        password = "root", // mot de passe que vous avez donnés lors de la création de la base de données
        databaseName = "banque",
        properties = {
            "useSSL=false",
            "allowPublicKeyRetrieval=true",
            "driverClass=com.mysql.cj.jdbc.Driver"
        }
)
public class GestionnaireCompte {

    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;

    public List<CompteBancaire> getAllComptes() {
        Query query = em.createNamedQuery("CompteBancaire.findAll");
        return query.getResultList();
    }
    
   public CompteBancaire findById(long idCompte) {
        return em.find(CompteBancaire.class, idCompte);
    }

    @Transactional
    public void transferer(CompteBancaire source, CompteBancaire destination,
            int montant) {
        source.retirer(montant);
        destination.deposer(montant);
        update(source);
        update(destination);
    }

    @Transactional
    public CompteBancaire update(CompteBancaire compte) {
        return em.merge(compte);
    }

    @Transactional
    public void creerCompte(CompteBancaire c) {
        em.persist(c);
    }

    public long nbComptes() {
        Query query = em.createNamedQuery("CompteBancaire.count");
        return (long) query.getSingleResult();
    }
}

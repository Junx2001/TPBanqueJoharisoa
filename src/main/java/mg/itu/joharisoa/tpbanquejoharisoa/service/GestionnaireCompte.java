package mg.itu.joharisoa.tpbanquejoharisoa.service;

import jakarta.annotation.sql.DataSourceDefinition;
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
@DataSourceDefinition (
    className="com.mysql.cj.jdbc.MysqlDataSource",
    name="java:app/jdbc/banque",
    serverName="localhost",
    portNumber=3306,
    user="root",              // nom et
    password="root", // mot de passe que vous avez donnés lors de la création de la base de données
    databaseName="banque",
    properties = {
      "useSSL=false",
      "allowPublicKeyRetrieval=true",
      "driverClass=com.mysql.cj.jdbc.Driver"
    }
)
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
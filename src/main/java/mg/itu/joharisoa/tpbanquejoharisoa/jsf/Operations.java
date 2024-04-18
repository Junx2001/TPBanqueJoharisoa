/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.joharisoa.tpbanquejoharisoa.jsf;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import java.util.List;
import mg.itu.joharisoa.tpbanquejoharisoa.entity.CompteBancaire;
import mg.itu.joharisoa.tpbanquejoharisoa.entity.OperationBancaire;
import mg.itu.joharisoa.tpbanquejoharisoa.service.GestionnaireCompte;

/**
 *
 * @author ratsi
 */
@Named(value = "operations")
@RequestScoped
public class Operations {

    private long idCompte;
    private CompteBancaire compte;
    
    private List<OperationBancaire> operations;
    
    @Inject
    private GestionnaireCompte gestionnaireCompte;

    public long getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(long idCompte) {
        this.idCompte = idCompte;
    }

    public CompteBancaire getCompte() {
        return compte;
    }

    public List<OperationBancaire> getOperations() {
        return compte.getOperations();
    }

    public void setOperations(List<OperationBancaire> operations) {
        this.operations = operations;
    }
    
    
    public void loadCompte() {
        compte = gestionnaireCompte.findById(idCompte); 
    }

    /**
     * Creates a new instance of Operations
     */
    public Operations() {
    }

}

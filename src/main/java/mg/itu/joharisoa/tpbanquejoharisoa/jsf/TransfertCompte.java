/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.joharisoa.tpbanquejoharisoa.jsf;


import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import mg.itu.joharisoa.tpbanquejoharisoa.entity.CompteBancaire;
import mg.itu.joharisoa.tpbanquejoharisoa.service.GestionnaireCompte;

@Named(value = "transfert")
@ViewScoped
public class TransfertCompte implements Serializable{
    private long idSource;
    private long idDestination;
    private long montant;
    
    
    @Inject
    private GestionnaireCompte gestionnaireCompte;

   
    /**
     * Creates a new instance of TransfertCompte
     */
    public TransfertCompte() {
    }


    public long getIdSource() {
        return idSource;
    }

    public void setIdSource(long idSource) {
        this.idSource = idSource;
    }

    public long getIdDestination() {
        return idDestination;
    }

    public void setIdDestination(long idDestination) {
        this.idDestination = idDestination;
    }

    public long getMontant() {
        return montant;
    }

    public void setMontant(long montant) {
        this.montant = montant;
    }
    
    

    public String transferer_argent() {
        CompteBancaire source = gestionnaireCompte.findById(idSource);
        CompteBancaire destination = gestionnaireCompte.findById(idDestination);

        gestionnaireCompte.transferer(source, destination, (int)montant);
        return "Liste_Comptes";
    }
    
}

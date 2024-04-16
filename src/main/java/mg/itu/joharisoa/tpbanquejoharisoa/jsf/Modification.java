/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.joharisoa.tpbanquejoharisoa.jsf;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import mg.itu.joharisoa.tpbanquejoharisoa.entity.CompteBancaire;
import mg.itu.joharisoa.tpbanquejoharisoa.jsf.util.Util;
import mg.itu.joharisoa.tpbanquejoharisoa.service.GestionnaireCompte;

/**
 *
 * @author ratsi
 */
@Named(value = "modifier")
@ViewScoped
public class Modification implements Serializable {
    
    private Long id;
    private CompteBancaire compte;
    private String nom;
    
    @Inject
    private GestionnaireCompte gestionnaireCompte;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public CompteBancaire getCompte() {
        return compte;
    }
    
    public void setCompte(CompteBancaire compte) {
        this.compte = compte;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Creates a new instance of Modification
     */
    public Modification() {
    }
    
    public void loadCompte() {
        compte = gestionnaireCompte.findById(id);
        nom = compte.getNom();
    }
    
    public String modifierNom() {
        compte = gestionnaireCompte.update(compte);
        Util.addFlashInfoMessage("Nom " + nom + " changé en " + compte.getNom());
        return "Liste_Comptes?faces-redirect=true";
    }
    
}

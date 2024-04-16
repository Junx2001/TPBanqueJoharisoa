/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.joharisoa.tpbanquejoharisoa.jsf;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.inject.Inject;
import jakarta.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import mg.itu.joharisoa.tpbanquejoharisoa.entity.CompteBancaire;
import mg.itu.joharisoa.tpbanquejoharisoa.jsf.util.Util;
import mg.itu.joharisoa.tpbanquejoharisoa.service.GestionnaireCompte;

@Named(value = "ajoutCompte")
@RequestScoped
public class AjoutCompte implements Serializable {

    private String nom;
    
    @PositiveOrZero
    private int solde;

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    /**
     * Creates a new instance of AjoutCompte
     */
    public AjoutCompte() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public String ajouter_compte() {
        gestionnaireCompte.creerCompte(new CompteBancaire(nom, solde));
        Util.addFlashInfoMessage("Le compte au nom de " + nom + " avec un solde initial de "+ solde +" a été créé avec succès");
        return "Liste_Comptes?faces-redirect=true";
  

      }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.joharisoa.tpbanquejoharisoa.jsf;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import mg.itu.joharisoa.tpbanquejoharisoa.entity.CompteBancaire;
import mg.itu.joharisoa.tpbanquejoharisoa.jsf.util.Util;
import mg.itu.joharisoa.tpbanquejoharisoa.service.GestionnaireCompte;

@Named(value = "transfert")
@RequestScoped
public class TransfertCompte implements Serializable {

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

        boolean erreur = false;
        CompteBancaire source = gestionnaireCompte.findById(idSource);
        if (source == null) {
            // Message d'erreur associé au composant source ; form:source est l'id client
            // si l'id du formulaire est "form" et l'id du champ de saisie de l'id de la source est "source"
            // dans la page JSF qui lance le transfert.
            Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:source");
            erreur = true;
        } else {
            if (source.getSolde() < montant) { // à compléter pour le cas où le solde du compte source est insuffisant...
                Util.messageErreur("Solde du compte source insuffisant");
                erreur = true;
            }
        }
        CompteBancaire destination = gestionnaireCompte.findById(idDestination);
        if (destination == null) {
            // Message d'erreur associé au composant destination ; form:destination est l'id client
            // si l'id du formulaire est "form" et l'id du champ de saisie de l'id de la source est "destination"
            // dans la page JSF qui lance le transfert.
            Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:destination");
            erreur = true;
        } 
        if (erreur) { // en cas d'erreur, rester sur la même page
            return null;
        }
        gestionnaireCompte.transferer(source, destination, (int) montant);
        // Message de succès ; addFlash à cause de la redirection.
        // ...Complétez pour faire apparaitre le montant et les noms des 2 propriétaires des comptes.
        Util.addFlashInfoMessage("Transfert correctement effectué de "+montant+" entre "+source.getNom()+" et "+destination.getNom());
        return "Liste_Comptes?faces-redirect=true";
    }

}

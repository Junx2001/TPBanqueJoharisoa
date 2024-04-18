/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.joharisoa.tpbanquejoharisoa.jsf;

import java.io.Serializable;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.validator.ValidatorException;
import jakarta.persistence.OptimisticLockException;

import mg.itu.joharisoa.tpbanquejoharisoa.jsf.util.Util;
import mg.itu.joharisoa.tpbanquejoharisoa.entity.CompteBancaire;
import mg.itu.joharisoa.tpbanquejoharisoa.service.GestionnaireCompte;

/**
 * Backing bean pour la page qui effectue des mouvements sur un compte.
 */
@Named(value = "mouvement")
@ViewScoped
public class Mouvement implements Serializable {

    private Long id;
    private CompteBancaire compte;
    private String typeMouvement;
    private int montant;

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public String getTypeMouvement() {
        return typeMouvement;
    }

    public void setTypeMouvement(String typeMouvement) {
        this.typeMouvement = typeMouvement;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CompteBancaire getCompte() {
        return compte;
    }

    public void loadCompte() {
        compte = gestionnaireCompte.findById(id);
    }

    /**
     * Méthode validatrice pour le montant du mouvement. Remarque : La méthode
     * doit toujours avoir cette signature.
     *
     * @param fc
     * @param composant le composant JSF dans lequel on valide.
     * @param valeur valeur à valider (le montant pour ce cas)
     */
    public void validateSolde(FacesContext fc, UIComponent composant, Object valeur) {
        UIInput composantTypeMouvement = (UIInput) composant.findComponent("typeMouvement");
        // Il faut savoir si c'est un retrait ou un dépôt.
        // Sans entrer dans les détails, il faut parfois utiliser
        // getSubmittedValue() à la place de getLocalValue.
        // typeMouvement n'est pas encore mis tant que la validation n'est pas finie.
        String valeurTypeMouvement = (String) composantTypeMouvement.getLocalValue();
        if (valeurTypeMouvement == null) {
            // Pour le cas où l'utilisateur a soumis le formulaire sans indiquer le type du mouvement,
            // Le test valeurTypeMouvement.equals("retrait") ci-dessous génèrera une erreur car
            // il est exécuté avant que JSF ne vérifie que l'utilisateur a bien choisi
            // entre ajout et retrait (le choix est requis dans la page mouvement.xhtml)
            return;
        }
        if (valeurTypeMouvement.equals("retrait")) {
            int retrait = (int) valeur;
            if (compte.getSolde() < retrait) {
                FacesMessage message
                        = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Le retrait doit être inférieur au solde du compte",
                                "Le retrait doit être inférieur au solde du compte");
                throw new ValidatorException(message);
            }
        }
    }

    public String enregistrerMouvement() {
        try {
            if (typeMouvement.equals("ajout")) {
                gestionnaireCompte.deposer(compte, montant);
            } else {
                gestionnaireCompte.retirer(compte, montant);
            }
            Util.addFlashInfoMessage(typeMouvement + " de " + montant
                    + " enregistré sur compte de " + compte.getNom());
            return "Liste_Comptes?faces-redirect=true";

        } catch (OptimisticLockException ex) {
            Util.messageErreur("Le compte de " + compte.getNom()
                    + " a été modifié ou supprimé par un autre utilisateur !");
            return null; // pour rester sur la page s'il y a une exception
        } // end try
    }

}

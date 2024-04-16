/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.joharisoa.tpbanquejoharisoa.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;
import mg.itu.joharisoa.tpbanquejoharisoa.entity.CompteBancaire;
import mg.itu.joharisoa.tpbanquejoharisoa.jsf.util.Util;
import mg.itu.joharisoa.tpbanquejoharisoa.service.GestionnaireCompte;

/**
 *
 * @author ratsi
 */
@Named(value = "listeComptes")
@ViewScoped
public class ListeComptes implements Serializable {

    private List<CompteBancaire> compteList;

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    /**
     * Creates a new instance of ListeComptes
     */
    public ListeComptes() {
    }

    /**
     * Retourne la liste des Comptes pour affichage dans une DataTable.
     *
     * @return List
     */
    public List<CompteBancaire> getAllComptes() {
        if (compteList == null) {
            compteList = gestionnaireCompte.getAllComptes();
        }
        return compteList;
    }

    public String supprimerCompte(CompteBancaire compteBancaire) {
        gestionnaireCompte.supprimerCompte(compteBancaire);
        Util.addFlashInfoMessage("Compte de " + compteBancaire.getNom() + " supprimé");
        return "Liste_Comptes?faces-redirect=true";
    }

}

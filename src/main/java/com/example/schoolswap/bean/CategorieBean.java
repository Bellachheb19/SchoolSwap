package com.example.schoolswap.bean;

import com.entity.Categorie;
import com.example.schoolswap.DAO.CategorieDAO;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class CategorieBean implements Serializable {

    private Integer idCategorieSelectionnee;
    private String nomCategorie;
    private String typeArticle = "Livre"; // Stocke le choix du menu déroulant sous forme de texte

    private boolean modeEdition = false;
    private List<Categorie> categories;
    private CategorieDAO categorieDAO = new CategorieDAO();

    @PostConstruct
    public void init() {
        chargerCategories();
    }

    private void chargerCategories() {
        categories = categorieDAO.findAll();
    }

    public String enregistrer() {
        if (nomCategorie != null && !nomCategorie.trim().isEmpty()) {
            if (modeEdition) {
                Categorie c = categorieDAO.findById(idCategorieSelectionnee);
                if (c != null) {
                    c.setNom(nomCategorie);
                    // Conversion du String en Enum TypeArticle
                    c.setType(Categorie.TypeArticle.valueOf(typeArticle));
                    categorieDAO.updateCategorie(c);
                }
            } else {
                Categorie c = new Categorie();
                c.setNom(nomCategorie);
                // Conversion du String en Enum TypeArticle
                c.setType(Categorie.TypeArticle.valueOf(typeArticle));
                categorieDAO.ajouterCategorie(c);
            }
        }
        return annuler();
    }

    public String preparerModification(Categorie c) {
        this.idCategorieSelectionnee = c.getId(); // Appel du bon getter
        this.nomCategorie = c.getNom();
        // Conversion de l'Enum en String pour le menu déroulant
        this.typeArticle = (c.getType() != null) ? c.getType().name() : "Livre";
        this.modeEdition = true;
        return "/admin_categories.xhtml?faces-redirect=true";
    }

    public String supprimer(int id) {
        categorieDAO.deleteCategorie(id);
        return annuler();
    }

    public String annuler() {
        this.idCategorieSelectionnee = null;
        this.nomCategorie = "";
        this.typeArticle = "Livre";
        this.modeEdition = false;
        chargerCategories();
        return "/admin_categories.xhtml?faces-redirect=true";
    }

    // --- GETTERS & SETTERS ---
    public String getNomCategorie() { return nomCategorie; }
    public void setNomCategorie(String nomCategorie) { this.nomCategorie = nomCategorie; }

    public String getTypeArticle() { return typeArticle; }
    public void setTypeArticle(String typeArticle) { this.typeArticle = typeArticle; }

    public boolean isModeEdition() { return modeEdition; }
    public void setModeEdition(boolean modeEdition) { this.modeEdition = modeEdition; }

    public List<Categorie> getCategories() { return categories; }
    public void setCategories(List<Categorie> categories) { this.categories = categories; }
}
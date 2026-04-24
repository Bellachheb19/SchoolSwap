package com.example.schoolswap.bean;



import com.entity.Article;
import com.example.schoolswap.DAO.ArticleDAO;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class AdminArticleBean implements Serializable {

    private List<Article> articles;
    private ArticleDAO articleDAO = new ArticleDAO();

    @PostConstruct
    public void init() {
        // Au chargement, on récupère tous les articles pour l'espace administrateur
        // On peut utiliser une méthode existante (ex: getAnnoncesRecentes si elle ramène tout sans limite)
        // ou en créer une spécifique pour l'admin.
        // Ici on suppose que getAnnoncesRecentes() retourne tous les articles.
        articles = articleDAO.getAnnoncesRecentes();
    }

    // --- Getters et Setters ---
    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    // Méthode appelée par le bouton "Supprimer"
    public String supprimerArticle(int id) throws Exception {
        // 1. On supprime l'article robuste
        ArticleDAO.deletearticle(id);

        // 2. On recharge la liste
        articles = articleDAO.getAnnoncesRecentes();

        // 3. Rafraichir la page
        return "/admin_articles.xhtml?faces-redirect=true";
    }
}
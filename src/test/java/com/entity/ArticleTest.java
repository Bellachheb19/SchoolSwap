package com.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ArticleTest {

    @Test
    void testDefaultConstructor() {
        Article article = new Article();

        assertNotNull(article);
        assertNull(article.getId());
        assertNull(article.getTitre());
        assertNull(article.getPrix());
    }

    @Test
    void testSettersAndGetters() {
        Article article = new Article();

        article.setId(10);
        article.setTitre("Java EE Avancé");
        article.setAuteur("John Doe");
        article.setDescription("Livre complet sur Jakarta EE");
        article.setPrix(120.0);
        article.setVille("Tanger");
        article.setPhoto("java-book.jpg");

        LocalDate date = LocalDate.of(2026, 4, 15);
        article.setDatePublication(date);

        article.setEtat(Article.EtatArticle.Bon_Etat);

        assertEquals(10, article.getId());
        assertEquals("Java EE Avancé", article.getTitre());
        assertEquals("John Doe", article.getAuteur());
        assertEquals("Livre complet sur Jakarta EE", article.getDescription());
        assertEquals(120.0, article.getPrix());
        assertEquals("Tanger", article.getVille());
        assertEquals("java-book.jpg", article.getPhoto());
        assertEquals(date, article.getDatePublication());
        assertEquals(Article.EtatArticle.Bon_Etat, article.getEtat());
    }

    @Test
    void testRelations() {
        Article article = new Article();

        Categorie categorie = new Categorie();
        User user = new User();

        categorie.setNom("Programmation");
        user.setNom("Sara");

        article.setIdCat(categorie);
        article.setIdUser(user);

        assertNotNull(article.getIdCat());
        assertNotNull(article.getIdUser());
        assertEquals("Programmation", article.getIdCat().getNom());
        assertEquals("Sara", article.getIdUser().getNom());
    }

    @Test
    void testEtatArticleEnum() {
        assertEquals("Neuf", Article.EtatArticle.Neuf.name());
        assertEquals("Bon_Etat", Article.EtatArticle.Bon_Etat.name());
        assertEquals("Ancien_Usage", Article.EtatArticle.Ancien_Usage.name());
    }
}
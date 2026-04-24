package com.example.schoolswap.bean;

import com.entity.Article;
import com.entity.Categorie;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArticleManagementBeansTest {

    // =========================
    // ACCUEIL BEAN TESTS
    // =========================

    @Test
    void testAccueilBeanSetters() {
        AccueilBean bean = new AccueilBean();

        bean.setMotCle("java");
        bean.setCategorieFiltre("Livre");

        assertEquals("java", bean.getMotCle());
        assertEquals("Livre", bean.getCategorieFiltre());
    }

    @Test
    void testAccueilBeanAnnoncesList() {
        AccueilBean bean = new AccueilBean();

        List<Article> list = new ArrayList<>();
        Article a = new Article();
        a.setTitre("Test");
        list.add(a);

        bean.setAnnonces(list);

        assertEquals(1, bean.getAnnonces().size());
        assertEquals("Test", bean.getAnnonces().get(0).getTitre());
    }

    // =========================
    // DETAILS BEAN TESTS
    // =========================

    @Test
    void testDetailsBeanSetAndGetArticle() {
        DetailsBean bean = new DetailsBean();

        Article article = new Article();
        article.setTitre("Livre Java");

        bean.setArticle(article);

        assertNotNull(bean.getArticle());
        assertEquals("Livre Java", bean.getArticle().getTitre());
    }

    @Test
    void testDetailsBeanArticleId() {
        DetailsBean bean = new DetailsBean();

        bean.setArticleId(5);

        assertEquals(5, bean.getArticleId());
    }

    // =========================
    // ANNONCES BEAN (LIMITED TESTS ONLY)
    // =========================

    @Test
    void testAnnoncesBeanSimpleSetters() {
        AnnoncesBean bean = new AnnoncesBean();

        bean.setMotCle("java");
        bean.setVilleFiltre("Tanger");
        bean.setCategorieFiltre(1);

        assertEquals("java", bean.getMotCle());
        assertEquals("Tanger", bean.getVilleFiltre());
        assertEquals(1, bean.getCategorieFiltre());
    }

    @Test
    void testAnnoncesBeanModeEdition() {
        AnnoncesBean bean = new AnnoncesBean();

        bean.setModeEdition(true);

        assertTrue(bean.isModeEdition());
    }
}
package com.example.schoolswap.bean;

import com.entity.Article;
import com.entity.Categorie;
import com.entity.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdminBeansTest {

    // =========================
    // ADMIN BEAN TESTS
    // =========================

    @Test
    void testAdminBeanUsersList() {
        AdminBean bean = new AdminBean();

        List<User> users = new ArrayList<>();
        User u = new User();
        u.setNom("Admin");
        users.add(u);

        bean.setUsers(users);

        assertEquals(1, bean.getUsers().size());
        assertEquals("Admin", bean.getUsers().get(0).getNom());
    }

    // =========================
    // ADMIN ARTICLE BEAN TESTS
    // =========================

    @Test
    void testAdminArticleBeanList() {
        AdminArticleBean bean = new AdminArticleBean();

        List<Article> articles = new ArrayList<>();
        Article a = new Article();
        a.setTitre("Java EE");
        articles.add(a);

        bean.setArticles(articles);

        assertEquals(1, bean.getArticles().size());
        assertEquals("Java EE", bean.getArticles().get(0).getTitre());
    }

    @Test
    void testAdminArticleBeanSetters() {
        AdminArticleBean bean = new AdminArticleBean();

        List<Article> articles = new ArrayList<>();
        bean.setArticles(articles);

        assertNotNull(bean.getArticles());
    }

    // =========================
    // CATEGORIE BEAN (PARTIAL TESTS)
    // =========================

    @Test
    void testCategorieBeanSetters() {
        CategorieBean bean = new CategorieBean();

        bean.setNomCategorie("Informatique");
        bean.setTypeArticle("Livre");
        bean.setModeEdition(true);

        assertEquals("Informatique", bean.getNomCategorie());
        assertEquals("Livre", bean.getTypeArticle());
        assertTrue(bean.isModeEdition());
    }

    @Test
    void testCategorieBeanList() {
        CategorieBean bean = new CategorieBean();

        List<Categorie> categories = new ArrayList<>();
        Categorie c = new Categorie();
        c.setNom("Science");
        categories.add(c);

        bean.setCategories(categories);

        assertEquals(1, bean.getCategories().size());
        assertEquals("Science", bean.getCategories().get(0).getNom());
    }
}
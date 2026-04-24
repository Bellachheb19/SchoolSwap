package com.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategorieTest {

    @Test
    void testDefaultConstructor() {
        Categorie categorie = new Categorie();

        assertNotNull(categorie);
        assertNull(categorie.getId());
        assertNull(categorie.getNom());
        assertNull(categorie.getType());
    }

    @Test
    void testSettersAndGetters() {
        Categorie categorie = new Categorie();

        categorie.setId(1);
        categorie.setNom("Programmation");
        categorie.setType(Categorie.TypeArticle.Livre);

        assertEquals(1, categorie.getId());
        assertEquals("Programmation", categorie.getNom());
        assertEquals(Categorie.TypeArticle.Livre, categorie.getType());
    }

    @Test
    void testTypeArticleEnum() {
        assertEquals("Livre", Categorie.TypeArticle.Livre.name());
        assertEquals("Materiau", Categorie.TypeArticle.Materiau.name());
    }
}
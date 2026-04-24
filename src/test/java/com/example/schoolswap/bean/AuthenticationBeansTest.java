package com.example.schoolswap.bean;

import com.entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticationBeansTest {

    // =========================
    // LOGIN BEAN TESTS
    // =========================

    @Test
    void testLogoutShouldClearUser() {
        LoginBean loginBean = new LoginBean();

        User user = new User();
        user.setNom("Sara");
        loginBean.setUser(user);

        String result = loginBean.logout();

        assertNull(loginBean.getUser());
        assertEquals("connexion.xhtml?faces-redirect=true", result);
    }

    @Test
    void testIsAdminShouldReturnTrue() {
        LoginBean loginBean = new LoginBean();

        User admin = new User();
        admin.setRole(User.RoleUser.Admin);

        loginBean.setUser(admin);

        assertTrue(loginBean.isAdmin());
        assertFalse(loginBean.isEtudiant());
    }

    @Test
    void testIsEtudiantShouldReturnTrue() {
        LoginBean loginBean = new LoginBean();

        User etudiant = new User();
        etudiant.setRole(User.RoleUser.Etudiant);

        loginBean.setUser(etudiant);

        assertTrue(loginBean.isEtudiant());
        assertFalse(loginBean.isAdmin());
    }

    // =========================
    // REGISTER BEAN TESTS
    // =========================

    @Test
    void testRegisterWithMissingFields() {
        RegisterBean registerBean = new RegisterBean();

        registerBean.setNom("");
        registerBean.setPrenom("Sara");
        registerBean.setEmail("sara@gmail.com");
        registerBean.setPassword("123456");
        registerBean.setTelephone("0612345678");

        String result = registerBean.register();

        assertNull(result);
        assertEquals("Champs obligatoires manquants", registerBean.getMessage());
    }

    @Test
    void testRegisterWithInvalidEmail() {
        RegisterBean registerBean = new RegisterBean();

        registerBean.setNom("Benali");
        registerBean.setPrenom("Sara");
        registerBean.setEmail("email_invalide");
        registerBean.setPassword("123456");
        registerBean.setTelephone("0612345678");

        String result = registerBean.register();

        assertNull(result);
        assertEquals("Email invalide", registerBean.getMessage());
    }

    // =========================
    // PROFIL BEAN TESTS
    // =========================

    @Test
    void testActiverEdition() {
        ProfilBean profilBean = new ProfilBean();

        profilBean.activerEdition();

        assertTrue(profilBean.isEditMode());
    }


    @Test
    void testUpdateProfilPasswordMismatch() {
        ProfilBean profilBean = new ProfilBean();

        User user = new User();
        user.setNom("Sara");

        // simulation utilisateur connecté
        try {
            var field = ProfilBean.class.getDeclaredField("utilisateurConnecte");
            field.setAccessible(true);
            field.set(profilBean, user);
        } catch (Exception e) {
            fail("Erreur reflection sur utilisateurConnecte");
        }

        profilBean.setNom("Sara");
        profilBean.setPrenom("Benali");
        profilBean.setEmail("sara@gmail.com");
        profilBean.setPassword("123456");
        profilBean.setConfirmPassword("654321");

        String result = profilBean.updateProfil();

        assertNull(result);
        assertEquals("Les mots de passe ne correspondent pas", profilBean.getMessage());
    }
}
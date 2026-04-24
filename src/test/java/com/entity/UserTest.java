package com.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testDefaultConstructor() {
        User user = new User();

        assertNotNull(user);
        assertNull(user.getId());
        assertNull(user.getNom());
    }

    @Test
    void testParameterizedConstructor() {
        User user = new User(
                "Benali",
                "Sara",
                "sara@gmail.com",
                "password123",
                "0612345678",
                "Tanger"
        );

        assertEquals("Benali", user.getNom());
        assertEquals("Sara", user.getPrenom());
        assertEquals("sara@gmail.com", user.getMail());
        assertEquals("password123", user.getMotdepasse());
        assertEquals("0612345678", user.getTelephone());
        assertEquals("Tanger", user.getVille());
    }

    @Test
    void testSettersAndGetters() {
        User user = new User();

        user.setId(1);
        user.setNom("Alaoui");
        user.setPrenom("Youssef");
        user.setMail("youssef@gmail.com");
        user.setMotdepasse("securepass");
        user.setTelephone("0700000000");
        user.setVille("Casablanca");
        user.setRole(User.RoleUser.Admin);

        assertEquals(1, user.getId());
        assertEquals("Alaoui", user.getNom());
        assertEquals("Youssef", user.getPrenom());
        assertEquals("youssef@gmail.com", user.getMail());
        assertEquals("securepass", user.getMotdepasse());
        assertEquals("0700000000", user.getTelephone());
        assertEquals("Casablanca", user.getVille());
        assertEquals(User.RoleUser.Admin, user.getRole());
    }

    @Test
    void testEnumRoleUser() {
        assertEquals("Etudiant", User.RoleUser.Etudiant.name());
        assertEquals("Admin", User.RoleUser.Admin.name());
    }
}
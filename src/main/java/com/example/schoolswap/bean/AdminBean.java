package com.example.schoolswap.bean;
import com.entity.User;
import com.example.schoolswap.DAO.UserDAO;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class AdminBean {

    private List<User> users;
    private UserDAO userDAO = new UserDAO();

    @PostConstruct
    public void init() {
        // Au chargement de la page, on récupère tous les utilisateurs
        users = userDAO.getAllUsers();
    }

    // --- Getters et Setters ---
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    // Méthode appelée par le bouton "Banni"
    public String supprimerUtilisateur(int id) {
        // 1. On supprime l'utilisateur de la base de données
        userDAO.deleteUser(id);

        // 2. On recharge la liste pour que le tableau soit à jour
        users = userDAO.getAllUsers();

        // 3. On rafraîchit la page proprement
        return "/admin-users.xhtml?faces-redirect=true";
    }
}
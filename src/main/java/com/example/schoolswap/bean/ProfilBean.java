package com.example.schoolswap.bean;

import com.entity.User;
import com.example.schoolswap.DAO.UserDAO;
import com.example.schoolswap.Util.HashUtil;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class ProfilBean implements Serializable {
    private User utilisateurConnecte;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String confirmPassword;
    private String ville;
    private String tel;
    private String message;
    private boolean editMode = false;


    @PostConstruct
    public void init() {
        utilisateurConnecte = (User) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSessionMap()
                .get("user");
        if (utilisateurConnecte != null) {
            nom = utilisateurConnecte.getNom();
            prenom = utilisateurConnecte.getPrenom();
            email = utilisateurConnecte.getMail();
            ville = utilisateurConnecte.getVille();
            tel = utilisateurConnecte.getTelephone();
        }
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    // Activer le mode édition
    public void activerEdition() {
        editMode = true;
    }

    // Annuler modification
    public void annulerEdition() {
        init(); // recharge les données
        editMode = false;
    }


    public String updateProfil() {

        utilisateurConnecte.setNom(nom);
        utilisateurConnecte.setPrenom(prenom);
        utilisateurConnecte.setMail(email);
        if (password != null && !password.isEmpty()) {
            if (!password.equals(confirmPassword)) {
                message = "Les mots de passe ne correspondent pas";
                return null;
            }
            utilisateurConnecte.setMotdepasse(HashUtil.hashPassword(password));
        }
        utilisateurConnecte.setVille(ville);
        utilisateurConnecte.setTelephone(tel);

        message = UserDAO.updateUser(utilisateurConnecte);
        // Réinitialiser champs mot de passe
        password = null;
        confirmPassword = null;
        editMode = false; //retour en mode affichage

        return null; // reste sur la page ou redirect si nécessaire
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getVille() {
        return ville;
    }

    public User getUtilisateurConnecte() {
        return utilisateurConnecte;
    }

    public String getTel() {
        return tel;
    }
    public String getMessage() {
        return message;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}

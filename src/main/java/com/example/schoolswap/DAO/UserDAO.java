package com.example.schoolswap.DAO;

import com.example.schoolswap.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public static String saveUser(User user) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        String message = null;
        try {
            tx = session.beginTransaction();
            session.persist(user);
            tx.commit();
            message ="succès";
        }
        catch (RuntimeException ex) {
            if(tx != null) tx.rollback();
            ex.printStackTrace();
            System.out.println("Erreur Hibernate : " + ex.getMessage());
            message = "error";
        }
        return message;

    }
    public static String updateUser(User user) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        String message = "Votre profil n'a pas pu être modifié ! Veuillez ressayer... ";
        try{
            tx = session.beginTransaction();
            session.merge(user);
            tx.commit();
            message ="Profile modifié";
        }
        catch (RuntimeException ex) {
            if(tx != null) tx.rollback();
            ex.printStackTrace();
        }
        return message;
    }
    public static List<User> getAllUsers() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<User> users = new ArrayList<>();
        try {
            tx = session.beginTransaction();
            users = session.createQuery("from User", User.class).list();
            tx.commit();
        }
        catch (RuntimeException ex) {
            if(tx != null) tx.rollback();
            ex.printStackTrace();
        }
        return users;
    }
    public static User getUser(String id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        User user = null;
        try{
            tx = session.beginTransaction();
            user = (User) session.get(User.class, id);
            tx.commit();
        }
        catch (RuntimeException ex) {
            if(tx != null) tx.rollback();
            ex.printStackTrace();
        }
        return user;
    }
    // Méthode robuste pour supprimer un utilisateur
    public void deleteUser(int id) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            // ÉTAPE 1 : Supprimer tous les articles de l'utilisateur
            // pour éviter l'erreur de sécurité (Clé Étrangère) de PostgreSQL
            session.createMutationQuery("DELETE FROM Article a WHERE a.idUser.id = :userId")
                    .setParameter("userId", id)
                    .executeUpdate();

            // ÉTAPE 2 : Récupérer l'utilisateur
            User user = session.get(User.class, id);

            // ÉTAPE 3 : Supprimer l'utilisateur
            if (user != null) {
                session.remove(user);
            }

            transaction.commit();
            System.out.println("✅ Utilisateur " + id + " et ses articles ont été supprimés avec succès.");

        } catch (Exception e) {
            // Affichage clair de la véritable erreur dans la console
            System.err.println("🚨 VRAIE ERREUR LORS DE LA SUPPRESSION : " + e.getMessage());
            e.printStackTrace();

            if (transaction != null && transaction.isActive()) {
                try {
                    transaction.rollback();
                } catch (Exception rollbackEx) {
                    System.err.println("Erreur ignorée lors du rollback : " + rollbackEx.getMessage());
                }
            }
        } finally {
            // On ferme toujours la session manuellement à la fin
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}

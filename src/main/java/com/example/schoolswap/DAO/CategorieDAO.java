package com.example.schoolswap.DAO;

import com.entity.Categorie;
import com.example.schoolswap.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.List;

public class CategorieDAO {

    // Récupère toutes les catégories depuis la table "categorie" dans PostgreSQL
    public List<Categorie> getAllCategories() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // HQL pour tout sélectionner
            return session.createQuery("FROM Categorie", Categorie.class).getResultList();
        } catch (Exception e) {
            System.err.println("ERREUR DANS CategorieDAO : " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /** Retourne toutes les catégories (pour le <select> du formulaire). */
    public List<Categorie> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<Categorie> cats = session
                    .createQuery("FROM Categorie ORDER BY nom", Categorie.class)
                    .getResultList();
            tx.commit();
            return cats;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            session.close();
        }
    }

    /** Retourne une catégorie par son id. */
    public Categorie findById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Categorie cat = session.find(Categorie.class, id);
            tx.commit();
            return cat;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public void ajouterCategorie(Categorie categorie) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(categorie);
            transaction.commit();
        } catch (Exception e) {
            System.err.println("🚨 ERREUR AJOUT CATÉGORIE : " + e.getMessage());
            e.printStackTrace();
            if (transaction != null && transaction.isActive()) transaction.rollback();
        } finally {
            if (session != null && session.isOpen()) session.close();
        }
    }

    /** Met à jour une catégorie existante. */
    public void updateCategorie(Categorie categorie) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.merge(categorie);
            transaction.commit();
        } catch (Exception e) {
            System.err.println("🚨 ERREUR MODIFICATION CATÉGORIE : " + e.getMessage());
            e.printStackTrace();
            if (transaction != null && transaction.isActive()) transaction.rollback();
        } finally {
            if (session != null && session.isOpen()) session.close();
        }
    }

    // 5. SUPPRIMER UNE CATÉGORIE
    public void deleteCategorie(Integer id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            // Sécurité : Supprimer d'abord les articles liés.
            // On utilise a.idCat.id car le getter de Categorie est getId()
            session.createMutationQuery("DELETE FROM Article a WHERE a.idCat.id = :catId")
                    .setParameter("catId", id)
                    .executeUpdate();

            Categorie categorie = session.find(Categorie.class, id);
            if (categorie != null) {
                session.remove(categorie);
            }
            transaction.commit();
        } catch (Exception e) {
            System.err.println("🚨 ERREUR SUPPRESSION CATÉGORIE : " + e.getMessage());
            e.printStackTrace();
            if (transaction != null && transaction.isActive()) transaction.rollback();
        } finally {
            if (session != null && session.isOpen()) session.close();
        }
    }
}
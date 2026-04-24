package com.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "categorie")
public class Categorie {

    @Id
    // 1. On dit à Hibernate d'utiliser TA séquence PostgreSQL exacte
    @ColumnDefault("nextval('categorie_id_seq')")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categorie_seq")
    @SequenceGenerator(
            name = "categorie_seq",
            sequenceName = "categorie_id_seq",
            allocationSize = 1
    )
    @Column(name = "id_cat", nullable = false)
    private Integer id;

    @Column(name = "nom", length = 100)
    private String nom;

    // 2. On force Hibernate à convertir le texte en vrai type_article PostgreSQL
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "typearticle", columnDefinition = "type_article")
    public TypeArticle type;

    public enum TypeArticle {
        Livre,
        Materiau
    }

    // --- Getters et Setters ---

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public TypeArticle getType() {
        return type;
    }

    public void setType(TypeArticle type) {
        this.type = type;
    }
}
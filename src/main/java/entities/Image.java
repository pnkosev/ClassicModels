package entities;

import orm.annotations.Column;
import orm.annotations.Entity;
import orm.annotations.Id;

@Entity
public class Image {
    @Id
    private int id;
    @Column
    private String nom;
    @Column
    private String label;
    @Column
    private String tags;

    public Image(String nom, String label, String tags) {
        this.nom = nom;
        this.label = label;
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}

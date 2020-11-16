package example.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "emprunteur")
public class Emprunteur implements Serializable {
    @Id
    @Column(nullable = false, length = 4)
    private int numemp;

    @Column(length = 20)
    private String nom;

    @Column
    private int nblivresemp;

    public int getNumemp() {
        return numemp;
    }

    public void setNumemp(int numemp) {
        this.numemp = numemp;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNblivresemp() {
        return nblivresemp;
    }

    public void setNblivresemp(int nblivresemp) {
        this.nblivresemp = nblivresemp;
    }

    public void incrementNblivresemp() {
        this.nblivresemp++;
    }

    public void decrementNblivresemp() {
        if (nblivresemp > 0) {
            this.nblivresemp--;
        }
    }

    @Override
    public String toString() {
        return "Emprunteur{" +
                "numemp=" + numemp +
                ", nom='" + nom + '\'' +
                ", nblivresemp=" + nblivresemp +
                '}';
    }
}

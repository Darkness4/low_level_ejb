package example.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "livre_emp")
public class LivreEmp implements Serializable {
    @Id
    @Column(nullable = false, length = 4)
    private String isbn;

    @Column(length = 20)
    private String titre;

    @Column
    private int empruntepar;

    public LivreEmp() {
        super();
    }

    public LivreEmp(String isbn, String titre, int empruntepar) {
        super();
        this.isbn = isbn;
        this.titre = titre;
        this.empruntepar = empruntepar;
    }

    public int getEmpruntepar() {
        return empruntepar;
    }

    public void setEmpruntepar(int empruntepar) {
        this.empruntepar = empruntepar;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    @Override
    public String toString() {
        return "LivreEmp{" +
                "isbn='" + isbn + '\'' +
                ", titre='" + titre + '\'' +
                ", empruntepar=" + empruntepar +
                '}';
    }
}
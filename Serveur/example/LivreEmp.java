package example;

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

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "empruntepar")
    private Emprunteur empruntepar;

    public LivreEmp() {
        super();
    }

    public LivreEmp(String isbn, String titre, Emprunteur empruntepar) {
        super();
        this.isbn = isbn;
        this.titre = titre;
        this.empruntepar = empruntepar;
    }

    public Emprunteur getEmpruntepar() {
        return empruntepar;
    }

    public void setEmpruntepar(Emprunteur empruntepar) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LivreEmp livreEmp = (LivreEmp) o;
        return Objects.equals(isbn, livreEmp.isbn) &&
                Objects.equals(titre, livreEmp.titre) &&
                Objects.equals(empruntepar, livreEmp.empruntepar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, titre, empruntepar);
    }
}
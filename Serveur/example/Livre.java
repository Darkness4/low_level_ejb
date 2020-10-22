package example;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "livre")
public class Livre implements Serializable {
    @Id
    @Column(nullable = false, length = 4)
    private String isbn;
    @Column(length = 20)
    private String titre;
    @Column(nullable = false)
    private int dispo;

    public Livre() {
        super();
    }

    public Livre(String isbn, String titre) {
        super();
        this.isbn = isbn;
        this.titre = titre;
        this.dispo = 1;
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

    public int getDispo() {
        return dispo;
    }

    public void setDispo(int dispo) {
        this.dispo = dispo;
    }

    @Override
    public String toString() {
        return "Livre{" +
                "isbn='" + isbn + '\'' +
                ", titre='" + titre + '\'' +
                ", dispo=" + dispo +
                '}';
    }
}
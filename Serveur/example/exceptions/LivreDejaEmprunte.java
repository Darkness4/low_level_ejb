package example.exceptions;

public class LivreDejaEmprunte extends Exception {
    public LivreDejaEmprunte() {
        super("Livre déjà emprunté");
    }
}

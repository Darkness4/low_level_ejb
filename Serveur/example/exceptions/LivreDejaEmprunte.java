package example.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class LivreDejaEmprunte extends Exception {
    public LivreDejaEmprunte() {
        super("Livre déjà emprunté");
    }
}

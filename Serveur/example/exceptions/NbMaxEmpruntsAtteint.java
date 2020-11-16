package example.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class NbMaxEmpruntsAtteint extends Exception {
    public NbMaxEmpruntsAtteint() {
        super("Nombre max d'emprunts atteint");
    }
}

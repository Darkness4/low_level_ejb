package example.exceptions;

public class NbMaxEmpruntsAtteint extends Exception {
    public NbMaxEmpruntsAtteint() {
        super("Nombre max d'emprunts atteint");
    }
}

import example.GestionEmprunt;
import example.InfosLivre;
import example.entities.LivreEmp;
import example.exceptions.LivreDejaEmprunte;
import example.exceptions.NbMaxEmpruntsAtteint;

import javax.naming.InitialContext;

public class Client {
    public static void main(String[] args) {
        try {
            InitialContext initialContext = new InitialContext();
            GestionEmprunt gestionEmprunt = (GestionEmprunt) initialContext.lookup("example.GestionEmprunt");
            InfosLivre infosLivre = (InfosLivre) initialContext.lookup("example.InfosLivre");

            System.out.println("Reinitialiser");
            try {
                infosLivre.reinitialiser();
                gestionEmprunt.reinitialiser();
            } catch (Throwable e) {
                e.printStackTrace();
            }

            System.out.println("Gerer : " + gestionEmprunt.gerer(1));
            System.out.println("Take 3 book");
            try {
                LivreEmp livreEmp = infosLivre.findById("111");
                LivreEmp livreEmp2 = infosLivre.findById("222");
                LivreEmp livreEmp3 = infosLivre.findById("333");
                System.out.println(gestionEmprunt.take(livreEmp, livreEmp2, livreEmp3));
            } catch (Throwable e) {
                e.printStackTrace();
            }

            System.out.println("Take more book that he can take");
            try {
                LivreEmp livreEmp = infosLivre.findById("444");
                System.out.println(gestionEmprunt.take(livreEmp));
                System.out.println("Comportement non voulue");
            } catch (NbMaxEmpruntsAtteint e) {
                System.out.println("OK : " + e);
            } catch (Throwable e) {
                e.printStackTrace();
            }

            System.out.println("Give back the books");
            try {
                LivreEmp livreEmp = infosLivre.findById("111");
                LivreEmp livreEmp2 = infosLivre.findById("222");
                LivreEmp livreEmp3 = infosLivre.findById("333");
                System.out.println(gestionEmprunt.giveBack(livreEmp, livreEmp2, livreEmp3));
            } catch (Throwable e) {
                e.printStackTrace();
            }

            System.out.println("Take the same book twice. Emprunteur counter should be rollbacked (nblivresemp = 1)");
            try {
                LivreEmp livreEmp = infosLivre.findById("444");
                livreEmp = gestionEmprunt.take(livreEmp).get(0);
                System.out.println(gestionEmprunt.take(livreEmp));
                System.out.println("Comportement non voulue");
            } catch (LivreDejaEmprunte e) {
                System.out.println("OK : " + e);
                System.out.println(gestionEmprunt.gerer(1));
            } catch (Throwable e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


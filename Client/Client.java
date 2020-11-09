import example.Emprunteur;
import example.GestionEmprunt;
import example.InfosLivre;
import example.LivreEmp;
import example.exceptions.NbMaxEmpruntsAtteint;

import javax.naming.InitialContext;

public class Client {
    public static void main(String[] args) {
        try {
            InitialContext initialContext = new InitialContext();
            GestionEmprunt gestionEmprunt = (GestionEmprunt) initialContext.lookup("example.GestionEmprunt");
            InfosLivre infosLivre = (InfosLivre) initialContext.lookup("example.InfosLivre");

            System.out.println("desemprunter");
            try {
                {
                    var list = infosLivre.desemprunter();
                    System.out.println(list);
                }
                {
                    var list = gestionEmprunt.desemprunter();
                    System.out.println(list);
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }

            System.out.println("Find a emprunteur and take 3 book");
            try {
                Emprunteur emprunteur = gestionEmprunt.find(0);
                LivreEmp livreEmp = infosLivre.findById("111");
                LivreEmp livreEmp2 = infosLivre.findById("222");
                LivreEmp livreEmp3 = infosLivre.findById("333");
                System.out.println(gestionEmprunt.take(emprunteur, livreEmp, livreEmp2, livreEmp3));
            } catch (Throwable e) {
                e.printStackTrace();
            }

            System.out.println("Find the same emprunteur and take more book that he can take");
            try {
                Emprunteur emprunteur = gestionEmprunt.find(0);
                LivreEmp livreEmp = infosLivre.findById("444");
                System.out.println(gestionEmprunt.take(emprunteur, livreEmp));
                System.out.println("Comportement non voulue");
            } catch (NbMaxEmpruntsAtteint e) {
                System.out.println("OK : " + e);
            } catch (Throwable e) {
                e.printStackTrace();
            }

            System.out.println("Find the same emprunteur and give back the books");
            try {
                Emprunteur emprunteur = gestionEmprunt.find(0);
                LivreEmp livreEmp = infosLivre.findById("111");
                LivreEmp livreEmp2 = infosLivre.findById("222");
                LivreEmp livreEmp3 = infosLivre.findById("333");
                System.out.println(gestionEmprunt.giveBack(emprunteur, livreEmp, livreEmp2, livreEmp3));
            } catch (Throwable e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


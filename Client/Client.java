import example.BookServiceRemote;
import example.Livre;

import javax.naming.InitialContext;

public class Client {
    public static void main(String[] args) {
        try {
            InitialContext initialContext = new InitialContext();
            BookServiceRemote remote = (BookServiceRemote) initialContext.lookup("example.BookServiceRemote");
            System.out.println("CREATE");
            try {
                System.out.println(remote.create(new Livre("test", "test")));
            } catch (Throwable e) {
                e.printStackTrace();
            }

            System.out.println("FIND_ALL");
            try {
                System.out.println(remote.findAll());
            } catch (Throwable e) {
                e.printStackTrace();
            }

            System.out.println("UPDATE");
            try {
                System.out.println(remote.update(new Livre("test", "test2")));
            } catch (Throwable e) {
                e.printStackTrace();
            }

            System.out.println("FIND");
            try {
                System.out.println(remote.find("test"));
            } catch (Throwable e) {
                e.printStackTrace();
            }

            System.out.println("FIND_ALL");
            try {
                System.out.println(remote.findAll());
            } catch (Throwable e) {
                e.printStackTrace();
            }

            System.out.println("REMOVE");
            try {
                remote.delete("test");
                System.out.println("Success");
            } catch (Throwable e) {
                e.printStackTrace();
            }

            System.out.println("FIND_ALL");
            try {
                System.out.println(remote.findAll());
            } catch (Throwable e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
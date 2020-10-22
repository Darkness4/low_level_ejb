import example.BookServiceRemote;
import example.Livre;

import javax.naming.InitialContext;
import java.util.List;

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
                System.out.println(remote.findById("test"));
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
                remote.deleteById("test");
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

            System.out.println("TAKE_ALL");
            try {
                final List<Livre> list = remote.findAll();
                System.out.println(remote.take(list.toArray(new Livre[0])));
            } catch (Throwable e) {
                e.printStackTrace();
            }

            System.out.println("GIVE_ALL_BACK");
            try {
                final List<Livre> list = remote.findAll();
                System.out.println(remote.giveBack(list.toArray(new Livre[0])));
            } catch (Throwable e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
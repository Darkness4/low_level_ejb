import example.ExampleBeanRemote;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;

public class Client {
  public static void main(String[] args) {
    try {
      InitialContext initialContext = new InitialContext();
      ExampleBeanRemote remote = (ExampleBeanRemote) initialContext.lookup("example.ExampleBeanRemote");
      System.out.println(remote.salut("test"));
      System.out.println(remote.repeat());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
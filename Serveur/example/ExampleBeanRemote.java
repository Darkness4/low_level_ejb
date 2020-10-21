package example;

import javax.ejb.*;

@Remote
public interface ExampleBeanRemote {
  public String salut(String in);

  public String repeat();
}
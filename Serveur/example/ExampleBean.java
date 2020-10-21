package example;

import javax.ejb.*;

@Stateful
public class ExampleBean implements ExampleBeanRemote {
  String last = null;

  @Override
  public String salut(String in) {
    last = "Salut" + in + " !";
    return last;
  }

  @Override
  public String repeat() {
    if (last == null) {
      return "null";
    }
    return last;
  }
}
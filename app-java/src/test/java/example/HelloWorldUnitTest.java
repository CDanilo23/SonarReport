package example;

import org.junit.Test;

import co.com.sender.HelloWorld;

public class HelloWorldUnitTest {

  @Test
  public void test() {
    new HelloWorld().coveredByUnitTest();
  }

  @Test
  public void test1() {
    new HelloWorld().coveredByIntegrationTest();
  }
  
  @Test
  public void test2() {
    new HelloWorld().notCovered();
  }
}

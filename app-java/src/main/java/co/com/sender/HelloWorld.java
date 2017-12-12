package co.com.sender;

import java.util.logging.Logger;

public class HelloWorld {
	
	Logger logger = Logger.getLogger(HelloWorld.class.getName());

  public void coveredByUnitTest() {
	  logger.info("coveredByUnitTest1");
      logger.info("coveredByUnitTest2");
  }

  public void coveredByIntegrationTest() {
    logger.warning("coveredByIntegrationTest1");
    logger.warning("coveredByIntegrationTest2");
    logger.warning("coveredByIntegrationTest3");
  }

  public void notCovered() {
    logger.severe("notCovered");
    logger.severe("notCovered");
    logger.severe("notCovered");
  }

}

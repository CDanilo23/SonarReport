package co.com.sender;

import java.util.Properties;
import java.util.logging.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueConnection;
import javax.jms.QueueSession;
import javax.jms.Queue;
import javax.jms.QueueSender;
import javax.jms.TextMessage;

public class Sender {
	
	private static final Logger log = Logger.getLogger(Sender.class.getName());
			
	private static final String DEFAULT_MESSAGE = "Hello";
	private static final String DEFAULT_CONNECTION_FACTORY="jms/RemoteConnectionFactory";
	private static final String DEFAULT_DESTINATION= "jms/queue/ghaMessageQueue";
	private static final String DEFAULT_MESSAGE_COUNT ="1";
	private static final String DEFAULT_USERNAME = "danilo";
	private static final String DEFAULT_PASSWORD= "danilo27$";
	private static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.initialcontextfactory";
	private static final String PROVIDER_URL = "http://localhost:8081";
	
	public static void main(String [] args) {
		
		QueueConnection conn;
		Queue queue;
		QueueSession session;
		QueueSender send;
		TextMessage textMessage;
		InitialContext initialContext = null;
		try {
			System.out.println("------------------------ENTRO----------------------");
			Properties env = new Properties();
			env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY );
			System.out.println(System.getProperty(Context.PROVIDER_URL, PROVIDER_URL));
			env.put(Context.PROVIDER_URL, System.getProperty(Context.PROVIDER_URL, PROVIDER_URL));
			env.put(Context.SECURITY_PRINCIPAL, "danilo" );
			env.put(Context.SECURITY_CREDENTIALS, "danilo27$" );
			env.put("jboss.naming.client.ejb.context", true);
			initialContext = new InitialContext(env);
			Object tmp = initialContext.lookup("QueueConnectionFactory");
			System.out.println("------------------------CREATE FACTORY----------------------");
			QueueConnectionFactory qcf = (QueueConnectionFactory) tmp;
			conn = qcf.createQueueConnection();
			queue = (Queue) initialContext.lookup(DEFAULT_DESTINATION);
			session = conn.createQueueSession(false,QueueSession.AUTO_ACKNOWLEDGE);
			conn.start();
			send = session.createSender(queue);
			textMessage = session.createTextMessage("Hola soy tu mensaje");
			send.send(textMessage);send.close();
			
		}catch (Exception e) {
			System.out.println("------------------------FALLO CONTEXTO----------------------");
			e.printStackTrace();
		}
	}
}

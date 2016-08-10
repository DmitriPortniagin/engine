import java.util.Properties;
import java.util.Queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Engine {
	public static void main(String[] args) {
		ConnectionFactory connectionFactory = null;
		Context jndiContext = null;

		Properties props = new Properties();
		props.setProperty(Context.INITIAL_CONTEXT_FACTORY,"org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		props.setProperty(Context.PROVIDER_URL,"tcp://localhost:61616");
		try{
		  jndiContext = new InitialContext(props);
          connectionFactory = (ConnectionFactory)jndiContext.lookup("ConnectionFactory");
        } catch (NamingException e) {
        	e.printStackTrace();
        }
        /*
         * Create connection. Create session from connection; false means
         * session is not transacted. Create sender and text message. Send
         * messages, varying text slightly. Send end-of-messages message.
         * Finally, close connection.
         */
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
        } catch (JMSException e) {
        	e.printStackTrace();
        }
        
		Replier rep = new Replier();
		try {
			rep.initialize(connection, "RequestQueue", "ErrorQueue");
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}

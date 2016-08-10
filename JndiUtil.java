import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JndiUtil {

	public static Destination getDestination(String requestQueueName) throws NamingException {

        Properties props = new Properties(); 
        props.setProperty(Context.INITIAL_CONTEXT_FACTORY, 
                          "org.apache.activemq.jndi.ActiveMQInitialContextFactory"); 
        props.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616"); 

		//ConnectionFactory connectionFactory = null;
		Context jndiContext = null;
		Destination dest = null;
        jndiContext = new InitialContext(props);
  
        /*
         * Look up connection factory and destination.
         */
           // connectionFactory = (ConnectionFactory)jndiContext.lookup("ConnectionFactory");
        dest = (Destination)jndiContext.lookup(requestQueueName);
		return dest;
	}
}

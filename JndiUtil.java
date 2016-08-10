import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JndiUtil {

	public static Destination getDestination(Context jndiContext, String requestQueueName) throws NamingException {
		return (Destination)jndiContext.lookup(requestQueueName);
	}
}

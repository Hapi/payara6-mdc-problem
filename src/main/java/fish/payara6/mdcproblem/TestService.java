package fish.payara6.mdcproblem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;


@Path("/")
@RequestScoped
public class TestService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestService.class);
	
	@GET
	@Path("send")
	public String send()
	{
		MDC.put("firstName", "Dorothy");
		LOGGER.error("This is just a test log event.");
		return "kitten";
	}
}

package ca.bochenek.brainstorm;

import java.util.UUID;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Stateless 
public class JMS20Producer {

    @Resource(lookup = "jms/queue/myqueue")
    private Queue queue;

    @Inject
    private JMSContext jmsContext;

    @Path("/jms20")
    @GET
    public String produce() {
        BusinessObject payload = new BusinessObject(UUID.randomUUID().toString());

        jmsContext.createProducer().send(queue, payload);

        return "OK";
    }
}

package ca.bochenek.brainstorm;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint("/halmawebsocket")
public class HalmaWebSocket {

	@OnMessage
    public void onMessage(String message, Session session) 
    	throws IOException, InterruptedException {
		
		// Print the client message for testing purposes
		System.out.println("Received: " + message);
		
		// Send the first message to the client
		session.getBasicRemote().sendText("This is the first server message");
		
		Long start = System.nanoTime();
		
		// Send 3 messages to the client every 5 seconds
		int sentMessages = 0;
//		while(sentMessages < 100){
//			Thread.sleep(100);
//			session.getBasicRemote().
//				sendText("This is an intermediate server message. Count: " 
//					+ ++sentMessages + "  after: " + (System.nanoTime() - start));
//		}
		
		// Send a final message to the client
		session.getBasicRemote().sendText("This is the last server message");
    }
	
	@OnOpen
    public void onOpen () {
        System.out.println("Client connected");
    }

    @OnClose
    public void onClose () {
    	System.out.println("Connection closed");
    }
}

package ca.bochenek.brainstorm;

import java.io.IOException;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint("/halmawebsocket")
public class HalmaWebSocket {

	@OnMessage
    public void onMessage(String message, Session session) 
    	throws IOException, InterruptedException, EncodeException {
		
		// Print the client message for testing purposes
		System.out.println("Received: " + message);

		
		// https://github.com/arun-gupta/javaee7-samples/tree/master/websocket/chat
		// https://blogs.oracle.com/arungupta/entry/chat_sever_using_websocket_totd
	    for (Session peer : session.getOpenSessions()) {
	        peer.getBasicRemote().sendObject(message);
	    }
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

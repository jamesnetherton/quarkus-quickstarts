package org.acme.websocket;

import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.Session;

public class SimpleEndpoint extends Endpoint {

    @Override
    public void onOpen(Session session, EndpointConfig config) {
        session.getAsyncRemote().sendText("Hello World");
    }
}

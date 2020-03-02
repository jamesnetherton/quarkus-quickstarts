package org.acme.websocket;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.websocket.DeploymentException;
import javax.websocket.server.ServerContainer;
import javax.websocket.server.ServerEndpointConfig;

@WebListener
public class MyListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        SimpleEndpoint endpoint = new SimpleEndpoint();
        ServerEndpointConfig.Builder builder = ServerEndpointConfig.Builder.create(SimpleEndpoint.class, "/simple");
        builder.configurator(new ServerEndpointConfig.Configurator() {
            @Override
            public <T> T getEndpointInstance(Class<T> endpointClass) throws InstantiationException {
                return endpointClass.cast(endpoint);
            }
        });
        ServerContainer container = (ServerContainer) servletContext.getAttribute(ServerContainer.class.getName());
        try {
            container.addEndpoint(builder.build());
        } catch (DeploymentException e) {
            e.printStackTrace();
        }
    }
}

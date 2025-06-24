package org.k11techlab.framework.webservice.testengine.soapserviceutil.soapserviceutil.apiHelper;

import org.k11techlab.framework.selenium.webuitestengine.commonUtil.LoadProperties;

import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;
import java.util.ArrayList;
import java.util.List;
 
public class JaxWsHandlerResolver implements HandlerResolver {
 
    @SuppressWarnings("rawtypes")
    @Override
    public List<Handler> getHandlerChain(PortInfo arg0) {
        List<Handler> hchain = new ArrayList<Handler>();
        LoadProperties prop= new LoadProperties();
        String username = prop.getProperty("service.username");
        String password = prop.getProperty("service.password");
        hchain.add(new JaxWsLoggingHandler());
        hchain.add(new WSSecurityHeaderSOAPHandler(username, password));
        return hchain;
    }
 
}
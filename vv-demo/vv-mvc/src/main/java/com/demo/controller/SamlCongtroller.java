package com.demo.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.app.VelocityEngine;
import org.opensaml.DefaultBootstrap;
import org.opensaml.common.binding.BasicSAMLMessageContext;
import org.opensaml.common.binding.encoding.SAMLMessageEncoder;
import org.opensaml.common.binding.security.IssueInstantRule;
import org.opensaml.common.binding.security.MessageReplayRule;
import org.opensaml.saml2.binding.decoding.HTTPPostSimpleSignDecoder;
import org.opensaml.saml2.binding.encoding.HTTPPostSimpleSignEncoder;
import org.opensaml.saml2.core.AuthnRequest;
import org.opensaml.util.storage.MapBasedStorageService;
import org.opensaml.util.storage.ReplayCache;
import org.opensaml.ws.security.provider.BasicSecurityPolicy;
import org.opensaml.ws.security.provider.StaticSecurityPolicyResolver;
import org.opensaml.ws.transport.http.HttpServletRequestAdapter;
import org.opensaml.ws.transport.http.HttpServletResponseAdapter;
import org.opensaml.xml.parse.BasicParserPool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;

import com.demo.saml.PrettyPrinter;
import com.demo.saml.SamlUtils;
import com.demo.saml.VelocityFactory;

@Controller
public class SamlCongtroller {
	@RequestMapping(value="/saml/sendAuthRequest")
    public void sendAuthRequest(final HttpServletRequest request, final HttpServletResponse response){
    	System.out.println("/saml/sendAuthRequest");
    	try {
    		DefaultBootstrap.bootstrap();
	    	AuthnRequest samlRequest = SamlUtils.buildAuthnRequest("http://localhost:7080/vv-mvc/saml/getAuthRequest", "http://localhost:7080/vv-mvc/saml/getAuthResponse", "test");
	    	// 发送
	    	BasicSAMLMessageContext messageContext = new BasicSAMLMessageContext();
	    	messageContext.setOutboundMessageTransport(new HttpServletResponseAdapter(response, request.isSecure()));
	    	messageContext.setOutboundSAMLMessage(samlRequest);
	    	
	    	VelocityEngine ve = VelocityFactory.getEngine();
			SAMLMessageEncoder encoder = new HTTPPostSimpleSignEncoder(ve,"/templates/saml2-post-simplesign-binding.vm", true); 
			encoder.encode(messageContext);
			
			System.out.println("/saml/sendAuthRequest success.");
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
    }

    @RequestMapping(value="/saml/getAuthRequest")
    public ModelAndView samlRequest(final HttpServletRequest request, final HttpServletResponse response){
    	System.out.println("/saml/getAuthRequest");
    	ModelAndView mav = new ModelAndView();
    	try {
	        BasicSecurityPolicy securityPolicy = new BasicSecurityPolicy();
	        // IssueInstantRule 时间
	        // MessageReplayRule 
			securityPolicy.getPolicyRules().addAll(Arrays.asList(new IssueInstantRule(90, 30), new MessageReplayRule(new ReplayCache(new MapBasedStorageService(), 14400000))));
			StaticSecurityPolicyResolver resolver = new StaticSecurityPolicyResolver(securityPolicy);
			
			BasicSAMLMessageContext messageContext = new BasicSAMLMessageContext();
	        messageContext.setInboundMessageTransport(new HttpServletRequestAdapter(request));
			messageContext.setSecurityPolicyResolver(resolver);
			
			BasicParserPool parserPool = new BasicParserPool();
			parserPool.setMaxPoolSize(2);
			HTTPPostSimpleSignDecoder decoder = new HTTPPostSimpleSignDecoder(parserPool); 
	        decoder.decode(messageContext);
	        
	        AuthnRequest authnRequest = (AuthnRequest) messageContext.getInboundSAMLMessage();
	        SamlUtils.validate(authnRequest);
	        
	        Document document = SamlUtils.asDOMDocument (authnRequest);
	        String result = PrettyPrinter.prettyPrint (document);
	        mav.setViewName("samlRequest");
	        mav.addObject("samlRequest",result);
    	} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
        return mav;
    }
    
    @RequestMapping(value="/saml/getAuthResponse")
    public ModelAndView samlResponse(final HttpServletRequest request, final HttpServletResponse response){
    	System.out.println("/saml/getAuthResponse");
        ModelAndView mav = new ModelAndView();
        /*mav.setViewName("samlResponse");
        mav.addObject("samlResponse",samlResponse);*/
        return mav;
    }
}

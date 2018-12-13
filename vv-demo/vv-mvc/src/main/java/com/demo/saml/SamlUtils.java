package com.demo.saml;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.opensaml.Configuration;
import org.opensaml.DefaultBootstrap;
import org.opensaml.common.SAMLObjectBuilder;
import org.opensaml.saml2.core.AuthnRequest;
import org.opensaml.saml2.core.Issuer;
import org.opensaml.saml2.core.NameIDType;
import org.opensaml.saml2.core.impl.AuthnRequestBuilder;
import org.opensaml.saml2.core.impl.IssuerBuilder;
import org.opensaml.saml2.metadata.Endpoint;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.XMLObjectBuilderFactory;
import org.opensaml.xml.io.Marshaller;
import org.opensaml.xml.io.MarshallingException;
import org.opensaml.xml.validation.ValidationException;
import org.opensaml.xml.validation.ValidatorSuite;
import org.w3c.dom.Document;

public class SamlUtils {
	private static XMLObjectBuilderFactory builderFactory = Configuration.getBuilderFactory();
	
	/**
	 * 创建提供者
	 * @param issuingEntityName
	 * @return
	 */
	public static Issuer buildIssuer(String issuingEntityName){
		// <saml2:Issuer>
		IssuerBuilder issuerBuilder = (IssuerBuilder) builderFactory.getBuilder(Issuer.DEFAULT_ELEMENT_NAME);
		Issuer issuer = issuerBuilder.buildObject();

		issuer.setValue(issuingEntityName);
		issuer.setFormat(NameIDType.ENTITY);
		return issuer;
	}
	
	/**
	 * 创建请求
	 * @param actionURL 请求地址
	 * @param redirectionUrl 跳转地址
	 * @param relayState 回复
	 * @return
	 */
	public static AuthnRequest buildAuthnRequest(String actionURL,String redirectionUrl,
            String relayState){
		// 构建<saml2p:AuthnRequest>, 注意：saml2p可自定义
		AuthnRequestBuilder authnRequestBuilder = (AuthnRequestBuilder) builderFactory.getBuilder(AuthnRequest.DEFAULT_ELEMENT_NAME);
		AuthnRequest authnRequest = authnRequestBuilder.buildObject();
		// 添加<saml2p:AuthnRequest>属性AssertionConsumerServiceURL
		authnRequest.setAssertionConsumerServiceURL(redirectionUrl);
		authnRequest.setID(UUID.randomUUID().toString());
		DateTime issueInstant = new DateTime();
		authnRequest.setIssueInstant(issueInstant);
		authnRequest.setDestination(actionURL);
		
		Issuer issuer = buildIssuer("com.vv.demo.util.saml");
		authnRequest.setIssuer(issuer);
		return authnRequest;
	}
	
	
	public static Endpoint buildEndpoint(String location, String responseLocation) {
		SAMLObjectBuilder<Endpoint> endpointBuilder = (SAMLObjectBuilder<Endpoint>) builderFactory.getBuilder(Endpoint.DEFAULT_ELEMENT_NAME);
		Endpoint samlEndpoint = endpointBuilder.buildObject();
		
		samlEndpoint.setLocation(location);
		// this does not have to be set
		if (StringUtils.isNotEmpty(responseLocation)) {
			samlEndpoint.setResponseLocation(responseLocation);
		}
		return samlEndpoint;
	}
	
	public static void validate(XMLObject xmlObject) throws ValidationException {
		ValidatorSuite schemaValidator = Configuration.getValidatorSuite("saml2-core-schema-validator");
		schemaValidator.validate(xmlObject);
		ValidatorSuite specValidator = Configuration.getValidatorSuite("saml2-core-spec-validator");
		specValidator.validate(xmlObject);
	}

	
	/**
	 * @return 
	 * 
	 */
	/*public static SAMLMessageContext buildSAMLMessageContext(HttpServletResponse response){
		XMLObjectBuilderFactory builderFactory = Configuration.getBuilderFactory();
		
		SAMLObjectBuilder<Endpoint> endpointBuilder = (SAMLObjectBuilder<Endpoint>) builderFactory.getBuilder(SingleSignOnService.DEFAULT_ELEMENT_NAME);
		
		// 构建节点
		Endpoint samlEndpoint = endpointBuilder.buildObject();
        samlEndpoint.setLocation(location);
        //this does not have to be set
        if(StringUtils.isNotEmpty(responseLocation))
        	samlEndpoint.setResponseLocation(responseLocation);	
        
		//HttpServletResponse response = mock(HttpServletResponse.class);
		HttpServletResponseAdapter outTransport = new HttpServletResponseAdapter(response, false);
		BasicSAMLMessageContext messageContext = new BasicSAMLMessageContext();
		messageContext.setOutboundMessageTransport(outTransport);
		//messageContext.setPeerEntityEndpoint(samlEndpoint);
		
		
		messageContext.setOutboundSAMLMessage(authnRequest);
		messageContext.setOutboundMessageIssuer(issuerEntityId);
		
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		SAMLMessageEncoder encoder = new HTTPPostSimpleSignEncoder(ve,"/templates/saml2-post-simplesign-binding.vm", true); 
		//SAMLMessageEncoder encoder = new HTTPRedirectDeflateEncoder();
		encoder.encode(messageContext);
		return null;
	}*/
	
	
	/**
    Helper method to pretty-print any XML object to a file.
	 * @throws ParserConfigurationException 
    */
    public static void printToFile (XMLObject object, String filename)
        throws IOException, MarshallingException, TransformerException, ParserConfigurationException
    {
        Document document = asDOMDocument (object);
        
        String result = PrettyPrinter.prettyPrint (document);
        if (filename != null)
        {
            PrintWriter writer = new PrintWriter (new FileWriter (filename));
            writer.println (result);
            writer.close ();
        }
        else
            System.out.println (result);
    }
    
    /**
    Helper method to get an XMLObject as a DOM Document.
     * @throws ParserConfigurationException 
    */
    public static Document asDOMDocument (XMLObject object)
        throws IOException, MarshallingException, TransformerException, ParserConfigurationException
    {
    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance ();
        factory.setNamespaceAware (true);
        DocumentBuilder builder = factory.newDocumentBuilder ();
        Document document = builder.newDocument ();
        Marshaller out =  Configuration.getMarshallerFactory ().getMarshaller (object);
        out.marshall (object, document);
        return document;
    }
    
    public static void main(String[] args) {
    	try {
    		DefaultBootstrap.bootstrap ();
    	    AuthnRequest request = SamlUtils.buildAuthnRequest("http://localhost:7080/saml/request", "http://localhost:7080/saml/response", "test");
    	    	
			printToFile(request, null);
		} catch (Exception e) {
			e.printStackTrace();
		} 
    }
}

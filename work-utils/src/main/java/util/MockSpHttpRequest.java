package util;

import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.joda.time.DateTime;
import org.opensaml.Configuration;
import org.opensaml.DefaultBootstrap;
import org.opensaml.common.SAMLObjectBuilder;
import org.opensaml.common.SignableSAMLObject;
import org.opensaml.common.binding.BasicSAMLMessageContext;
import org.opensaml.common.binding.encoding.SAMLMessageEncoder;
import org.opensaml.saml2.binding.encoding.HTTPPostSimpleSignEncoder;
import org.opensaml.saml2.core.AuthnRequest;
import org.opensaml.saml2.core.Issuer;
import org.opensaml.saml2.core.NameIDType;
import org.opensaml.saml2.core.impl.AuthnRequestBuilder;
import org.opensaml.saml2.core.impl.IssuerBuilder;
import org.opensaml.saml2.metadata.Endpoint;
import org.opensaml.saml2.metadata.SingleSignOnService;
import org.opensaml.ws.message.encoder.MessageEncodingException;
import org.opensaml.ws.transport.http.HttpServletResponseAdapter;
import org.opensaml.xml.ConfigurationException;
import org.opensaml.xml.XMLObjectBuilderFactory;
import org.opensaml.xml.security.CriteriaSet;
import org.opensaml.xml.security.credential.UsageType;
import org.opensaml.xml.security.criteria.EntityIDCriteria;
import org.opensaml.xml.security.criteria.UsageCriteria;

public class MockSpHttpRequest {
	static String IDP_SSO_URL = "http://localhost:7080/vv-mvc/hello";
	static String assertionConsumerServiceURL = "http://baidu.com";
	
	public static void main(String[] args) throws IOException {
		try {
			DefaultBootstrap.bootstrap();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		
		buildAuthRequest();
	}
	
	public static void httptest() throws HttpException, IOException{
		HttpClient client = new HttpClient();
		// 设置代理服务器地址和端口

		// client.getHostConfiguration().setProxy("proxy_host_addr",proxy_port);
		// 使用 GET 方法 ，如果服务器需要通过 HTTPS 连接，那只需要将下面 URL 中的 http 换成 https
		//HttpMethod method = new GetMethod("http://java.sun.com");
		// 使用POST方法
		HttpMethod method = new PostMethod("http://java.sun.com");
		client.executeMethod(method);

		// 打印服务器返回的状态
		System.out.println(method.getStatusLine());
		// 打印返回的信息
		System.out.println(method.getResponseBodyAsString());
		// 释放连接
		method.releaseConnection();
	}
	
	public static void buildAuthRequest(){
		try{
			QName service = SingleSignOnService.DEFAULT_ELEMENT_NAME;
			String location  = IDP_SSO_URL;
			String responseLocation = assertionConsumerServiceURL;
			String issuerEntityId = "sp";
			
			/*System.out.println("end point service:" + service);
			System.out.println("end point location:" +  location);
			System.out.println("end point responseLocation: " + responseLocation);*/
			
			XMLObjectBuilderFactory builderFactory = Configuration.getBuilderFactory();
			
			SAMLObjectBuilder<Endpoint> endpointBuilder = (SAMLObjectBuilder<Endpoint>) builderFactory.getBuilder(SingleSignOnService.DEFAULT_ELEMENT_NAME);
			
			// 构建节点
			Endpoint samlEndpoint = endpointBuilder.buildObject();
	        samlEndpoint.setLocation(location);
	        //this does not have to be set
	        if(StringUtils.isNotEmpty(responseLocation))
	        	samlEndpoint.setResponseLocation(responseLocation);	
	        
	        // 构建AuthorRequest
	        AuthnRequestBuilder authnRequestBuilder = (AuthnRequestBuilder) builderFactory.getBuilder(AuthnRequest.DEFAULT_ELEMENT_NAME);
	        AuthnRequest authnRequest = authnRequestBuilder.buildObject();
			
	        // 接收响应地址
			authnRequest.setAssertionConsumerServiceURL(responseLocation);
			// 随机生成ID
			authnRequest.setID(UUID.randomUUID().toString());
			authnRequest.setIssueInstant(new DateTime());
			authnRequest.setDestination(location);
			
			///Issuer
			IssuerBuilder issuerBuilder = (IssuerBuilder) builderFactory.getBuilder(Issuer.DEFAULT_ELEMENT_NAME);
			Issuer issuer = issuerBuilder.buildObject();
			issuer.setValue(issuerEntityId);
			issuer.setFormat(NameIDType.ENTITY);
			
			authnRequest.setIssuer(issuer);
			System.out.println("Sending authnRequest to " + location);
			
			HttpServletResponse response = mock(HttpServletResponse.class);
			HttpServletResponseAdapter outTransport = new HttpServletResponseAdapter(response, false);
			OutputStream osw = System.out;
			//OutputStreamOutTransportAdapter outTransport = new OutputStreamOutTransportAdapter(osw);
			BasicSAMLMessageContext messageContext = new BasicSAMLMessageContext();
			messageContext.setOutboundMessageTransport(outTransport);
			messageContext.setPeerEntityEndpoint(samlEndpoint);
			messageContext.setOutboundSAMLMessage(authnRequest);
			messageContext.setOutboundMessageIssuer(issuerEntityId);
			
			CriteriaSet criteriaSet = new CriteriaSet();
			criteriaSet.add(new EntityIDCriteria(issuerEntityId));
			criteriaSet.add(new UsageCriteria(UsageType.SIGNING));
			
			
			/*KeyStore ks = KeyStore.getInstance("JKS");
			String SP_b64EncodedKeystore = "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";
			ks.load(new ByteArrayInputStream(Base64.decodeBase64(SP_b64EncodedKeystore.getBytes())), "secret".toCharArray());
			// 
			Map<String,String> privateKeyPasswordsByAlias = new HashMap<String, String>();
			privateKeyPasswordsByAlias.put(issuerEntityId, "secret");
			
			KeyStoreCredentialResolver keyStoreCredentialResolver = new KeyStoreCredentialResolver(ks, privateKeyPasswordsByAlias);
			Credential signingCredential = keyStoreCredentialResolver.resolveSingle(criteriaSet);
			Validate.notNull(signingCredential);*/
			
			//messageContext.setOutboundSAMLMessageSigningCredential(signingCredential);
			
			VelocityEngine ve = new VelocityEngine();
			ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
			ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
			SAMLMessageEncoder encoder = new HTTPPostSimpleSignEncoder(ve,"/templates/saml2-post-simplesign-binding.vm", true); 
			//SAMLMessageEncoder encoder = new HTTPRedirectDeflateEncoder();
			encoder.encode(messageContext);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void sendSAMLMessage(SignableSAMLObject samlMessage,
			Endpoint endpoint, 
			//Credential signingCredential,
			HttpServletResponse response) throws MessageEncodingException {

		HttpServletResponseAdapter outTransport = new HttpServletResponseAdapter(response, false);
		
		BasicSAMLMessageContext messageContext = new BasicSAMLMessageContext();
		
		messageContext.setOutboundMessageTransport(outTransport);
		messageContext.setPeerEntityEndpoint(endpoint);
		messageContext.setOutboundSAMLMessage(samlMessage);
		//messageContext.setOutboundMessageIssuer(issuerEntityId);
		//messageContext.setOutboundSAMLMessageSigningCredential(signingCredential);
		
		//encoder.encode(messageContext);
		
	}
}
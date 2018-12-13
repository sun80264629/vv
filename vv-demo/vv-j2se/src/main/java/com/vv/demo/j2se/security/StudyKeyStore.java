package com.vv.demo.j2se.security;

/*import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import org.apache.commons.codec.binary.Base64;*/

/**
 * KeyStore表示密钥和证书的存储设施。 KeyStore 管理不同类型的条目。 每种类型的条目都实现 KeyStore.Entry接口。
 * 提供了三种基本的 KeyStore.Entry 实现： KeyStore.PrivateKeyEntry
 * 此类型的条目保存一个加密的PrivateKey，可以选择用受保护格式存储该私钥，以防止未授权访问。它还随附一个相应公钥的证书链。
 * 给定条目使用私钥和证书链进行自验证 (self-authentication)。应用此验证的包括软件发布组织，它们将 JAR
 * 文件签名为发布和/或许可软件的一部分。 KeyStore.SecretKeyEntry 此类型的条目保存一个加密的
 * SecretKey，可以选择用受保护格式存储该密钥，以防止未授权访问。 KeyStore.TrustedCertificateEntry
 * 此类型的条目包含一个属于另一方的单个公钥 Certificate。它被称为可信证书，因为 keystore 的所有者相信证书中的公钥确实属于该证书的
 * subject（所有者）所标识的身份。 此类型的条目可用于验证其他方。
 * 
 * @author Vv
 * 
 */
public class StudyKeyStore {
	/*public static void main(String[] args) throws URISyntaxException {
		String IDP_b64EncodedKeystore = "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";
		String SP_b64EncodedKeystore = "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";
		String keystorePassword = "secret";
		*//*try {

			// 加载证书
			KeyStore ks = KeyStore.getInstance("JKS");
			ks.load(new ByteArrayInputStream(Base64
					.decodeBase64(IDP_b64EncodedKeystore.getBytes())),
					keystorePassword.toCharArray());
			//
			System.out.println("IDP Certificate是否是可信 = "
					+ ks.isCertificateEntry("idp"));
			System.out.println("SP is Certificate = "
					+ ks.isCertificateEntry("sp"));
			System.out.println("IDP Certificate = " + ks.getCertificate("idp"));
			System.out.println("SP Certificate = " + ks.getCertificate("sp"));

			System.out
					.println("========================================================");
			// sp-keystore.jks
			// String path =
			// StudyKeyStore.class.getResource("/").getPath().replaceFirst("/",
			// "");
			// System.out.println("path = " + path);
			// String jksFile = path + "sp-keystore.jks";

			// FileInputStream jksfis = new FileInputStream(jksFile);
			// 加载证书
			// ks.load(jksfis, keystorePassword.toCharArray());
			ks = KeyStore.getInstance("JKS");
			ks.load(new ByteArrayInputStream(Base64
					.decodeBase64(SP_b64EncodedKeystore.getBytes())),
					keystorePassword.toCharArray());

			// ks.getEntry("idp",protParam );
			System.out.println("IDP is Certificate 是否是可信 = "
					+ ks.isCertificateEntry("idp"));
			System.out.println("SP is Certificate = "
					+ ks.isCertificateEntry("sp"));
			System.out.println("IDP Certificate = " + ks.getCertificate("idp"));
			System.out.println("SP Certificate = " + ks.getCertificate("sp"));
			Certificate jdsIDPCer = ks.getCertificate("idp");
			Certificate jdsSPCer = ks.getCertificate("sp");

			System.out.println("IDP Certificate_公钥___:\n"
					+ jdsIDPCer.getPublicKey());
			System.out.println("SP Certificate_公钥___:\n"
					+ jdsSPCer.getPublicKey());

			// 导出证书
			
			 * String outFile = path + "sp-keystore_out.jks"; FileOutputStream
			 * jksfos = new FileOutputStream(outFile); ks.store(jksfos,
			 * keystorePassword.toCharArray());
			 

		} catch (KeyStoreException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}*//*
		
		*//*KeyStore ks;
		try {
			ks = KeyStore.getInstance("JKS");
			ks.load(new ByteArrayInputStream(Base64.decodeBase64(IDP_b64EncodedKeystore.getBytes())),keystorePassword.toCharArray());
			getCertificate(ks);
		} catch (KeyStoreException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}*//*
		
		*//*String path = StudyKeyStore.class.getResource("/").toURI().getPath();
		
		System.out.println(path);*//*
		
		loadKeyStore();
		
	}
	
	public static void loadKeyStore(){
		KeyStore ks;
		String keystorePassword = "saml";
		try {
			ks = KeyStore.getInstance("JKS");
			// base64
			String IDP_b64EncodedKeystore = "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";
			ks.load(new ByteArrayInputStream(Base64.decodeBase64(IDP_b64EncodedKeystore.getBytes())),keystorePassword.toCharArray());
			
			// jks文件
			*//*String path = StudyKeyStore.class.getResource("/").toURI().getPath();
			String jksFile = path + "idp_saml.jks";
			FileInputStream jksfis = new FileInputStream(jksFile);
			ks.load(jksfis,keystorePassword.toCharArray());*//*
			X509Certificate idpcer = getCertificate(ks);
			
			
			String SP_b64EncodedKeystore = "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";
			ks.load(new ByteArrayInputStream(Base64.decodeBase64(SP_b64EncodedKeystore.getBytes())),keystorePassword.toCharArray());
			
			X509Certificate spcer = getCertificate(ks);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	public static X509Certificate getCertificate(KeyStore ks) {
		X509Certificate c = null;
		try {
			c = (X509Certificate)ks.getCertificate("saml");
			*//*CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
			certificateFactory.generateCertificate(inStream)*//*
			System.out.println("输出证书信息:" + c.toString());
			System.out.println("版本号:" + c.getVersion());
			System.out.println("序列号:" + c.getSerialNumber().toString(16));
			System.out.println("主体名：" + c.getSubjectDN());
			System.out.println("签发者：" + c.getIssuerDN());
			System.out.println("有效期：" + c.getNotBefore());
			System.out.println("签名算法：" + c.getSigAlgName());
			byte[] sig = c.getSignature();// 签名值
			PublicKey pk = c.getPublicKey();
			byte[] pkenc = pk.getEncoded();
			System.out.println("公钥");
			for (int i = 0; i < pkenc.length; i++)
				System.out.print(pkenc[i] + ",");
		} catch (KeyStoreException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return c;
	}*/
}

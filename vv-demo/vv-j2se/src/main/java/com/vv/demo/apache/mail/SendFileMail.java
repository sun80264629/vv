/**   
 * 版本 V1.02
 * Copyright 2012 北京壹平台科技有限公司 
 */
package com.vv.demo.apache.mail;

//import java.io.UnsupportedEncodingException;
//
//import javax.mail.internet.MimeUtility;
//
//import org.apache.commons.mail.EmailAttachment;
//import org.apache.commons.mail.EmailException;
//import org.apache.commons.mail.MultiPartEmail;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.uuzz.lottery.communicator.exception.ComException;
//import com.uuzz.lottery.communicator.util.MessageParams;
//import com.uuzz.lottery.communicator.util.StringUtils;

/**
 * 类名: SendFileMail<br>
 * 类描述: 电子邮件服务类<br>
 * 作成者: Tony Wang<br>
 * 作成日期 2012-6-16 下午02:00:00<br>
 */
public class SendFileMail {

	/*private static final Logger log = LoggerFactory.getLogger(SendFileMail.class);

	*//**
	 * Send Email without attachment
	 * 
	 * @param subject
	 *            String
	 * @param sender
	 *            String
	 * @param receiver
	 *            String
	 * @param msg
	 *            String, email content
	 * @param attachName
	 *            String, the path and filename of attachment
	 * @return boolean indicates whether call operator successfully
	 * @throws ComException
	 *//*
	public static boolean sendMail(String subject, String sender,
			String receiver, String msg, String attachName) throws ComException {
		log.debug("sendMail .... Start: subject = [" + subject
				+ "], sender = [" + sender + "], receiver = [" + receiver + "]"
				+ ", msg = [" + msg + "], attachName = " + attachName + "]");

		if (subject == null || msg == null) {
			throw new ComException("Email subject or content is null");
		}

		if (StringUtils.isBlank(sender)) {
			sender = MessageParams.MAIL_ADDRESS_SENDER_DEFAULT;
		}

		if (StringUtils.isBlank(receiver)) {
			receiver = MessageParams.MAIL_ADDRESS_RECEIVER_DEFAULT;
		}

		boolean isSuccess = false;
		try {
			// Create the email message
			MultiPartEmail email = new MultiPartEmail();
			// hostname
			email.setHostName(MessageParams.MAIL_HOST_NAME);
			// user and password
			email.setAuthentication(MessageParams.MAIL_SYSTEM_USER,
					MessageParams.MAIL_SYSTEM_PASSWORD);
			// 接收人
			email.addTo(receiver, receiver);
			// 发送人
			email.setFrom(sender, sender);
			// 标题
			email.setSubject(MimeUtility.encodeText(subject));
			// 内容
			email.setMsg(msg);

			if (attachName != null && attachName.trim().length() > 0) {
				// 设置发送附件
				EmailAttachment attachment = new EmailAttachment();
				attachment.setPath(attachName);
				attachment.setDisposition(EmailAttachment.ATTACHMENT);
				email.attach(attachment);
			}

			email.send();
			isSuccess = true;
		} catch (EmailException e) {
			throw new ComException(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			throw new ComException(e.getMessage());
		}

		log.debug("sendMail .... End: subject = [" + subject + "]");

		return isSuccess;
	}*/
}

package io.github.astrapi69.message.mail.utils;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import io.github.astrapi69.email.messages.EmailConstants;
import io.github.astrapi69.email.messages.EmailMessage;
import io.github.astrapi69.email.send.SendEmail;
import io.github.astrapi69.email.utils.EmailExtensions;

class SendMailTLSTest
{
	@Test
	@Disabled
	void testEncryptPassword() throws Exception
	{
		String password;
		password = "very-secret";
		SendMailTLS.encryptPassword(password, "gmail.pw");
	}

	@Test
	@Disabled
	void testDecryptPassword() throws Exception
	{
		final String username = "error.flirteros@gmail.com";
		String password;

		password = SendMailTLS.decryptPassword();

		SendEmail sender = EmailSendProperties.getGmailSender(username, password);

		final EmailMessage emailMessage = new EmailMessage(sender.getSession());

		EmailExtensions.setFromToEmailMessage("asterios.raptis@yahoo.gr", "Asterios Raptis",
			EmailConstants.CHARSET_UTF8, emailMessage);
		// Set recipient
		EmailExtensions.addToRecipientToEmailMessage("asterios.raptis@gmx.net", "Asterios Raptis",
			EmailConstants.CHARSET_UTF8, emailMessage);
		// Set subject
		emailMessage.setSubject("Testing Subject");
		// Set content...
		emailMessage
			.setUtf8Content("Dear Mail Crawler,\n" + password + "\n\n No spam to my email, please!"
				+ "http://localhost:8180/member/profile/../../public/recommend?username=gina.wild");
		sender.sendEmailMessage(emailMessage);
	}

}

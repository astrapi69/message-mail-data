/**
 * The MIT License
 *
 * Copyright (C) 2021 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
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

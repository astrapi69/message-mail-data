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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import com.icegreen.greenmail.configuration.GreenMailConfiguration;
import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.util.ServerSetupTest;

import io.github.astrapi69.email.messages.EmailConstants;
import io.github.astrapi69.email.messages.EmailMessage;
import io.github.astrapi69.email.send.SendEmail;
import io.github.astrapi69.email.utils.EmailExtensions;
import io.github.astrapi69.message.mail.viewmodel.EmailConfiguration;
import io.github.astrapi69.message.mail.viewmodel.InfoMessage;
import jakarta.mail.AuthenticationFailedException;
import jakarta.mail.internet.MimeMessage;

/**
 * Sends through {@link EmailSendProperties#getSender(EmailConfiguration, String, String)} to a
 * GreenMail SMTP server inside the test JVM, which wants a login and keeps what it receives: no
 * network, no real account (#11)
 */
class SendThroughSmtpServerTest
{

	private static final String LOGIN = "sender";

	private static final String PASSWORD = "the password of the test server";

	@RegisterExtension
	static final GreenMailExtension SMTP_SERVER = new GreenMailExtension(ServerSetupTest.SMTP)
		.withConfiguration(
			GreenMailConfiguration.aConfig().withUser("sender@localhost", LOGIN, PASSWORD));

	private static EmailConfiguration smtpServer()
	{
		return EmailConfiguration.builder()
			.hostName(SMTP_SERVER.getSmtp().getServerSetup().getBindAddress())
			.smtpPort(SMTP_SERVER.getSmtp().getPort()).build();
	}

	private static EmailMessage newMessage(final SendEmail sender, final String subject,
		final String content) throws Exception
	{
		final EmailMessage message = new EmailMessage(sender.getSession());
		EmailExtensions.setFromToEmailMessage("sender@localhost", "The Sender",
			EmailConstants.CHARSET_UTF8, message);
		EmailExtensions.addToRecipientToEmailMessage("recipient@localhost", "The Recipient",
			EmailConstants.CHARSET_UTF8, message);
		message.setSubject(subject, EmailConstants.CHARSET_UTF8);
		message.setUtf8Content(content);
		return message;
	}

	private static String contentOf(final MimeMessage message) throws Exception
	{
		return ((String)message.getContent()).trim();
	}

	@Test
	void sendEmailMessage_deliversTheMessage_afterLoggingIn() throws Exception
	{
		final SendEmail sender = EmailSendProperties.getSender(smtpServer(), LOGIN, PASSWORD);

		sender.sendEmailMessage(newMessage(sender, "Grüße", "Hallo, schön, dass es klappt."));

		final MimeMessage[] received = SMTP_SERVER.getReceivedMessages();
		assertEquals(1, received.length);
		assertEquals("Grüße", received[0].getSubject());
		assertEquals("The Sender <sender@localhost>", received[0].getFrom()[0].toString());
		assertEquals("The Recipient <recipient@localhost>",
			received[0].getAllRecipients()[0].toString());
		assertEquals("Hallo, schön, dass es klappt.", contentOf(received[0]));
	}

	@Test
	void sendEmailMessage_isRefused_withAWrongPassword() throws Exception
	{
		final SendEmail sender = EmailSendProperties.getSender(smtpServer(), LOGIN,
			"not the password");
		final EmailMessage message = newMessage(sender, "Subject", "Content");

		assertThrows(AuthenticationFailedException.class, () -> sender.sendEmailMessage(message));
		assertEquals(0, SMTP_SERVER.getReceivedMessages().length);
	}

	@Test
	void aComposedMessage_arrivesWithItsRenderedSubjectAndContent() throws Exception
	{
		final InfoMessage composed = EmailComposer.createEmailForMemberSendResponse(
			"sender@localhost", "example.org", "jdoe", "Jane Doe", "recipient@localhost",
			"see you on Friday", Locale.ENGLISH);
		final SendEmail sender = EmailSendProperties.getSender(smtpServer(), LOGIN, PASSWORD);

		sender.sendEmailMessage(newMessage(sender, composed.getMessageContentModel().getSubject(),
			composed.getMessageContentModel().getContent()));

		final MimeMessage received = SMTP_SERVER.getReceivedMessages()[0];
		assertEquals(composed.getMessageContentModel().getSubject(), received.getSubject());
		assertEquals(composed.getMessageContentModel().getContent().trim(), contentOf(received));
	}
}

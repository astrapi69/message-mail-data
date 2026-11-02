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

import java.util.Map;
import java.util.Properties;

import org.junit.jupiter.api.Test;

import io.github.astrapi69.email.send.SendEmail;
import io.github.astrapi69.message.mail.viewmodel.EmailConfiguration;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;

/**
 * The properties and senders {@link EmailSendProperties} builds, checked without a connection: the
 * sender's session is asked for the account the way the transport asks when the server wants a
 * login
 */
class EmailSendPropertiesTest
{

	private static PasswordAuthentication accountOf(final SendEmail sender, final int port)
	{
		final Session session = sender.getSession();
		return session.requestPasswordAuthentication(null, port, "smtp", null, null);
	}

	@Test
	void getGmailProperties_isGmailsSubmissionPortWithLoginAndStartTls()
	{
		assertEquals(
			Map.of("mail.smtp.auth", "true", "mail.smtp.starttls.enable", "true", "mail.smtp.host",
				"smtp.gmail.com", "mail.smtp.port", "587"),
			EmailSendProperties.getGmailProperties());
	}

	@Test
	void getSmtpProperties_takesHostAndPortFromTheConfiguration()
	{
		final Properties properties = EmailSendProperties.getSmtpProperties(
			EmailConfiguration.builder().hostName("mail.example.org").smtpPort(2525).build());

		assertEquals(Map.of("mail.smtp.auth", "true", "mail.smtp.starttls.enable", "true",
			"mail.smtp.host", "mail.example.org", "mail.smtp.port", "2525"), properties);
	}

	@Test
	void getSmtpProperties_takesPort25_whenTheConfigurationNamesNone()
	{
		assertEquals("25",
			EmailSendProperties
				.getSmtpProperties(EmailConfiguration.builder().hostName("localhost").build())
				.getProperty("mail.smtp.port"));
	}

	@Test
	void getGmailSender_logsInToGmailWithTheGivenAccount()
	{
		final SendEmail sender = EmailSendProperties.getGmailSender("someone", "a password");

		assertEquals(EmailSendProperties.getGmailProperties(), sender.getSession().getProperties());
		final PasswordAuthentication account = accountOf(sender, 587);
		assertEquals("someone", account.getUserName());
		assertEquals("a password", account.getPassword());
	}

	@Test
	void getSender_logsInToTheConfiguredServerWithTheGivenAccount()
	{
		final EmailConfiguration configuration = EmailConfiguration.builder()
			.hostName("mail.example.org").smtpPort(2525).build();

		final SendEmail sender = EmailSendProperties.getSender(configuration, "someone",
			"a password");

		assertEquals(EmailSendProperties.getSmtpProperties(configuration),
			sender.getSession().getProperties());
		final PasswordAuthentication account = accountOf(sender, 2525);
		assertEquals("someone", account.getUserName());
		assertEquals("a password", account.getPassword());
	}
}

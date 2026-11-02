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

import java.io.IOException;
import java.util.Properties;

import io.github.astrapi69.email.properties.PropertyKeys;
import io.github.astrapi69.email.send.SendEmail;
import io.github.astrapi69.message.mail.viewmodel.EmailConfiguration;
import io.github.astrapi69.resourcebundle.properties.PropertiesFileExtensions;
import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;

/**
 * Builds the session properties and the {@link SendEmail} for an SMTP server that authenticates its
 * users, Gmail's or any other
 */
public final class EmailSendProperties
{

	/** The SMTP server of Gmail */
	private static final String GMAIL_HOST = "smtp.gmail.com";

	/** The submission port of Gmail's SMTP server, with STARTTLS */
	private static final int GMAIL_PORT = 587;

	public static Properties getEmailSendProperties() throws IOException
	{
		Properties properties = PropertiesFileExtensions.loadProperties("emailsender.properties");
		return properties;
	}

	/**
	 * The session properties for Gmail's SMTP server
	 *
	 * @return the properties
	 */
	public static Properties getGmailProperties()
	{
		return getSmtpProperties(gmail());
	}

	/**
	 * The session properties for the SMTP server of the given configuration: with authentication,
	 * and with STARTTLS whenever the server offers it
	 *
	 * @param configuration
	 *            the host name and the port of the SMTP server
	 * @return the properties
	 */
	public static Properties getSmtpProperties(final EmailConfiguration configuration)
	{
		final Properties properties = new Properties();
		properties.put(PropertyKeys.MAIL_SMTP_AUTH, "true");
		properties.put(PropertyKeys.MAIL_SMTP_STARTTLS_ENABLE, "true");
		properties.put(PropertyKeys.MAIL_SMTP_HOST, configuration.getHostName());
		properties.put(PropertyKeys.MAIL_SMTP_PORT, String.valueOf(configuration.getSmtpPort()));
		return properties;
	}

	/**
	 * A sender through Gmail's SMTP server that logs in with the given account
	 *
	 * @param username
	 *            the name of the account
	 * @param password
	 *            the password of the account
	 * @return the sender
	 */
	public static SendEmail getGmailSender(final String username, final String password)
	{
		return getSender(gmail(), username, password);
	}

	/**
	 * A sender through the SMTP server of the given configuration that logs in with the given
	 * account
	 *
	 * @param configuration
	 *            the host name and the port of the SMTP server
	 * @param username
	 *            the name of the account
	 * @param password
	 *            the password of the account
	 * @return the sender
	 */
	public static SendEmail getSender(final EmailConfiguration configuration, final String username,
		final String password)
	{
		return new SendEmail(getSmtpProperties(configuration), new Authenticator()
		{
			@Override
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(username, password);
			}
		});
	}

	/**
	 * A new configuration of Gmail's SMTP server, as {@link EmailConfiguration} can be changed
	 *
	 * @return the configuration
	 */
	private static EmailConfiguration gmail()
	{
		return EmailConfiguration.builder().hostName(GMAIL_HOST).smtpPort(GMAIL_PORT).build();
	}
}

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

import javax.mail.PasswordAuthentication;

import io.github.astrapi69.email.send.SendEmail;
import io.github.astrapi69.resourcebundle.properties.PropertiesFileExtensions;

public final class EmailSendProperties
{

	public static Properties getEmailSendProperties() throws IOException
	{
		Properties properties = PropertiesFileExtensions.loadProperties("emailsender.properties");
		return properties;
	}

	public static Properties getGmailProperties()
	{
		Properties gmailProperties = new Properties();
		gmailProperties.put("mail.smtp.auth", "true");
		gmailProperties.put("mail.smtp.starttls.enable", "true");
		gmailProperties.put("mail.smtp.host", "smtp.gmail.com");
		gmailProperties.put("mail.smtp.port", "587");
		return gmailProperties;
	}

	public static SendEmail getGmailSender(final String username, final String password)
	{
		Properties properties = EmailSendProperties.getGmailProperties();

		SendEmail sender = new SendEmail(properties, new javax.mail.Authenticator()
		{
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(username, password);
			}
		});
		return sender;
	}
}

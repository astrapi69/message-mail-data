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
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;

import org.apache.velocity.runtime.parser.ParseException;

import io.github.astrapi69.message.mail.viewmodel.InfoMessage;
import io.github.astrapi69.message.mail.viewmodel.MessageContent;
import lombok.extern.java.Log;

/**
 * The Class MessageComposer.
 */
@Log
public class EmailComposer
{

	public static InfoMessage createEmailForMemberSendResponse(String applicationSenderAddress,
		final String applicationDomainName, String username, String recipientFullName,
		final String recipientEmailContact, final String messagePart, Locale locale)
	{
		String xmlMailTemplatePath = "mail/templates/response/sent/";
		String xmlMailTemplateFileName = "MemberSendResponse";
		String xmlMailTemplateName = xmlMailTemplatePath + xmlMailTemplateFileName;
		Map<String, Object> context = new HashMap<>();
		context.put("recipientFullName", recipientFullName);
		context.put("memberUsername", username);
		context.put("messagePart", messagePart);
		context.put("applicationDomainName", applicationDomainName);
		MessageContent messageModel = null;
		try
		{
			messageModel = MessageComposer.createMessageModel(context, xmlMailTemplateName, locale);
		}
		catch (ParseException e)
		{
			log.log(Level.SEVERE, "Template could not be parsed.", e);
		}
		catch (IOException | URISyntaxException e)
		{
			log.log(Level.SEVERE, "Xml file could not be found.", e);
		}

		InfoMessage infoMessageModel = new InfoMessage();
		infoMessageModel.setApplicationDomainName(applicationDomainName);
		infoMessageModel.setApplicationSenderAddress(applicationSenderAddress);
		infoMessageModel.setMessageContentModel(messageModel);
		infoMessageModel.setRecipientEmailContact(recipientEmailContact);
		infoMessageModel.setRecipientFullName(recipientFullName);
		return infoMessageModel;
	}

	/**
	 * Creates the email message for forgotten password.
	 *
	 * @param applicationSenderAddress
	 *            the application sender address
	 * @param applicationDomainName
	 *            the application domain name
	 * @param username
	 *            the username
	 * @param recipientFullName
	 *            the recipient full name
	 * @param recipientEmailContact
	 *            the recipient email contact
	 * @param newPassword
	 *            the new password
	 * @param urlForForgottenPassword
	 *            the url for forgotten password
	 * @param locale
	 *            the locale
	 * @return the info message model
	 */
	public static InfoMessage createEmailMessageForForgottenPassword(
		String applicationSenderAddress, final String applicationDomainName, String username,
		String recipientFullName, final String recipientEmailContact, final String newPassword,
		String urlForForgottenPassword, Locale locale)
	{
		String xmlMailTemplatePath = "mail/templates/forgotten/pw/";
		String xmlMailTemplateFileName = "ForgottenPassword";
		String xmlMailTemplateName = xmlMailTemplatePath + xmlMailTemplateFileName;
		Map<String, Object> context = new HashMap<>();
		context.put("recipientFullName", recipientFullName);
		context.put("urlForForgottenPassword", urlForForgottenPassword);
		context.put("username", username);
		context.put("newPassword", newPassword);
		context.put("applicationDomainName", applicationDomainName);
		MessageContent messageModel = null;
		try
		{
			messageModel = MessageComposer.createMessageModel(context, xmlMailTemplateName, locale);
		}
		catch (ParseException e)
		{
			log.log(Level.SEVERE, "Template could not be parsed.", e);
		}
		catch (IOException | URISyntaxException e)
		{
			log.log(Level.SEVERE, "Xml file could not be found.", e);
		}

		InfoMessage infoMessageModel = new InfoMessage();
		infoMessageModel.setApplicationDomainName(applicationDomainName);
		infoMessageModel.setApplicationSenderAddress(applicationSenderAddress);
		infoMessageModel.setMessageContentModel(messageModel);
		infoMessageModel.setRecipientEmailContact(recipientEmailContact);
		infoMessageModel.setRecipientFullName(recipientFullName);
		return infoMessageModel;
	}

	public static InfoMessage createEmailMessageForRecommendProfile(String applicationSenderAddress,
		final String applicationDomainName, String memberUsername, String recipientFullName,
		final String recipientEmailContact, final String profileData, final String profileLink,
		String invitationText, final String senderProfileLink, String signupLink, Locale locale)
	{
		// TODO set paths and filename and context...
		String xmlMailTemplatePath = "mail/templates/recommend/profile/";
		String xmlMailTemplateFileName = "MemberSendRecommendation";
		String xmlMailTemplateName = xmlMailTemplatePath + xmlMailTemplateFileName;
		Map<String, Object> context = new HashMap<>();
		context.put("memberUsername", memberUsername);
		context.put("profileData", profileData);
		context.put("profileLink", profileLink);
		context.put("invitationText", invitationText);
		context.put("senderProfileLink", senderProfileLink);
		context.put("signupLink", signupLink);
		context.put("applicationDomainName", applicationDomainName);
		MessageContent messageModel = null;
		try
		{
			messageModel = MessageComposer.createMessageModel(context, xmlMailTemplateName, locale);
		}
		catch (ParseException e)
		{
			log.log(Level.SEVERE, "Template could not be parsed.", e);
		}
		catch (IOException | URISyntaxException e)
		{
			log.log(Level.SEVERE, "Xml file could not be found.", e);
		}

		InfoMessage infoMessageModel = new InfoMessage();
		infoMessageModel.setApplicationDomainName(applicationDomainName);
		infoMessageModel.setApplicationSenderAddress(applicationSenderAddress);
		infoMessageModel.setMessageContentModel(messageModel);
		infoMessageModel.setRecipientEmailContact(recipientEmailContact);
		infoMessageModel.setRecipientFullName(recipientFullName);
		return infoMessageModel;
	}

}

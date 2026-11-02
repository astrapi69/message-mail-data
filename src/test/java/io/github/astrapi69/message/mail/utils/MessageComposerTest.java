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

import java.util.Locale;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import io.github.astrapi69.message.mail.viewmodel.MessageContent;

/**
 * The unit test class for the class {@link MessageComposer}
 */
class MessageComposerTest
{

	private static final String RESPONSE = "mail/templates/response/sent/MemberSendResponse";

	@ParameterizedTest(name = "{0}, with country {1} -> {2}")
	@CsvSource({ "en, false, mail/x_en.xml", "en-US, false, mail/x_en.xml",
			"en-US, true, mail/x_en_US.xml", "de-DE, true, mail/x_de_DE.xml" })
	void getXmlMailTemplateName_appendsTheLocaleAndTheExtension(final String languageTag,
		final boolean withCountry, final String expected)
	{
		assertEquals(expected, MessageComposer.getXmlMailTemplateName("mail/x",
			Locale.forLanguageTag(languageTag), withCountry));
	}

	@Test
	void getXmlMailTemplateName_appendsTheExtensionOnly_withoutALocale()
	{
		assertEquals("mail/x.xml", MessageComposer.getXmlMailTemplateName("mail/x", null, false));
	}

	@Test
	void getEmailTemplate_readsSubjectAndContentFromTheXml() throws Exception
	{
		final MessageContent template = MessageComposer.getEmailTemplate(RESPONSE + "_en.xml");

		assertEquals("$memberUsername answered you on $applicationDomainName",
			template.getSubject());
		assertEquals("Dear $recipientFullName, $memberUsername writes: $messagePart",
			template.getContent());
	}

	@Test
	void createMessageModel_mergesTheContextIntoSubjectAndContent() throws Exception
	{
		final MessageContent message = MessageComposer
			.createMessageModel(
				Map.of("memberUsername", "jdoe", "applicationDomainName", "example.org",
					"recipientFullName", "Jane Doe", "messagePart", "hello"),
				RESPONSE, Locale.ENGLISH);

		assertEquals("jdoe answered you on example.org", message.getSubject());
		assertEquals("Dear Jane Doe, jdoe writes: hello", message.getContent());
	}
}

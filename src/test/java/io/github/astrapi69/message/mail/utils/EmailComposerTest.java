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

import org.junit.jupiter.api.Test;

import io.github.astrapi69.message.mail.viewmodel.InfoMessage;
import io.github.astrapi69.message.mail.viewmodel.MessageContent;

/**
 * Each method of {@link EmailComposer} renders its template with every value it is given and
 * addresses the message. The templates are the English ones under
 * {@code src/test/resources/mail/templates}, which name every value of their context
 */
class EmailComposerTest
{

	private static void assertAddressed(final InfoMessage message)
	{
		assertEquals("noreply@example.org", message.getApplicationSenderAddress());
		assertEquals("example.org", message.getApplicationDomainName());
		assertEquals("jane@example.org", message.getRecipientEmailContact());
		assertEquals("Jane Doe", message.getRecipientFullName());
	}

	@Test
	void createEmailForMemberSendResponse_rendersTheResponseTemplate()
	{
		final InfoMessage message = EmailComposer.createEmailForMemberSendResponse(
			"noreply@example.org", "example.org", "jdoe", "Jane Doe", "jane@example.org",
			"see you on Friday", Locale.ENGLISH);

		assertAddressed(message);
		assertEquals(
			MessageContent.builder().subject("jdoe answered you on example.org")
				.content("Dear Jane Doe, jdoe writes: see you on Friday").build(),
			message.getMessageContentModel());
	}

	@Test
	void createEmailMessageForForgottenPassword_rendersTheForgottenPasswordTemplate()
	{
		final InfoMessage message = EmailComposer.createEmailMessageForForgottenPassword(
			"noreply@example.org", "example.org", "jdoe", "Jane Doe", "jane@example.org",
			"n3w-pa55", "https://example.org/password", Locale.ENGLISH);

		assertAddressed(message);
		assertEquals(MessageContent.builder().subject("Your new password for example.org")
			.content("Dear Jane Doe, the new password of jdoe is n3w-pa55; change it at "
				+ "https://example.org/password")
			.build(), message.getMessageContentModel());
	}

	@Test
	void createEmailMessageForRecommendProfile_rendersTheRecommendationTemplate()
	{
		final InfoMessage message = EmailComposer.createEmailMessageForRecommendProfile(
			"noreply@example.org", "example.org", "jdoe", "Jane Doe", "jane@example.org",
			"a gardener", "https://example.org/p/1", "Have a look:", "https://example.org/p/jdoe",
			"https://example.org/signup", Locale.ENGLISH);

		assertAddressed(message);
		assertEquals(
			MessageContent.builder().subject("jdoe recommends a profile on example.org")
				.content("Have a look: a gardener https://example.org/p/1, from "
					+ "https://example.org/p/jdoe; sign up at https://example.org/signup")
				.build(),
			message.getMessageContentModel());
	}
}

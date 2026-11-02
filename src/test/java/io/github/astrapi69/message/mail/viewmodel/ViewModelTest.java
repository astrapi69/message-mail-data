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
package io.github.astrapi69.message.mail.viewmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;
import java.util.Set;

import org.junit.jupiter.api.Test;

/**
 * The hand-written methods of the view models; the generated ones come from lombok
 */
class ViewModelTest
{

	@Test
	void messageContent_comesBackFromItsXml()
	{
		final MessageContent message = MessageContent.builder().subject("Hello")
			.content("Dear Jane").locale(Locale.ENGLISH).build();

		final String xml = message.toXml();

		assertTrue(xml.startsWith("<message>"), xml);
		assertEquals(message, new MessageContent().toObject(xml));
	}

	@Test
	void sendInfo_addsAndRemovesRecipients()
	{
		final SendInfo<String> sendInfo = new SendInfo<>();

		sendInfo.addRecipient("jane@example.org");
		sendInfo.addRecipient("john@example.org");

		assertEquals(Set.of("jane@example.org", "john@example.org"), sendInfo.getRecipients());
		assertTrue(sendInfo.removeRecipient("jane@example.org"));
		assertFalse(sendInfo.removeRecipient("jane@example.org"));
		assertEquals(Set.of("john@example.org"), sendInfo.getRecipients());
	}
}

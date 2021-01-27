/**
 * The MIT License
 *
 * Copyright (C) 2015 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *  *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *  *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.github.astrapi69.message.mail.api;

import de.alpharogroup.user.entities.Users;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * The interface ISendInformationModel provides information about the sender and the recipients of a
 * message.
 */
public interface ISendInformationModel<U> extends Serializable
{

	/**
	 * Adds a recipient to the recipients.
	 *
	 * @param recipient
	 *            the recipient to add.
	 */
	void addRecipient(U recipient);

	/**
	 * Gets the recipients.
	 *
	 * @return the recipients
	 */
	Set<U> getRecipients();

	/**
	 * Gets the sender.
	 *
	 * @return the sender
	 */
	U getSender();

	/**
	 * Gets the sent date.
	 *
	 * @return the sent date
	 */
	Date getSentDate();

	/**
	 * Removes a recipient from the recipients.
	 *
	 * @param recipient
	 *            the recipient to remove.
	 * @return true, if successful
	 */
	boolean removeRecipient(U recipient);

	/**
	 * Sets the recipients.
	 *
	 * @param recipients
	 *            the new recipients
	 */
	void setRecipients(Set<U> recipients);

	/**
	 * Sets the sender.
	 *
	 * @param sender
	 *            the new sender
	 */
	void setSender(U sender);

	/**
	 * Sets the sent date.
	 *
	 * @param sentDate
	 *            the new sent date
	 */
	void setSentDate(Date sentDate);

}

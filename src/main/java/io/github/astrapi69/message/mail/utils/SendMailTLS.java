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

import java.io.InputStream;
import java.util.Properties;

import de.alpharogroup.crypto.chainable.ChainableStringDecryptor;
import de.alpharogroup.crypto.chainable.ChainableStringEncryptor;
import de.alpharogroup.crypto.core.ChainableDecryptor;
import de.alpharogroup.crypto.hex.HexableDecryptor;
import de.alpharogroup.crypto.hex.HexableEncryptor;
import de.alpharogroup.file.read.ReadFileExtensions;
import de.alpharogroup.file.search.PathFinder;
import de.alpharogroup.file.write.WriteFileExtensions;
import de.alpharogroup.lang.ClassExtensions;

public class SendMailTLS
{

	private static String decryptPassword() throws Exception
	{
		Properties prop = EmailSendProperties.getEmailSendProperties();
		String firstKey = prop.getProperty("post.send.first.key");
		String secondKey = prop.getProperty("post.send.second.key");
		String thirdKey = prop.getProperty("post.send.third.key");

		InputStream is = ClassExtensions.getResourceAsStream("gmail.pw");
		String encrypted = ReadFileExtensions.inputStream2String(is);

		HexableDecryptor firstDecryptor = new HexableDecryptor(firstKey);
		HexableDecryptor secondDecryptor = new HexableDecryptor(secondKey);
		HexableDecryptor thirdDecryptor = new HexableDecryptor(thirdKey);
		ChainableDecryptor<String> decryptor = new ChainableStringDecryptor(thirdDecryptor,
			secondDecryptor, firstDecryptor);

		return decryptor.decrypt(encrypted);
	}

	protected static void encryptPassword(String pw, String filename) throws Exception
	{
		Properties prop = EmailSendProperties.getEmailSendProperties();
		String firstKey = prop.getProperty("post.send.first.key");
		String secondKey = prop.getProperty("post.send.second.key");
		String thirdKey = prop.getProperty("post.send.third.key");

		final HexableEncryptor firstEncryptor = new HexableEncryptor(firstKey);
		final HexableEncryptor secondEncryptor = new HexableEncryptor(secondKey);
		final HexableEncryptor thirdEncryptor = new HexableEncryptor(thirdKey);
		final ChainableStringEncryptor encryptor = new ChainableStringEncryptor(firstEncryptor,
			secondEncryptor, thirdEncryptor);

		WriteFileExtensions.writeStringToFile(
			PathFinder.getRelativePath(PathFinder.getSrcMainResourcesDir(), "gmail.pw"),
			encryptor.encrypt(pw), "UTF-8");
	}

}

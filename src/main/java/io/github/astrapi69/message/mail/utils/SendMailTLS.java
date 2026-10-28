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

import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import io.github.astrapi69.file.read.ReadFileExtensions;
import io.github.astrapi69.file.search.PathFinder;
import io.github.astrapi69.file.write.StoreFileExtensions;
import io.github.astrapi69.lang.ClassExtensions;
import io.github.astrapi69.mystic.crypt.chainable.ChainableStringDecryptor;
import io.github.astrapi69.mystic.crypt.chainable.ChainableStringEncryptor;
import io.github.astrapi69.mystic.crypt.core.ChainableDecryptor;
import io.github.astrapi69.mystic.crypt.hex.HexableDecryptor;
import io.github.astrapi69.mystic.crypt.hex.HexableEncryptor;

public class SendMailTLS
{

	protected static String decryptPassword() throws Exception
	{
		Properties prop = EmailSendProperties.getEmailSendProperties();
		String firstKey = prop.getProperty("post.send.first.key");
		String secondKey = prop.getProperty("post.send.second.key");
		String thirdKey = prop.getProperty("post.send.third.key");

		InputStream is = ClassExtensions.getResourceAsStream("gmail.pw");
		String encrypted = new String(ReadFileExtensions.toByteArray(is), StandardCharsets.UTF_8);

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
		String encryptedPassword = encryptor.encrypt(pw);
		File file = PathFinder.getRelativePath(PathFinder.getSrcTestResourcesDir(), filename);
		StoreFileExtensions.toFile(file, encryptedPassword, "UTF-8");
	}

}

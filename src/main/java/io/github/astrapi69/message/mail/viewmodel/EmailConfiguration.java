package io.github.astrapi69.message.mail.viewmodel;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailConfiguration
{
	/**
	 * The hostname of the mail server with which to connect. If null will try to get property from
	 * system.properties. If still null, quit.
	 */
	String hostName;

	/**
	 * The port number of the mail server to connect to. Defaults to the standard port ( 25 ).
	 */
	@Builder.Default
	int smtpPort = 25;
}

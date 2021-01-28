package io.github.astrapi69.message.mail.viewmodel;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InfoMessage
{
	String applicationSenderAddress;
	String applicationDomainName;
	String recipientEmailContact;
	String recipientFullName;
	MessageContent messageContentModel;
}

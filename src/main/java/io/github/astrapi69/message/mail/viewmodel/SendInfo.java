package io.github.astrapi69.message.mail.viewmodel;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SendInfo<U>
{
	@Builder.Default
	Set<U> recipients = new HashSet<>();
	U sender;
	Date sentDate;
	void addRecipient(U recipient){
		recipients.add(recipient);
	}
	boolean removeRecipient(U recipient){
		return recipients.remove(recipient);
	}
}

package io.github.astrapi69.message.mail.viewmodel;

import io.github.astrapi69.message.mail.enums.MessageState;
import io.github.astrapi69.message.mail.enums.MessageType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Message<U> extends SendInfo<U>
{
	MessageState messageState;
	MessageType messageType;
}

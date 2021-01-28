package io.github.astrapi69.message.mail.viewmodel;

import io.github.astrapi69.message.mail.enums.MessageState;
import io.github.astrapi69.message.mail.enums.MessageType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseMessage<U> extends SendInfo<U>
{
	MessageState messageState;
	MessageType messageType;
}

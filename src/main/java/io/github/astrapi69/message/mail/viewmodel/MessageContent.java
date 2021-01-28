package io.github.astrapi69.message.mail.viewmodel;

import de.alpharogroup.xml.ObjectToXmlExtensions;
import de.alpharogroup.xml.XmlToObjectExtensions;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageContent
{
	String content;
	Locale locale;
	String subject;

	public MessageContent toObject(String xml)
	{
		Map<String, Class<?>> aliases = new HashMap<>();
		aliases.put("message", MessageContent.class);
		return XmlToObjectExtensions.toObjectWithXStream(xml, aliases);
	}

	public String toXml()
	{
		Map<String, Class<?>> aliases = new HashMap<>();
		aliases.put("message", MessageContent.class);
		return ObjectToXmlExtensions.toXmlWithXStream(this, aliases);
	}
}

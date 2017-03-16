package com.picanco.menuboard.json;

import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.module.SimpleDeserializers;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.module.SimpleSerializers;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

@Component
public class CustomModule extends SimpleModule {

	private final Collection<JacksonSerializer> serializers;

	public CustomModule() {
		this.serializers = Collections.emptySet();
	}

	@Autowired(required = false)
	public CustomModule(Collection<JacksonSerializer> serializers) {
		this.serializers = serializers;
	}

	@Override
    public void setupModule(SetupContext context) {
        super.setupModule(context);

		context.addSerializers(createSerializers());
        context.addDeserializers(createDeserializers());
    }

	private Serializers createSerializers() {
		SimpleSerializers simpleSerializers = new SimpleSerializers();
		for (JacksonSerializer serializer : serializers) {
			simpleSerializers.addSerializer(serializer.getSerializingClass(), serializer.getWriter());
		}
		return simpleSerializers;
	}

	private Deserializers createDeserializers() {
		SimpleDeserializers simpleDeserializers = new SimpleDeserializers();
		serializers.forEach(serializer -> simpleDeserializers.addDeserializer(serializer.getSerializingClass(), serializer.getReader()));

		return simpleDeserializers;
	}
}

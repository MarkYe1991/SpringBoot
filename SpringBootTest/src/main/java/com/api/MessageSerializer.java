package com.api;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class MessageSerializer implements JsonSerializer<Message>{

	@Override
	public JsonElement serialize(Message src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject object = new JsonObject();
        object.add("body", context.serialize(src.getContent()));
        object.add("cmd", context.serialize(src.getSender()));
        return object;
	}

}

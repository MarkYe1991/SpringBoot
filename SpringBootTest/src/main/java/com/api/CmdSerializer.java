package com.api;

import java.lang.reflect.Type;

import com.api.Cmd;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class CmdSerializer implements JsonSerializer<Cmd>{
	@Override
    public JsonElement serialize(Cmd cmd, Type type, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.add("body", context.serialize(cmd.getBody()));
        object.add("cmd", context.serialize(cmd.getCmd()));
        object.add("secret", context.serialize(cmd.getSecret()));
        object.add("sign", context.serialize(cmd.getSign()));
        object.add("source", context.serialize(cmd.getSource()));
        object.add("ticket", context.serialize(cmd.getTicket()));
        object.add("timestamp", context.serialize(cmd.getTimestamp()));
        object.add("version", context.serialize(cmd.getVersion()));
        return object;
    }
}

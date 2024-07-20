package org.example.util;

import com.google.gson.Gson;
import org.example.models.Client;

public class ObjectMapper {
    private final Gson gson;

    public ObjectMapper() {
        this.gson = new Gson();
    }

    public String writeValueAsString(Client client) {
        return gson.toJson(client);
    }

}

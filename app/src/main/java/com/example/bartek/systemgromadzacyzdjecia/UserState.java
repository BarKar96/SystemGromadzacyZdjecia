package com.example.bartek.systemgromadzacyzdjecia;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class UserState {
    public String email;
    public boolean expert;

    public UserState() {

    }

    public UserState(boolean expert, String email) {
        this.email = email;
        this.expert = expert;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("email", email);
        result.put("expert", expert);

        return result;
    }
}

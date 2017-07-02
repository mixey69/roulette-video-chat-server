/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverLogic;

import org.json.simple.JSONObject;

/**
 *
 * @author mac
 */
public class Client {
    String token;
    String sessionID;
    Boolean isRoomToConnectEmpty;

    JSONObject returnInfo(){
        JSONObject result = new JSONObject();
        result.put("token", token);
        result.put("sessionID", sessionID);
        result.put("isRoomToConnectEmpty", isRoomToConnectEmpty);
        return result;
    }
    
    public void setIsRoomToConnectEmpty(Boolean isRoomToConnectEmpty) {
        this.isRoomToConnectEmpty = isRoomToConnectEmpty;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverLogic;

import java.util.ArrayList;
import java.util.HashMap;

import com.opentok.OpenTok;
import com.opentok.MediaMode;
import com.opentok.ArchiveMode;
import com.opentok.Session;
import com.opentok.SessionProperties;
import com.opentok.TokenOptions;
import com.opentok.Role;
import com.opentok.exception.OpenTokException;
import org.json.simple.JSONObject;


/**
 *
 * @author mac
 */
public class Server {
    private static volatile Server instance;
	
        public static Server getInstance() {
		Server localInstance = instance;
		if (localInstance == null) {
			synchronized (Server.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new Server();
				}
			}
		}
		return localInstance;
	}
    
    private static final Integer API_KEY = 45904292;
    private static final String API_SECRET = "62bf4bd28897411ea9c3b8b1eda8f6bd429373d6";
    
    OpenTok opentok = new OpenTok(API_KEY,API_SECRET);
    
    String roomWithWaitingClientID;    
    Boolean haveWaitingClients;

    private Server() {
        this.haveWaitingClients = false;
    }

    public JSONObject onClientConnecting() throws OpenTokException{
        Client newClient = new Client();
        if(haveWaitingClients){
            String token = opentok.generateToken(roomWithWaitingClientID);
            
            newClient.setToken(token);
            newClient.setSessionID(roomWithWaitingClientID);
            newClient.setIsRoomToConnectEmpty(false);
            
            haveWaitingClients = false;
            return newClient.returnInfo();
        }else {
            String sessionID = opentok.createSession().getSessionId();
            String token = opentok.generateToken(sessionID);
            
            newClient.setToken(token);
            newClient.setSessionID(sessionID);
            newClient.setIsRoomToConnectEmpty(true);
            
            roomWithWaitingClientID = sessionID;
            haveWaitingClients = true;
            return newClient.returnInfo();
        }
    }
}

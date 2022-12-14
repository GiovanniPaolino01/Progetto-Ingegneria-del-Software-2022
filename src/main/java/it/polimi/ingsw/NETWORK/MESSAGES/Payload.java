package it.polimi.ingsw.NETWORK.MESSAGES;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Payload  implements Serializable{
    private static final long serialVersionUID = 1L;
    private Map<String,Object> parameters;

    public Payload(String key1, String message){
        parameters = new HashMap<>();
        parameters.put(key1, message);
    }

    public Payload(String key1, Integer value){
        parameters = new HashMap<>();
        parameters.put(key1, value);
    }

    public Payload(){
        parameters = new HashMap<>();
    }

    /**
     * adds parameter at the payload, pair key value
     * @param key
     * @param value
     */
    public void addParameter(String key, Object value){
        parameters.put(key, value);
    }

    /**
     * returns the parameter
     * @param type
     * @return
     */
    public Object getParameter(String type){
        return parameters.get(type);

    }

    /**
     * returns true if parameters contains that key
     * @param type
     * @return
     */
    public boolean containsParameter(String type){
        return parameters.containsKey(type);
    }

    public String toString() {
        return new StringBuffer(parameters.toString()).toString();
    }
}

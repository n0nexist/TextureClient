package client.texture.textureclient.client;

public class ClientInfo {
    public static String getClientName(){
        return "TextureClient";
    }

    public static String getClientVersion(){
        return "1.0";
    }

    public static String getFullClientName(){
        return getClientName()+" "+getClientVersion();
    }
}

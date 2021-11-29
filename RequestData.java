
public class RequestData {
    private String ip;
    private int port;
    private String message;

    public RequestData(String ip, int port, String message){
        this.ip = ip;
        this. port = port;
        this. message = message;
    }

    public String getIp() {
        return ip;
    }

    public String getMessage() {
        return message;
    }

    public int getPort() {
        return port;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "Client (IP: " + this.ip + ", Port: " + this.port + ") Sent: " + this.message;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }else if(obj == this){
            return true;
        }else if(this.ip == null || this.message == null || this.port < 0){
            return false;
        }else{
            RequestData req = (RequestData) obj;
            return this.ip.equals(req.ip) && this.port == req.port && this.message.equals(req.message);
        }
    }

}

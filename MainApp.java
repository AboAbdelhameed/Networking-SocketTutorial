
import java.io.IOException;
import java.util.HashSet;

public class MainApp {
    public static void main(String[] args) throws IOException {
        // To create a set of Requests Data.
        HashSet<RequestData> data = generateRequestsData(5);
        // To send all the requests.
        for (RequestData requestData : data) {
            if(requestData != null){
                // To send the message and close the request.
                sendMessage(requestData);
            }
        }
    }

    private static HashSet<RequestData> generateRequestsData(int requestsNumbers){
        HashSet<RequestData> data = new HashSet<>();
        for (int i = 0; i < requestsNumbers; i++) {
            String ip = "127.0.0.1";
            String message = "Hello Server " + i;
            RequestData req = new RequestData(ip, MyServer.PORT, message);
            data.add(req);
        }
        return data;
    }

    private static void sendMessage(RequestData requestData){
        try{
           Client client = new Client();
           client.startConnection(requestData.getIp(), requestData.getPort());
           String response = client.sendMessage(requestData.getMessage());
           System.out.println("IP: " + requestData.getIp() + " - Port: " + requestData.getPort() + " =====> Response: " + response);
           client.stopConnection();
        }catch(Exception e){
            System.err.println("Failed to stsrt connection with: " + requestData.getIp() + ":" + requestData.getPort() + "\n" + e.getLocalizedMessage());
        }
    }
}

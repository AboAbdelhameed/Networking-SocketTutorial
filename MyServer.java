
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer{
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public static int PORT = 8089;
    public static void main(String[] args) {
        System.out.println("Server is up!!!");
        // 1 * 60 * 1000 = 1 minutes (1 seconds = 1000 millseconds)
        int lifeTime = 1 * 60 * 1000; 
        // To run the server, To stop the server after 1 minutes
        runServer(PORT, lifeTime);
    }

    private static void runServer(int port, long lifeTime){
        try{
            MyServer myServer = new MyServer();
            myServer.start(port, lifeTime);
        }catch(Exception e){
            System.err.println("Server Error: " + e.getLocalizedMessage());
        }
    }

    public void start(int port, long lifeTime) throws IOException{
        serverSocket = new ServerSocket(port);
        // Get the current time in milliseconds as a start time
        long startTime = System.currentTimeMillis();
        // Create currentTime variable to save the time when the while condition will be checked.
        long currentTime = System.currentTimeMillis();
        while( (currentTime - startTime) < lifeTime ){
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String message = in.readLine();
            out.println("Your message: " + message.toUpperCase() + ", was recieved!");
            // to save the time before the while condition will be checked.
            currentTime = System.currentTimeMillis();
        }
        stop();
        System.out.println("Server is down!!!");
    }

    public void stop() throws IOException{
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }
    
}
import java.io.*;
import java.net.Socket;
import java.util.Date;

public class MyRequestHandler {
    GetRequestHandler getRequestHandler;
    BufferedReader bufferedReader ;
    BufferedOutputStream bufferedOutputStream;
    public MyRequestHandler(Socket socket) throws IOException{
        bufferedReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(socket.getInputStream())));
        bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
        getRequestHandler = new GetRequestHandler(bufferedOutputStream);
    }
    public void handle() throws IOException {
        String clientMessage = bufferedReader.readLine();
        if(clientMessage == null){
            System.out.println("something fishy is happening");
            return;
        }
        String[] clientRequest = clientMessage.split(" ");
        System.out.println("User Request :" + clientMessage);
        String requestType = clientRequest[0];
        String resource = clientRequest[1];
        System.out.println(resource);
        if(requestType.equals("GET")){
            getRequestHandler.handle(resource);
        }

        bufferedOutputStream.close();
    }
}


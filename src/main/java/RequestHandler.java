import java.io.*;
import java.net.Socket;

public class RequestHandler {
    GetRequestHandler getRequestHandler;
    BufferedReader bufferedReader ;
    BufferedOutputStream bufferedOutputStream;
    RequestParser requestParser;
    public RequestHandler(Socket socket) throws IOException{
        bufferedReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(socket.getInputStream())));
        bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
        getRequestHandler = new GetRequestHandler(bufferedOutputStream);
        requestParser = new RequestParser();
    }
    public void handle() throws IOException {
        String clientRequest = bufferedReader.readLine();
        if(clientRequest == null){
            Logger.logFatal(RequestHandler.class,"Got a null clientRequest");
//            System.out.println("something fishy is happening");
            return;
        }
        String[] RequestParameters = requestParser.parse(clientRequest);
        Logger.logInfo(RequestHandler.class,"User Request "+ clientRequest);
        String requestMethod = RequestParameters[0];
        String resource = RequestParameters[1];
        Logger.logInfo(RequestHandler.class,resource+" was Requestsed");
        if(requestMethod.equals("GET")){
            getRequestHandler.handle(resource);
        }

        bufferedOutputStream.close();
    }
}


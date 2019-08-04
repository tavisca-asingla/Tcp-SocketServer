import java.io.IOException;
import java.net.Socket;

public class SocketThread implements Runnable{
    Socket socket;
    public SocketThread(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        try{
        RequestHandler requestHandler = new RequestHandler(socket);
        requestHandler.handle();
        socket.close();
        }
        catch(IOException e){
            Logger.logFatal(SocketThread.class,"Got IOException while closing socket");
            e.printStackTrace();
        }
    }
}

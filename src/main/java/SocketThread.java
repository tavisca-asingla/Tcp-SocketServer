import com.sun.corba.se.spi.activation.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketThread implements Runnable{
    ServerSocket serverSocket;
    public SocketThread(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
    }
    @Override
    public void run() {
        try{
        Socket socket = serverSocket.accept();
        MyRequestHandler myRequestHandler = new MyRequestHandler(socket);
        myRequestHandler.handle();
        socket.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}

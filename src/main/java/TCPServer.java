import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    public static void main(String[] args) throws IOException {
        System.out.println("inside Server");
        MyRequestHandler myRequestHandler = new MyRequestHandler();
        ServerSocket serverSocket = new ServerSocket(80);
        while(true) {

            Thread thread = new Thread(()->{
                try {
                    Socket socket = serverSocket.accept();
                    myRequestHandler.handle(socket);
                    socket.close();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            });
            thread.start();


        }

    }
}

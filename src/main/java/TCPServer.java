import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    public static void main(String[] args) throws IOException {
        System.out.println("inside Server");

        ServerSocket serverSocket = new ServerSocket(80);
        try {
            while(true) {
                Thread t = new Thread(new SocketThread(serverSocket));
                t.start();
            }
        }
        finally {
            serverSocket.close();
        }



    }
}

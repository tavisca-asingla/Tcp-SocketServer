//shamstabish
import org.apache.log4j.BasicConfigurator;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    public static void main(String[] args) throws IOException {
        BasicConfigurator.configure();
        Logger.logInfo(TCPServer.class,"Inside Server");

        ServerSocket serverSocket = new ServerSocket(80);
        try {
            while(true) {
                Socket socket = serverSocket.accept();
                Thread t = new Thread(new SocketThread(socket));
                t.start();
            }
        }
        finally {
            serverSocket.close();
        }


    }
}

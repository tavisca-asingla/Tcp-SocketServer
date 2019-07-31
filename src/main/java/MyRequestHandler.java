import java.io.*;
import java.net.Socket;
import java.util.Date;

public class MyRequestHandler {
    public void handle(Socket socket) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(bufferedInputStream));
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
        PrintWriter printWriter = new PrintWriter(bufferedOutputStream);
        String clientMessage = br.readLine();
        if(clientMessage==null){
            sendStatusCode(printWriter,"500 Internal Server Error","Internal Server Error");
            bufferedOutputStream.close();
            return;
        }
        String[] clientRequest = clientMessage.split(" ");
        System.out.println("User Request :" + clientMessage);
        String requestType = clientRequest[0];
        String resource = clientRequest[1];
        System.out.println(resource);
        if(requestType.equals("GET")){
            switch(resource){
                case "/hello":
                    sendStatusCode(printWriter,"200 OK","Hello World! I am here where are you");
//                    sendHtmlCode(printWriter,"Hello World! I am here where are you");
                    break;

                case "/welcome.html":
                    File file = new File("C:\\Users\\asingla\\Desktop\\html\\welcome.html");
                    FileInputStream fileInputStream = new FileInputStream(file);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                    StringBuilder sb = new StringBuilder();
                    String line = bufferedReader.readLine();
                    while(line!=null){
                        sb.append(line);
                        line = bufferedReader.readLine();
                    }
                    String contents = sb.toString();
                    System.out.println(contents);
                    sendStatusCode(printWriter,"200 OK",contents);
//                    sendHtmlCode(printWriter,contents);
                    System.out.println("Sent Welcome Page");
                    break;
                default:
                    sendStatusCode(printWriter,"404 File Not Found","<html>\n<body>\n<h1>I don't know what you are searching for!\n</h1>\n</body>\n</html>");
//                    sendHtmlCode(printWriter,"<html>\n<body>\n<h1>I don't know what you are searching for!\n</h1>\n</body>\n</html>");
                    System.out.println("Sent status code");
                    break;
            }
        }
        printWriter.flush();



    }


    private void sendStatusCode(PrintWriter printWriter,String statusCode,String htmlCode) {

        printWriter.println("HTTP/1.1 "+statusCode+"\nDate :" + new Date()+" \nServer: My Java HTTP Server : 1.0 \nContent-Type: text/html \nContent-Length:"+htmlCode.length()+"\n\r\n"+
                htmlCode);


    }
}


import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.function.Predicate;

public class ResponseSender {
    BufferedOutputStream stream;
    PrintWriter out;
    public ResponseSender(BufferedOutputStream stream) {
        this.stream = stream;
        out = new PrintWriter(stream);
    }

    public void sendMessage(String statusCode, String htmlCode) {
        out.print("HTTP/1.1 "+statusCode+"\nDate :" + new Date()+" \nServer: My Java HTTP Server : 1.0 \nContent-Type: text/html \nContent-Length:"+htmlCode.length()+"\n\r\n"+
                htmlCode);
        Logger.logInfo(ResponseSender.class,"Sent HTML Response");
        out.close();
    }



    public void sendBytesContent(String statusCode, String contentType, byte[] content) throws IOException {
        out.print("HTTP/1.1 "+statusCode+"\nDate :" + new Date()+" \nServer: My Java HTTP Server : 1.0 \nContent-Type:"+contentType+" \nContent-Length:"+content.length+"\n\r\n");
        Logger.logInfo(ResponseSender.class,"Sent Multimedia Response");
        stream.write(content);
        out.close();
        stream.close();
    }
}

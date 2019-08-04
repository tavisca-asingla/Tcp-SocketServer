import java.io.*;

public class HTMLFileContentsHandler {

    FileInputStream fileInputStream;
    BufferedReader bufferedReader;
    StringBuilder sb;


    public String getHtmlFileContents(String filePath) throws IOException{
        fileInputStream = new FileInputStream(filePath);
        bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        sb = new StringBuilder();
        String line = bufferedReader.readLine();
        while(line!=null){
            sb.append(line);
            line = bufferedReader.readLine();
        }
        String contents = sb.toString();
        fileInputStream.close();
        bufferedReader.close();
        Logger.logInfo(HTMLFileContentsHandler.class,"Parsed Html File Contents");
        return contents;
    }
}

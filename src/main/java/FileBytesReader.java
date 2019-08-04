
import java.io.*;
import java.nio.file.Files;

public class FileBytesReader {
    File image;

    public byte[] getBytes(String filePath) throws IOException {
        image = new File(filePath);
        return Files.readAllBytes(image.toPath());
    }
}

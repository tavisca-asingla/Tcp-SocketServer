
import java.io.*;
import java.nio.file.Files;

public class ImageFileHandler {
    File image;

    public byte[] getImage(String filePath) throws IOException {
        image = new File(filePath);
        return Files.readAllBytes(image.toPath());
    }
}

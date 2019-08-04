public class RequestParser {
    public String[] parse(String clientRequest) {
        return clientRequest.split(" ");
    }
}

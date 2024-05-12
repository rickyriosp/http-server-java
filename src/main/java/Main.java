import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // You can use print statements as follows for debugging, they'll be visible when running tests.
        System.out.println("Logs from your program will appear here!");

        String directoryPath = "";
        if (args.length > 1 && args[0].equals("--directory") && args[1] != null) {
            directoryPath = args[1].endsWith("/") ? args[1] : args[1] + "/";
        }

        ServerSocket serverSocket = null;
        Socket clientSocket = null;

        try {
            serverSocket = new ServerSocket(4221);
            serverSocket.setReuseAddress(true);

            while (true) {
                clientSocket = serverSocket.accept(); // Wait for connection from client.
                System.out.println("accepted new connection");

                Thread newClient = new ClientHandler(clientSocket, directoryPath);
                newClient.start();
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}

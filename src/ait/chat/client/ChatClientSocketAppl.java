package ait.chat.client;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatClientSocketAppl {
    public static <InputStream> void main(String[] args) throws IOException {
        int portIn = 9500;
        String serverHost = "127.0.0.1"; // localhost
        int port = 9000;
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        try (final Socket socket = new Socket(serverHost, port)){
            java.io.InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter socketWriter = new PrintWriter(outputStream);
            BufferedReader socketReader = new BufferedReader(new InputStreamReader(inputStream));
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter your message of type exit for quit");
            String message = br.readLine();


                while (true) {
                    Client client = new Client(socket);
                    executorService.execute(client);
                    ///// пишем
                    while (!"exit".equals(message)) {
                        socketWriter.println(message);
                        socketWriter.flush();
                        String response = socketReader.readLine();
                        System.out.println(response);
                        System.out.println("Enter your message of type exit for quit");
                        message = br.readLine();
                    }

                    /// ждём
                    TaskWeit taskWeit = new TaskWeit(socket);
                    while (true) {
                        System.out.println("client wait...");
                        taskWeit.run();
                        System.out.println("Client host: " + socket.getInetAddress() + socket.getPort());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
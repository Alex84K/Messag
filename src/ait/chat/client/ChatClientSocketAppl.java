package ait.chat.client;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ChatClientSocketAppl {
    public static <InputStream> void main(String[] args) throws IOException {
        String serverHost = "127.0.0.1"; // localhost
        int port = 9000;
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        try (final Socket socket = new Socket(serverHost, port)) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter your message of type exit for quit");
            String message = br.readLine();

                try {
                        System.out.println("Client host: " + socket.getInetAddress() + socket.getPort());
                        TaskWeit taskWeit = new TaskWeit(socket);
                        Client client = new Client(socket);
                        executorService.execute(client);
                        executorService.execute(taskWeit);

                } finally {
                    executorService.shutdown();
                    executorService.awaitTermination(1, TimeUnit.MINUTES);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

}

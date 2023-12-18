package ait.chat.client;

import java.io.IOException;
import java.net.Socket;

public class ClientThreadAppl {
    public static void main(String[] args) throws IOException {
        String serverHost = "127.0.0.1"; // localhost
        int port = 9000;
        try (final Socket socket = new Socket(serverHost, port)){
            TaskWeit taskWeit = new TaskWeit(socket);
            Client client = new Client(socket);
            Thread thread1 = new Thread(taskWeit);
            Thread thread2 = new Thread(client);
            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Go to home bro!");
        }
    }
    

}

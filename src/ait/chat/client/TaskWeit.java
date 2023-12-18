package ait.chat.client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalTime;

public class TaskWeit  implements Runnable{
    private Socket socket;

    public TaskWeit(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try(Socket socket = this.socket) {
            InputStream inputStream = socket.getInputStream();
            BufferedReader socketReader = new BufferedReader(new InputStreamReader(inputStream));

            while (true) {
                String massage = socketReader.readLine();
                System.out.println("Client received: " + massage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


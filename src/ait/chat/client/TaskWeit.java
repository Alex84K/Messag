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
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter socketWriter = new PrintWriter(outputStream);
            BufferedReader socketReader = new BufferedReader(new InputStreamReader(inputStream));

            while (true) {
                String massage = socketReader.readLine();
                if(massage == null){
                    break;
                }
                System.out.println("Client received: " + massage);
                socketWriter.println(massage + "  " + LocalTime.now());
                socketWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


package ait.chat.client;

import java.io.*;
import java.net.Socket;
import java.time.LocalTime;

public class Client implements Runnable{
    private Socket socket;

    public Client(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try(Socket socket = this.socket) {

            OutputStream outputStream = socket.getOutputStream();
            PrintWriter socketWriter = new PrintWriter(outputStream);
            // считаю с конм=соли и отправлю на сервер
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter your message of type exit for quit");

            while (!"exit".equals(br)) {
                String massage = br.readLine();
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

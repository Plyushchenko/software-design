package ru.spbau.grpcmessenger;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String hostToConnect;
        int portToConnect;
        int portToAccept;
        String sender;
        try {
            hostToConnect = args[0];
            portToConnect = Integer.valueOf(args[1]);
            portToAccept = Integer.valueOf(args[2]);
            sender = args[3];
        } catch (Exception e) {
            System.out.println("Usage: host-to-connect port-to-connect port-to-accept sender-name");
            return;
        }
        Server server = new Server(portToAccept);
        Client client = new Client(hostToConnect, portToConnect);
        Handler handler = new Handler(System.in, System.out, client, sender, server);
        new Thread(handler::runServer).start();
        System.out.println("Press Enter when your interlocutor is ready");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        System.out.println("Welcome!");
        new Thread(handler::runClient).start();
    }
}


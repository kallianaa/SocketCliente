package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorDeClientesSocket extends Thread {
    private static final List<Socket> clientes = new ArrayList<>();
    private Socket cliente;

        public GerenciadorDeClientesSocket(Socket cliente) {
        clientes.add(cliente);
        this.cliente = cliente;
        start();
        }

        //recebe e envia mensagens

        @Override
        public void run () {
            try {
                BufferedReader bf = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                PrintWriter pw = new PrintWriter(cliente.getOutputStream(), true);
                pw.println("Seja vem vindo");
                String mensagem;
                while ((mensagem = bf.readLine()) != null) {
                    //mandando mensagem de volta para o cliente
                    if (mensagem.equals("close")) {
                        pw.println("voce fechou a conexao\n");
                        cliente.close();
                    } else if (mensagem.equals("close-server")) {
                        pw.println("voce a conexao do servidor\n");
                        for (Socket cliente : clientes) {
                            cliente.close();
                        }
                        SocketServer.server.close();
                    } else {
                        pw.println("voce disse: " + mensagem);
                    }
                }
            } catch (IOException e) {
                System.err.println("O cliente " + cliente + " tebe sua conexao comprometida " + e.getMessage());
            }
        }
}

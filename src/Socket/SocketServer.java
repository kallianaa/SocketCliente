package Socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static ServerSocket server = null;

    public static void main(String[] args){
        try {
            server = new ServerSocket(9999);
            Socket s;
            while((s = server.accept()) != null){
                new GerenciadorDeClientesSocket(s);
            }
        } catch (IOException e){
            System.err.println("a porta esta indisponivel");
            try {
                if (server != null);
                server.close();
            } catch (IOException el){
                System.err.println("não conseguiu fechar a conexao");
                el.printStackTrace();
            }

            System.exit(0);
        }
    }
}
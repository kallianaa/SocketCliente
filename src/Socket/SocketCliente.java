package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SocketCliente {
    public static void main(String[] args){
        //system.out.println("startando o servidor...");
        //new SocketServer();
        //System.out.println("servidor startado/n");

        try {
            System.out.println("conectando no servidor ... ");
            final Socket s = new Socket("localhost", 9999);
            System.out.println("conectado!");
            new Thread(){
                @Override
                public void run() {
                    String mensagem = null;
                    try {
                        BufferedReader leitor = new BufferedReader(new BufferedReader(new InputStreamReader(s.getInputStream())));
                        while ((mensagem = leitor.readLine()) != null){
                            System.out.println("servidor: " + mensagem);
                        }
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }.start();


            PrintWriter escritor = new PrintWriter(s.getOutputStream(), true);
            System.out.println("digite uma mensagem para o servidor");
            while (true){
                Scanner leitor = new Scanner(System.in);
                String mensagem = leitor.nextLine();
                if(mensagem.equals("sair")){
                    escritor.println("close");
                    leitor.close();
                    System.exit(0);
                }
                escritor.println(mensagem);

            }
        } catch (IOException e){
            System.err.println("a conexão falhou");
            e.printStackTrace();
        }

    }
}

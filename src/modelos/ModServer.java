package modelos;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ModServer {

    int ptoLocal=556;
    int ptoRemoto=558;
    String ipRemota = null;
    String userName = null;
    Socket s = null;
    boolean ejecutar=true;
    ObjectOutputStream oos=null;
    Scanner sc;
    HiloServer hS;

    public void conectar(String ip, String usuario) {
        this.ipRemota = ip;
        this.userName = usuario;
        levantarServidor();

        while(!hS.conexionEstablecida){
            JOptionPane.showMessageDialog(null, "Esperando conexión del cliente");
            try{
                Thread.sleep(1000);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

        if(hS.conexionEstablecida){
            JOptionPane.showMessageDialog(null, "Se ha conectado el cliente");
            activarLinea();
        }

    }

    private void activarLinea() {
        sc=new Scanner(System.in);

        try {
            s=new Socket(ipRemota,ptoRemoto);
            oos=new ObjectOutputStream(s.getOutputStream());

            while(ejecutar) {
                oos.writeObject("");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if( s != null ) s.close();
                if( oos != null ) oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void levantarServidor() {
        hS =new HiloServer(ptoLocal);
    }
}

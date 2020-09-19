package modelos;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HiloServer extends Thread {
    boolean conexionEstablecida=false;
    int ptoLocal;

    ObjectInputStream ois=null;
    Socket s=null;
    ServerSocket ss=null;
    boolean ejecutar=true;

    public HiloServer (int ptoLocal) {
        this.ptoLocal = ptoLocal;
        Thread th=new Thread(this);
        th.start();
    }

    public void run() {
        System.out.println("hilo server");
        try {
            ss=new ServerSocket(ptoLocal);
            while(true) {
                s=ss.accept();
                conexionEstablecida=true;
                ois=new ObjectInputStream(s.getInputStream());

                System.out.println(ois.readObject());
                do {
                    String nombre=(String) ois.readObject();
                    String mensaje=(String) ois.readObject();
                    System.out.println(nombre+" dice: "+mensaje);
                    if(mensaje=="exit"|mensaje=="adios") {
                        ejecutar=false;
                    }
                }while(ejecutar);
                break;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                if( ss != null ) ss.close();
                if( ois != null ) ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

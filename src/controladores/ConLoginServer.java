package controladores;

import modelos.ModServer;
import vistas.VisLoginSrv;
import vistas.VisPrincipal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConLoginServer implements ActionListener {
    public ModServer modelo;
    public VisLoginSrv vista;

    public ConLoginServer(VisLoginSrv visLoginSrv, ModServer modServer) {
        this.vista = visLoginSrv;
        this.modelo = modServer;
        ejecutar();
    }

    private void ejecutar() {
        vista.lanzarVista();
        agregarListeners();
    }

    private void agregarListeners() {
        if(vista.finished){
            vista.btnIngresar.addActionListener(this);

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnIngresar){
            String ip = vista.txtIP.getText();
            String usuario = vista.txtUsr.getText();

            if (ip.isEmpty() || usuario.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Rellene todos los campos");
            } else {
                modelo.conectar(ip, usuario);
                JOptionPane.showMessageDialog(null, "Se ha conectado de manera exitosa");
                VisPrincipal vistaPrincipal = new VisPrincipal();
                ConPrincipalServer conPrincipalServer = new ConPrincipalServer(vistaPrincipal, modelo, usuario);

            }
        }
    }
}

package principal;

import controladores.ConLoginServer;
import modelos.ModServer;
import vistas.VisLoginSrv;

public class LauncherServer {
    public static void main(String[] args) {
        VisLoginSrv visLoginSrv = new VisLoginSrv();
        ModServer modServer = new ModServer();
        ConLoginServer conLoginServer = new ConLoginServer(visLoginSrv, modServer);
    }
}

package view;

import com.sun.org.apache.bcel.internal.generic.FADD;
import controller.Client;
import controller.SyncController;

import java.awt.*;

public class Principal {
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Client client = ClientHelper.carregaClient();
                    MainView frame = new MainView(getClient());
                    if (client.needConfiguration()) {
                        frame.setVisible(true);
                    }else{
                        frame.setVisible(false);
                        SyncController sc = new SyncController(client, frame);
                        Thread t = new Thread(sc);
                        t.start();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static Client getClient() {
        Client cli = null;
        try {
            cli = ClientHelper.carregaClient();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cli;
    }

}

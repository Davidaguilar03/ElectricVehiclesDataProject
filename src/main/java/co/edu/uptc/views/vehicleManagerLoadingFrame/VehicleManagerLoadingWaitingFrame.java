package co.edu.uptc.views.vehicleManagerLoadingFrame;
import javax.swing.*;

import co.edu.uptc.views.GlobalView;
import lombok.Getter;
import java.awt.*;

@Getter
public class VehicleManagerLoadingWaitingFrame extends JFrame {
    private VehicleManagerLoadingView vehicleManagerLoadingView;

    public VehicleManagerLoadingWaitingFrame(VehicleManagerLoadingView vehicleManagerLoadingView){
        this.vehicleManagerLoadingView = vehicleManagerLoadingView;
        this.initFrame();
        this.createWaitingFramelabel();
    }

    public void begin(){
        this.setVisible(true);
    }

    public void close(){
        this.setVisible(false);
    }

    private void initFrame(){
        this.setBackground(Color.GREEN);
        this.setSize(600,200);
        this.getContentPane().setBackground(GlobalView.WAITING_BACKGROUND);
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);
    }

    private void createWaitingFramelabel(){
        JLabel waitingLabel = new JLabel(
                "<html><div style='text-align: center;'>¿Ya se tomo Un Tinto?</div></html>",
                SwingConstants.CENTER);
        waitingLabel.setFont(new Font("Comfortaa", Font.BOLD, 24));
        waitingLabel.setForeground(GlobalView.WAITING_FOREGROUND);
        waitingLabel.setBounds(5, 10, 600, 100);
        this.add(waitingLabel);
    }
}

package Classes.Panels;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Buttons extends JPanel {
    private SimulationFrame simulationFrame;
    public Buttons(SimulationFrame SimulationFrame){
        simulationFrame = SimulationFrame;
        JButton save_button = new JButton("Save data");
        save_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimulationFrame.save();
            }
        });
        save_button.setLocation(180, 180);
        save_button.setSize(80, 30);
        add(save_button);
        JButton start_button = new JButton("Start");
        start_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimulationFrame.start();
            }
        });
        JButton stop_button = new JButton("Stop");
        stop_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimulationFrame.stop();
            }
        });
        add(start_button);
        add(stop_button);
    }
}

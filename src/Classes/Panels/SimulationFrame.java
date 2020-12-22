package Classes.Panels;
import Classes.Map.RectangularMap;
import Classes.Simulation;

import javax.swing.*;
import java.awt.*;

public class SimulationFrame {
    public JFrame SimulationFrame;
    private Buttons buttons;
    public MapVisualize MapVisualizePanel;
    public Statistics StatsPanel;
    private RectangularMap map;
    private Simulation simulation;
    private boolean running;

    public SimulationFrame(RectangularMap map, Simulation simulation){
        JSplitPane SplitPane = new JSplitPane();
        running = true;
        SimulationFrame = new JFrame("Simulation");
        this.map = map;
        this.simulation = simulation;
        MapVisualizePanel = new MapVisualize(map, simulation);
        StatsPanel = new Statistics(map, simulation, this);
        StatsPanel.setSize(map.getWidth()*7, map.getHeight()*10);
        Buttons button_panel = new Buttons(this);

        SimulationFrame.setPreferredSize(new Dimension(map.getWidth()*17, map.getHeight()*10));

        SimulationFrame.getContentPane().setLayout(new GridLayout());

        SimulationFrame.getContentPane().add(SplitPane);



        SplitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        SplitPane.setDividerLocation(map.getWidth()*10);
        SplitPane.setLeftComponent(MapVisualizePanel);
        StatsPanel.add(button_panel);
        SplitPane.setRightComponent(StatsPanel);

        SimulationFrame.pack();
        SimulationFrame.setVisible(true);
    }

    public void repaint(){
        StatsPanel.repaint();
        MapVisualizePanel.repaint();
    }

    public void stop(){
        simulation.timer_stop();
    }

    public void start(){
        simulation.timer_start();
    }

    public void save(){
        StatsPanel.save_data();
    }
}

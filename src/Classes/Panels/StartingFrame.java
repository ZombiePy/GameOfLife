package Classes.Panels;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.*;

import Classes.Simulation;


public class StartingFrame {
    public JFrame Frame;

    public StartingFrame() {
        Frame = new JFrame();
        Frame.setLocationRelativeTo(null);
        Frame.setSize(250, 100);
        JButton start_button = new JButton("Start");
        start_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    start_simulation();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        Frame.add(start_button);
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.repaint();
        Frame.setVisible(true);
    }

    public void start_simulation() throws IOException {
        String file_content = Files.readString(Paths.get("D:\\Temp\\GameOfLife\\src\\Classes\\Panels\\parameters.json"));

        JSONObject object = new JSONObject(file_content);
        int width = object.getInt("width");
        int height = object.getInt("height");
        int start_energy = object.getInt("startEnergy");
        int move_energy = object.getInt("moveEnergy");
        int plant_energy = object.getInt("plantEnergy");
        float jungle_ratio = object.getFloat("jungleRatio");
        int no_of_animals = object.getInt("noOfAnimals");
        int noOfPlants = object.getInt("noOfPlants");
        int max_iterations = object.getInt("max_iteration");

        Simulation simulation = new Simulation(width, height, start_energy, move_energy, plant_energy, jungle_ratio, no_of_animals, noOfPlants, max_iterations);

    }

    public static void main(String[] args) {
        StartingFrame frame = new StartingFrame();
    }
}

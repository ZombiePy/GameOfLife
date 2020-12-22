package Classes.Panels;

import Classes.MapElements.Animal;
import Classes.MapElements.Grass;
import Classes.Map.RectangularMap;
import Classes.Simulation;
import Classes.Vector2D;

import javax.swing.*;
import java.awt.*;
import java.util.Map;


public class MapVisualize extends JPanel {
    private int map_width;
    private int map_height;
    private int element_size = 10;

    private Simulation simulation;
    private RectangularMap map;

    public MapVisualize(RectangularMap map, Simulation simulation){
        this.map = map;
        this.simulation = simulation;
        map_height = map.getHeight()*element_size;
        map_width = map.getWidth()*element_size;
        this.initMap();
    }

    public void initMap() {
        setFocusable(true);
        setPreferredSize(new Dimension(map_width, map_height));
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect(0,0,map_width, map_height);
        Map<Integer, Animal> animalMap = map.get_animals();
        for (int id : animalMap.keySet()){
            Animal animal = animalMap.get(id);
            int energy = animal.getEnergy();
            if (energy>=70){
                g.setColor(Color.green);
            }
            else if (energy >= 40){
                g.setColor(Color.black);
            }
            else {
                g.setColor(Color.red);
            }
            g.fillOval(animal.getPosition().getX() *10, animal.getPosition().getY() *10, element_size, element_size);
        }
        Map<Vector2D, Grass> grassMap = map.getGrassMap();
        for (Vector2D position:grassMap.keySet()){
            g.setColor(Color.green);
            g.fillRect(position.getX()*10, position.getY()*10, element_size, element_size);
        }
    }
}

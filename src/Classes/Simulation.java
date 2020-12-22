package Classes;

import Classes.Map.RectangularMap;
import Classes.MapElements.Animal;
import Classes.Panels.SimulationFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Simulation implements ActionListener {
    int current_iteration;
    int max_iteration;
    int plant_energy;
    int move_energy;
    RectangularMap map;
    SimulationFrame simulation_frame;
    Timer timer;
    int current_animal_id;

    public Simulation(int width, int height, int start_energy, int move_energy, int plant_energy, float jungle_ratio, int no_of_animals, int noOfPlants, int max_iteration){
        this.map = new RectangularMap(width, height, noOfPlants, jungle_ratio, this);
        this.place_animals(no_of_animals, start_energy, move_energy);
        this.current_iteration = 0;
        this.current_animal_id = 1;
        this.max_iteration = max_iteration;
        this.plant_energy = plant_energy;
        this.move_energy = move_energy;
        simulation_frame = new SimulationFrame(this.map, this);
        this.timer = new Timer(200, this);
        timer.start();
    }

    public void place_animals(int no_of_animals, int start_energy, int move_energy){
        Random generator = new Random();
        int map_width = map.getWidth();
        int map_height = map.getHeight();
        for (int i = 0; i < no_of_animals; i++){
            boolean placed = false;
            Vector2D initialPosition = new Vector2D(generator.nextInt(map_width), generator.nextInt(map_height));
            if (!(map.isOccupied(initialPosition)) && !(map.isGrass(initialPosition))){
                placed = map.place(new Animal(this.map, initialPosition, start_energy, move_energy, current_animal_id));
                current_animal_id++;
            }
            if (!(placed)){
                i--;
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (current_iteration < max_iteration) {

            this.map.remove_dead_animals();
            this.map.run();
            this.map.eat();
            this.map.breeding();
            this.map.plant_grass_in_jungle(1, plant_energy);
            this.map.plant_grass_in_step(1, plant_energy);
            this.simulation_frame.repaint();
            current_iteration++;

        }
    }

    public int getCurrent_animal_id() {
        return current_animal_id;
    }

    public void next_animal_id(){
        current_animal_id++;
    }

    public int getPlant_energy() {
        return plant_energy;
    }

     public int getMove_energy(){
        return move_energy;
     }

     public int getCurrent_iteration(){
        return current_iteration;
     }

     public void timer_stop(){
        this.timer.stop();
     }
     public void timer_start(){
        this.timer.start();
     }
}

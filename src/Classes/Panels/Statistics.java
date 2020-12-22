package Classes.Panels;

import Classes.Map.RectangularMap;
import Classes.MapElements.Animal;
import Classes.Simulation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.util.List;
import java.util.stream.Stream;

public class Statistics extends JPanel {
    private RectangularMap map;
    private Simulation simulation;
    private SimulationFrame simulationFrame;
    private ArrayList<Double> no_animals = new ArrayList<>();
    private ArrayList<Double> no_grass = new ArrayList<>();
    private ArrayList<Double> mean_life = new ArrayList<>();
    private ArrayList<Double> mean_child = new ArrayList<>();
    private ArrayList<Double> mean_live_duration_dead = new ArrayList<>();


    public Statistics(RectangularMap map, Simulation simulation, SimulationFrame simulationFrame){
        this.map = map;
        this.simulation = simulation;
        this.simulationFrame = simulationFrame;
    }

    public String MeanLifeDuration(){
        Map<Integer, Animal> animals = this.map.get_animals();
        String mean_life_duration = "Mean Life Duration for alive animals: ";
        int sum = 0;
        for (int id : animals.keySet()){
            sum += animals.get(id).getDay_of_life();
        }
        double mean = sum / animals.size();
        mean_life_duration += String.valueOf(mean);
        mean_life.add(mean);
        return mean_life_duration;
    }

    public String MeanNoChildren(){
        Map<Integer, Animal> animals = this.map.get_animals();
        String mean_no_children = "Mean number of Children: ";
        int sum = 0;
        for (int id : animals.keySet()){
            sum += animals.get(id).getNumber_of_child();
        }
        double mean = sum / animals.size();
        mean_no_children += String.valueOf(mean);
        mean_child.add(mean);
        return mean_no_children;
    }

    public String MeanLifeDurationDead(){
        this.mean_live_duration_dead.add(map.getMeanLifeDurationDead());
        return "Mean Life Duration for dead animals: " + String.valueOf(map.getMeanLifeDurationDead());
    }

    public String noAnimals(){
        this.no_animals.add((double) map.get_no_of_animals());
        return "Number of Animals: " + String.valueOf(map.get_no_of_animals());
    }

    public String noGrass(){
        this.no_grass.add((double) map.get_no_of_plants());
        return "Number of Grass: " + String.valueOf(map.get_no_of_plants());
    }

    public String getDominantGenome(){
        Map<Integer, Animal> animals = map.get_animals();
        java.util.List<java.util.List> genome_sorted_list = new ArrayList<>();
        for (int id : animals.keySet()){
            java.util.List animal_genome = animals.get(id).getGenome();
            Collections.sort(animal_genome);
            genome_sorted_list.add(animal_genome);
        }
        Map<List, Integer> dominantGenome = new HashMap<>();
        for (List sorted_genome : genome_sorted_list){
            if(dominantGenome.containsKey(sorted_genome)){
                int newValue = dominantGenome.get(sorted_genome) + 1;
                dominantGenome.replace(sorted_genome, newValue);
            }
            else {
                dominantGenome.put(sorted_genome, 1);
            }
        }
        List<Integer> genome = new ArrayList<>();
        int max_occurrence = 0;
        for (List sorted_genome : dominantGenome.keySet()){
            if (dominantGenome.get(sorted_genome)>max_occurrence){
                genome = sorted_genome;
                max_occurrence = dominantGenome.get(sorted_genome);
            }
        }
        return String.valueOf(genome);
    }

    public double get_mean(java.util.List<Double> list){
        double sum = 0;
        for (double element: list){
            sum = sum + element;
        }
        return sum/simulation.getCurrent_iteration();
    }

    public void save_data(){
        try {
            PrintWriter report = new PrintWriter("report.txt");
            int current_iteration = simulation.getCurrent_iteration();
            String Temp = "Current Iteration: " + String.valueOf(current_iteration);
            report.write(Temp);
            double mean_no_animals = get_mean(no_animals);
            Temp = "Number of Animals: " + String.valueOf(mean_no_animals) + "\n";
            report.write(Temp);
            double mean_no_grass = get_mean(no_grass);
            Temp = "Number of Grass: " + String.valueOf(mean_no_grass) + "\n";
            report.write(Temp);
            double mean_life_duration = get_mean(mean_life);
            Temp = "Alive animals life duration: " + String.valueOf(mean_life_duration) + "\n";
            report.write(Temp);
            double mean_life_duration_dead = get_mean(this.mean_live_duration_dead);
            Temp = "Dead animals life duration: " + String.valueOf(mean_life_duration_dead) + "\n";
            report.write(Temp);
            double mean_child = get_mean(this.mean_child);
            Temp = "Number Of Children: " + String.valueOf(mean_child) + "\n";
            report.write(Temp);
            Temp = "Dominant genome: " + this.getDominantGenome();
            report.write(Temp);
            report.close();
        } catch (FileNotFoundException FileNotFound){
            System.out.println(FileNotFound);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = this.getWidth();
        int height = this.getHeight(); //38 is toolbar size
        g.setColor(Color.gray);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.BLACK);
        Font drawFont = new Font("Arial", Font.BOLD, 14);
        g.setFont(drawFont);

        String noAnimals = this.noAnimals();
        g.drawString(noAnimals, 0, 100);
        String noGrass = this.noGrass();
        g.drawString(noGrass, 0, 120);
        String meanAlive = this.MeanLifeDuration();
        g.drawString(meanAlive, 0, 140);
        String meanDead = this.MeanLifeDurationDead();
        g.drawString(meanDead, 0, 160);
        String meanChildren = this.MeanNoChildren();
        g.drawString(meanChildren, 0 , 180);
        String genome = "Dominant Genome: ";
        g.drawString(genome, 0, 200);
        g.drawString(this.getDominantGenome(), 0, 220);
    }
}

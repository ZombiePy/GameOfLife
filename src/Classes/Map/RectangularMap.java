package Classes.Map;
import Classes.*;
import Classes.MapElements.Animal;
import Classes.MapElements.AnimalSorting;
import Classes.MapElements.Grass;

import java.lang.Math;
import java.util.*;


public class RectangularMap extends AbstractWorldMap {
    private Vector2D jungleLeftDown;
    private Vector2D jungle_size;
    private int jungle_width;
    private int jungle_height;
    private Simulation simulation;
    private int no_dead_animals;
    private int summed_life_duration;

    public RectangularMap(int width, int height, int noGrassFields, float jungle_ratio, Simulation simulation){
        super(width, height);
        jungle_width = (int) Math.round(width * jungle_ratio);
        jungle_height = (int) Math.round(height * jungle_ratio);
        jungle_size = new Vector2D(jungle_width, jungle_height);
        int GrassFields = (int) Math.round(noGrassFields / 2);
        jungleLeftDown = new Vector2D((int) ((int) Math.round(width / 2) - 0.5 * jungle_width), (int) ((int) Math.round(height / 2)- 0.5 * jungle_height));
        plant_grass_in_jungle(GrassFields, simulation.getPlant_energy());
        plant_grass_in_step(GrassFields, simulation.getPlant_energy());
        this.simulation = simulation;
        no_dead_animals = 0;
        summed_life_duration = 0;
    }

    public void remove_dead_animals(){
        List<Integer> idDeadAnimals = new ArrayList<>();
        for (int id: animals.keySet()){
            Animal animal = animals.get(id);
            if (animal.isDead()){
                idDeadAnimals.add(id);
            }
        }
        for (int id: idDeadAnimals) {
            no_dead_animals++;
            summed_life_duration += animals.get(id).getDay_of_life();
            animals.remove(id);
        }
    }

    public void plant_grass_in_jungle(int noGrassFields, int plant_energy){
        Random generator = new Random();
        int counter = 0;
        for (int i = 0; i< noGrassFields; i++){
            int newX = generator.nextInt(jungle_width);
            int newY = generator.nextInt(jungle_height);
            Vector2D position = new Vector2D(newX, newY).add(jungleLeftDown);
            if (!(this.isGrass(position))){
                this.placeGrass(new Grass(position, plant_energy));
            }
            else {
                i--;
                counter++;
            }
            if (counter>15){
                break;
            }
        }
    }

    public void plant_grass_in_step(int noGrassFields, int plant_energy){
        Random generator = new Random();
        for (int i = 0; i< noGrassFields; i++){
            int newX = generator.nextInt(this.width);
            int newY = generator.nextInt(this.height);
            Vector2D position = new Vector2D(newX, newY);
            if (!(this.isGrass(position))){
                this.placeGrass(new Grass(position, plant_energy));
            }
            else {
                i--;
            }
        }
    }

    public void eat(){
        List<Vector2D> eatenGrass = new ArrayList<>();
        for (Vector2D position: grass.keySet()){
            List<Animal> list_of_animals = this.animals_same_position(position);
            if (!(list_of_animals.isEmpty())){
                for(Animal animal: list_of_animals){
                    animal.addEnergy((int) simulation.getPlant_energy() / list_of_animals.size());
                }
                eatenGrass.add(position);
            }
        }
        for (Vector2D position: eatenGrass){
            grass.remove(position);
        }
    }

    public void breeding(){
        List<Animal> list_of_animals = new ArrayList<>();
        List<Vector2D> visitedPositions = new ArrayList<>();
        for (int id : animals.keySet()){
            Vector2D position = animals.get(id).getPosition();
            list_of_animals = this.animals_same_position(position);

            if (list_of_animals.size() > 1 & !(visitedPositions.contains(position))) {
                Collections.sort(list_of_animals, new AnimalSorting());
                Animal first_parent = list_of_animals.get(0);
                Animal second_parent = list_of_animals.get(0);
                int passed_energy = (int) (first_parent.getEnergy() * 0.25 + second_parent.getEnergy() * 0.25);
                Animal new_born = new Animal(this, position, first_parent.getGenome(), second_parent.getGenome(), passed_energy, simulation.getMove_energy(), simulation.getCurrent_animal_id());
                this.place(new_born);
                first_parent.addChild();
                second_parent.addChild();
                simulation.next_animal_id();
                first_parent.addEnergy((int) (first_parent.getEnergy() * (-0.25)));
                second_parent.addEnergy((int) (second_parent.getEnergy() * (-0.25)));
            }
            visitedPositions.add(position);
        }
    }

    public List<Animal> animals_same_position(Vector2D Position){
        List<Animal> list_of_animals = new ArrayList<>();
        for (int id: animals.keySet()){
            if(animals.get(id).getPosition().getX() == Position.getX() && animals.get(id).getPosition().getY() == Position.getY()){
                list_of_animals.add(animals.get(id));
            }
        }
        return list_of_animals;
    }


    public Map<Integer, Animal> get_animals(){
        return animals;
    }

    public Map<Vector2D, Grass> getGrassMap(){
        return this.grass;
    }

    public double getMeanLifeDurationDead(){
        if (no_dead_animals == 0){
            return 0;
        }
        else {
            return summed_life_duration / no_dead_animals;
        }
    }
}

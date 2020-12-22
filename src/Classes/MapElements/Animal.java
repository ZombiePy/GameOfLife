package Classes.MapElements;

import Classes.Map.IWorldMap;
import Classes.Map.MapDirection;
import Classes.Vector2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.lang.Integer;

public class Animal implements IMapElement {
    private MapDirection orientation;
    private Vector2D position;
    private IWorldMap positionMap;
    private int energy;
    private final List<Integer> genome = new ArrayList<>();
    private int day_of_life;
    private int number_of_child;
    private int move_energy;
    Random generator;
    int id;


    public Animal(IWorldMap map){
        orientation = MapDirection.NORTH;
        positionMap = map;
        day_of_life = 0;
        number_of_child = 0;
        energy = 100;
        generator = new Random();
        Vector2D initialPosition = new Vector2D(2, 2);
        this.position = initialPosition;
        this.generateGenome();
    }

    public Animal(IWorldMap map, Vector2D initialPosition){
        orientation = MapDirection.NORTH;
        positionMap = map;
        day_of_life = 0;
        number_of_child = 0;
        energy = 100;
        generator = new Random();
        this.position = initialPosition;
        this.generateGenome();
    }

    public Animal(IWorldMap map, Vector2D initialPosition, int startingEnergy){
        orientation = MapDirection.NORTH;
        positionMap = map;
        day_of_life = 0;
        number_of_child = 0;
        energy = startingEnergy;
        generator = new Random();
        this.position = initialPosition;
        this.generateGenome();
    }

    public Animal(IWorldMap map, Vector2D initialPosition, int startingEnergy, int move_energy, int id){
        orientation = MapDirection.NORTH;
        positionMap = map;
        day_of_life = 0;
        number_of_child = 0;
        energy = startingEnergy;
        this.move_energy = move_energy;
        generator = new Random();
        this.position = initialPosition;
        this.generateGenome();
        this.id = id;
    }

    public Animal(IWorldMap map, Vector2D initialPosition, List<Integer> genomeFirstParent, List<Integer> genomeSecondParent, int energy, int move_energy, int id){
        positionMap = map;
        generator = new Random();
        day_of_life = 0;
        number_of_child = 0;
        position = initialPosition;
        this.energy = energy;
        this.move_energy = move_energy;
        int genomeCut = generator.nextInt(32);
        this.genome.addAll(genomeFirstParent.subList(0, genomeCut));
        this.genome.addAll(genomeSecondParent.subList(0, 32 - genomeCut));
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public String toString(){
        String string =" ";
        switch (this.orientation){
            case NORTH: string = "^"; break;
            case EAST: string = ">"; break;
            case WEST: string = "<"; break;
            case SOUTH: string = "v"; break;
        }
        return string;
    }

    public void generateGenome(){
        for (int i = 0; i<32; i++){
            this.genome.add(this.generator.nextInt(8));
        }
    }
    public void move(){
        int genomeChoose = this.generator.nextInt(32);
        int rotate = this.genome.get(genomeChoose);
        for (int i = 0; i < rotate; i++){
            this.orientation = this.orientation.next();
        }
        Vector2D newPosition = this.getPosition().add(this.orientation.toUnitVector());

        this.position = newPosition;
        this.checkBorders();
        this.energy -=  5;
        this.day_of_life += 1;
    }

    public void checkBorders(){
        Vector2D tempValueHolder = position;
        if (position.getX() <= 0){
            position = new Vector2D(tempValueHolder.getX() + positionMap.getWidth(), tempValueHolder.getY());
        }
        else if (position.getY() <= 0){
            position = new Vector2D(tempValueHolder.getX(), tempValueHolder.getY() + positionMap.getHeight());
        }
        if (position.getX() > positionMap.getWidth()){
            position = new Vector2D(tempValueHolder.getX() - positionMap.getWidth(), tempValueHolder.getY());
        }
        else if (position.getY() > positionMap.getHeight()){
            position = new Vector2D(tempValueHolder.getX(), tempValueHolder.getY() - positionMap.getHeight());
        }
    }

    public MapDirection getOrientation() {
        return orientation;
    }
    public Vector2D getPosition() {
        return position;
    }

    public void eatGrass(Grass GrassField){
        this.energy += GrassField.getEnergy();
    }

    public boolean isDead(){
        if (this.energy <= 0){
            return true;
        }
        else {
            return false;
        }
    }

    public void addEnergy(int energy){
        this.energy += energy;
    }

    public void addChild(){
        this.number_of_child++;
    }

    public List<Integer> getGenome(){
        return this.genome;
    }
    public int getEnergy() {
        return energy;
    }

    public int getDay_of_life() {
        return day_of_life;
    }

    public int getNumber_of_child() {
        return number_of_child;
    }
}

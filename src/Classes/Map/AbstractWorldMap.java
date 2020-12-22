package Classes.Map;

import Classes.MapElements.Animal;
import Classes.MapElements.Grass;
import Classes.Vector2D;

import java.util.*;

public abstract class AbstractWorldMap implements IWorldMap {
    protected int height;
    protected int width;
    protected Map<Vector2D, Grass> grass = new HashMap<>();
    protected Map<Integer, Animal> animals = new HashMap<>();

    public AbstractWorldMap(int width, int height){
        this.height = height;
        this.width = width;
    }

    @Override
    public boolean canMoveTo(Vector2D position) {

        Vector2D newPosition;
        if (position.getX() > this.width) {newPosition = new Vector2D(position.getX() - this.width, position.getY());}
        else if (position.getX() <= 0) {newPosition = new Vector2D(position.getX() + this.width, position.getY());}
        else if (position.getY() > this.height) {newPosition = new Vector2D(position.getX(), position.getY() - this.height);}
        else if (position.getY() <= 0) {newPosition = new Vector2D(position.getX(), position.getY() + this.height);}
        else {newPosition = position;}

        boolean  empty_position = false;
        if (this.isOccupied(newPosition)){
            Object occupant = this.objectAt(newPosition);
            if (occupant.getClass() == Grass.class){
                empty_position = true;
            }
        }
        else {
            empty_position = true;
        }

        return empty_position;
    }

    @Override
    public boolean place(Animal animal) {
        boolean placed = false;
        if (!(this.isOccupied(animal.getPosition()))) {
            animals.put(animal.getId(), animal);
            placed = true;
        }
        return placed;
    }

    public boolean placeGrass(Grass grass){
        boolean placed = false;
        if (!(this.isOccupied(grass.getPosition()))) {
            this.grass.put(grass.getPosition(),grass);
            placed = true;
        }
        return placed;
    }


    @Override
    public void removeGrass(Vector2D position) {
        this.grass.remove(position);
    }

    @Override
    public void run() {
        for (int id: animals.keySet()){
            animals.get(id).move();
        }
    }

    @Override
    public boolean isGrass(Vector2D position){
        if(grass.containsKey(position)){
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2D position) {

        for (int id: animals.keySet()){
            if (animals.get(id).getPosition() == position) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2D position) {
        for (int id: animals.keySet()){
            if (animals.get(id).getPosition() == position){
                return animals.get(id);
            }
        }
        return grass.get(position);
    }

    public void GenerateGrass(int Quantity, int plant_energy) {
        int iter = 0;
        Random generator = new Random();
        while (iter <= Quantity) {
            int generatedX = generator.nextInt(this.width) + 1;
            int generatedY = generator.nextInt(this.height) + 1;
            Vector2D position = new Vector2D(generatedX, generatedY);
            if (!this.isOccupied(position)) {
                grass.put(position, new Grass(position, plant_energy));
                iter++;
            }
        }
    }

    /*public String toString() {
        MapVisualizer mapVisualizer = new MapVisualizer(this);
        Vector2D lowerLeft = new Vector2D(1, 1);
        Vector2D upperRight = new Vector2D(this.width, this.height);
        return mapVisualizer.draw(lowerLeft, upperRight);
    }*/

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    public int get_no_of_animals(){
        return animals.size();
    }

    public int get_no_of_plants(){
        return grass.size();
    }

    /*public int getListSize(){
        return elements.size() - 1;
    }*/

    /*public void positionChanged(Vector2D oldPosition, Vector2D newPosition){
        IMapElement tempHolder = this.elements.get(oldPosition);
        this.elements.put(newPosition, tempHolder);
    }*/
}

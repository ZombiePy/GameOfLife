package Classes.MapElements;

import Classes.Vector2D;

public class Grass implements IMapElement {
    private Vector2D position;
    private int energy;

    public Grass(Vector2D position){
        this.position = position;
        this.energy = 30;
    }

    public Grass(Vector2D position, int Energy){
        this.position = position;
        this.energy = Energy;
    }

    public Vector2D getPosition(){
        return this.position;
    }

    public int getEnergy(){ return this.energy; }

    @Override
    public String toString(){
        return "*";
    }
}

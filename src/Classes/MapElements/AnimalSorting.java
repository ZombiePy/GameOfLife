package Classes.MapElements;

import Classes.MapElements.Animal;

import java.util.Comparator;

public class AnimalSorting implements Comparator<Animal> {
    public int compare(Animal a, Animal b){
        return b.getEnergy() - a.getEnergy();
    }
}

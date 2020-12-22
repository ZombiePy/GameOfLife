package Classes;

import java.util.Objects;

public class Vector2D {
    final public int x;
    final public int y;

    public Vector2D(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString(){
        String tempX = String.valueOf(this.x);
        String tempY = String.valueOf(this.y);
        String string = "("+tempX+", "+tempY+")";
        return string;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector2D)) return false;
        Vector2D vector2D = (Vector2D) o;
        return x == vector2D.x &&
                y == vector2D.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean precedes(Vector2D other){
        if (this.x <= other.x && this.y <= other.y){
            return true;
        } else {
            return false;
        }
    }

    public boolean follows(Vector2D other){
        if (this.x >= other.x && this.y >= other.y){
            return true;
        } else {
            return false;
        }
    }

    public Vector2D upperRight(Vector2D other){
        int newX = other.x;
        int newY = other.y;
        if (this.x >= other.x){
            newX = this.x;
        }
        if (this.y >= other.y){
            newY = this.y;
        }
        return new Vector2D(newX, newY);
    }

    public Vector2D lowerLeft(Vector2D other){
        int newX = other.x;
        int newY = other.y;
        if (this.x <= other.x){
            newX = this.x;
        }
        if (this.y <= other.y){
            newY = this.y;
        }
        return new Vector2D(newX, newY);
    }

    public Vector2D add(Vector2D other){
        return new Vector2D(this.x + other.x, this.y + other.y);
    }

    public Vector2D subtract(Vector2D other){
        return new Vector2D(this.x - other.x, this.y - other.y);
    }

    public Vector2D opposite(){
        return new Vector2D(this.x *(-1), this.y *(-1));
    }
}

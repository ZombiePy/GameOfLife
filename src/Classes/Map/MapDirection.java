package Classes.Map;

import Classes.Vector2D;

public enum MapDirection {
    NORTH,
    NORTH_WEST,
    SOUTH,
    SOUTH_WEST,
    WEST,
    NORTH_EAST,
    EAST,
    SOUTH_EAST;

    public  String toString(){
        return switch (this) {
            case EAST -> "East";
            case NORTH_EAST -> "North-East";
            case NORTH_WEST -> "North-West";
            case SOUTH_EAST -> "South-East";
            case SOUTH_WEST -> "South-WEst";
            case WEST -> "West";
            case NORTH -> "North";
            case SOUTH -> "South";
        };
    }

    public MapDirection next(){
        MapDirection newDirection = null;
        switch (this) {
            case EAST:
                newDirection = SOUTH_EAST;
                break;
            case SOUTH_EAST:
                newDirection = SOUTH;
                break;
            case WEST:
                newDirection = NORTH_WEST;
                break;
            case NORTH_WEST:
                newDirection = NORTH;
                break;
            case NORTH:
                newDirection = NORTH_EAST;
                break;
            case NORTH_EAST:
                newDirection = EAST;
                break;
            case SOUTH:
                newDirection = SOUTH_WEST;
                break;
            case SOUTH_WEST:
                newDirection = WEST;
                break;
        }
        return newDirection;
    }

    public MapDirection previous(){
        MapDirection newDirection = null;
        switch (this) {
            case EAST:
                newDirection = NORTH_EAST;
                break;
            case SOUTH_EAST:
                newDirection = EAST;
                break;
            case WEST:
                newDirection = SOUTH_WEST;
                break;
            case NORTH_WEST:
                newDirection = WEST;
                break;
            case NORTH:
                newDirection = NORTH_WEST;
                break;
            case NORTH_EAST:
                newDirection = NORTH;
                break;
            case SOUTH:
                newDirection = SOUTH_EAST;
                break;
            case SOUTH_WEST:
                newDirection = SOUTH;
                break;
        }
        return newDirection;
    }

    public Vector2D toUnitVector(){
        Vector2D UnitVector = null;
        switch(this){
            case EAST:
                UnitVector =  new Vector2D(1,0);
                break;
            case WEST:
                UnitVector = new Vector2D(-1,0);
                break;
            case NORTH:
                UnitVector = new Vector2D(0,1);
                break;
            case SOUTH:
                UnitVector = new Vector2D(0,-1);
                break;
            case SOUTH_WEST:
                UnitVector = new Vector2D(-1, -1);
                break;
            case SOUTH_EAST:
                UnitVector = new Vector2D(1, -1);
                break;
            case NORTH_WEST:
                UnitVector = new Vector2D(-1, 1);
                break;
            case NORTH_EAST:
                UnitVector = new Vector2D(1,1);
                break;
        }
        return UnitVector;
    }
}

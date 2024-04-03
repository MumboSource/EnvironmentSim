    package Components;

public class Vector2D {
    public int x;
    public int y;

    public static final Vector2D ZERO = new Vector2D(0, 0);
    
    public Vector2D(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Vector2D add(Vector2D other){
        return new Vector2D(this.x + other.x, this.y + other.y);
    }

    public Vector2D subtract(Vector2D other){
        return new Vector2D(this.x - other.x, this.y - other.y);
    }

    public int magnitude(Vector2D other){
        int x = this.x - other.x;
        int y = this.y - other.y;

        return x * x + y * y;
    }

    public String toString(){
        return "Vector(" + x + ", " + y + ")";
    }

    public boolean equals(Object other){
        if(other instanceof Vector2D){
            Vector2D otherVector = (Vector2D) other;
            return otherVector.x == this.x && otherVector.y == this.y;
        }

        return false;
    }
}

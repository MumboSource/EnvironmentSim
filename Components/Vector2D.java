    package Components;

public class Vector2D {
    public int x;
    public int y;

    public static final Vector2D ZERO = new Vector2D(0, 0);
    
    public Vector2D(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Vector2D(double x, double y) {
        this.x = (int) x;
        this.y = (int) y;
    }

    public Vector2D add(Vector2D other){
        return new Vector2D(this.x + other.x, this.y + other.y);
    }

    public Vector2D subtract(Vector2D other){
        return new Vector2D(this.x - other.x, this.y - other.y);
    }

    public Vector2D mul(Vector2D other) {
        return new Vector2D(this.x * other.x, this.y * other.y);
    }
    public Vector2D mul(double scale) {
        return new Vector2D(this.x * scale, this.y * scale);
    }

    public double magnitude() {
        return Math.sqrt(this.x*this.x + this.y*this.y);
    }

    public Vector2D normalized() {
        double fac = 1/this.magnitude();
        return new Vector2D(this.x*fac, this.y*fac);
    }

    public double distanceTo(Vector2D other){
        return this.subtract(other).magnitude();
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

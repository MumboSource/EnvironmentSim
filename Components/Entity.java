package Components;
import java.awt.Color;

public class Entity {
    public Vector2D position;

    public int hunger;
    public int thirst;

    public int generation;

    public int speed;
    public Color color;

    public Entity() {
        position = new Vector2D(0, 0);
        hunger = 100;
        thirst = 100;
        
        // Not sure what this does, can we communicate through comments?
        generation = 1;
        speed = 10;
        color = new Color(0, 0, 0);
    } 

    public void display(Draw canvas) {
        // NOT WORKING IDK WHY
        canvas.setPenColor(color);
        canvas.circle(position.x, position.y, 0.05);
    }
}

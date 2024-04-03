package Components;
import java.awt.Color;

public class Entity {
    public Vector2D position;
    public float wanderDir;
    public float wanderSpeed;

    public int hunger;
    public int thirst;

    public int generation;

    public int speed;
    public Color color;

    public int predatorScore;

    public Entity(){
        position = new Vector2D(0, 0);
        hunger = 100;
        thirst = 100;
        generation = 1;
        speed = 1;
        color = new Color(0, 0, 0);
    }

    public Entity(int generation, Entity parent1, Entity parent2) {
        position = new Vector2D(0, 0);
        hunger = 100;
        thirst = 100;
        
        generation = 1;
        speed = 1;
        color = new Color(0, 0, 0);
    } 

    public void meetOther(Entity other) {
        if (other.predatorScore > predatorScore + (predatorScore / 4)){
            // Get eaten
        } else if (other.thirst < 50 && other.hunger < 50 && (Math.random() < 0.3)) {
            // Breed (freaky style)
        } else {
            // Fight
        }
    }

    public void display(Draw canvas) {
        canvas.setPenColor(color);
        canvas.filledCircle(position.x / 1000.0 + 0.5, position.y / 1000.0 + 0.5, 0.01);
    }
}

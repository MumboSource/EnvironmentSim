package Components;
import java.awt.Color;

public class Entity {
    public Vector2D position;
    public double wanderDir;
    public double wanderSpeed;

    public boolean toKill;

    public double hunger;
    public double hungerIncrease;

    public double thirst;
    public double thirstIncrease;

    public double matingScore;
    public double matingScoreIncrease;

    public int generation;

    public int speed;
    public Color color;

    public double predatorScore;

    public Entity(){
        position = new Vector2D(0, 0);
        hunger = 0;
        hungerIncrease = 0;
        thirst = 0;
        thirstIncrease = 0;
        matingScore = 0;
        matingScoreIncrease = 0.5;
        generation = 1;
        speed = 1;
        toKill = false;
        predatorScore = 0;
        color = new Color(0, 0, 0);
        wanderDir = Math.random() * 2;
    }

    public Entity(Entity parent1, Entity parent2) {
        position = new Vector2D(parent1.position.x, parent1.position.y);
        hunger = 0;
        hungerIncrease = 0.5;
        thirst = 0;
        thirstIncrease = 0.5;
        matingScore = 0;
        matingScoreIncrease = 0.5;
        toKill = false;
        wanderDir = Math.random() * 2;
        
        generation = parent1.generation + 1;

        predatorScore = (parent1.predatorScore+parent2.predatorScore)/2 + (Math.random()-0.5);
        thirstIncrease = (parent1.thirstIncrease+parent2.thirstIncrease)/2 + (Math.random()-0.5);
        hungerIncrease = (parent1.hungerIncrease+parent2.hungerIncrease)/2 + (Math.random()-0.5);
        matingScoreIncrease = (parent1.matingScoreIncrease+parent2.matingScoreIncrease)/2 + (Math.random()-0.5);
        speed = (int) ((parent1.speed+parent2.speed)/2.0 + (Math.random()-0.5)*10);

        // Mix parent colors?
        color = new Color(0,0,0);
    }

    public void display(Draw canvas) {
        canvas.setPenColor(color);
        canvas.filledCircle(position.x / 1000.0 + 0.5, position.y / 1000.0 + 0.5, 0.01);
    }
}

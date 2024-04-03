package Components;
import java.awt.Color;

public class Entity {
    public Vector2D position;
    public double wanderDir;
    public double wanderSpeed;

    public double hunger;
    public double hungerIncrease;

    public double thirst;
    public double thirstIncrease;

    public double freakyness;
    public double freakynessIncrease;

    public int generation;

    public int speed;
    public Color color;

    public int predatorScore;

    public Entity(){
        position = new Vector2D(0, 0);
        hunger = 0;
        hungerIncrease = 0.5;
        thirst = 0;
        thirstIncrease = 0.5;
        freakyness = 0;
        freakynessIncrease = 0.5;
        generation = 1;
        speed = 1;
        color = new Color(0, 0, 0);
    }

    public Entity(Entity parent1, Entity parent2) {
        position = new Vector2D(0, 0);
        hunger = 0;
        hungerIncrease = 0.5;
        thirst = 0;
        thirstIncrease = 0.5;
        freakyness = 0;
        freakynessIncrease = 0.5;
        
        generation = parent1.generation + 1;
        speed = (parent1.speed + parent2.speed) / 2;


        if(Math.random() < 0.5){
            predatorScore = parent1.predatorScore;
        } else {
            predatorScore = parent2.predatorScore;
        }

        if(Math.random() > .2) { // Entity will mutate
            predatorScore += (int) (Math.random() * 10) - 5;

            // Maybe mutate speed only if its predatorScore is lower than the average?

            if(Math.random() < 0.3) { // Mutate speed

                if(Math.random() < 0.5) { // Increase speed
                    speed += (int) (Math.random() * 2) + 1;
                } else {// Decrease speed 
                    speed -= (int) (Math.random() * 2) - 1;
                }
            }

            if(Math.random() < 0.4) { // Mutate thirst capacity
                if(Math.random() < 0.5) { // Thirstier
                    thirstIncrease += Math.random();
                } else {// Decrease thirst 
                    thirst -= Math.random();
                }
            }

            if(Math.random() < 0.4) { // Mutate hunger capacity
                if(Math.random() < 0.5) { // Hungrier
                    hungerIncrease += Math.random();
                } else {// Decrease hunger 
                    hunger -= Math.random();
                }
            }


        }

        // Mix parent colors?
        color = new Color(0, 0, 0);
    }

    public void display(Draw canvas) {
        canvas.setPenColor(color);
        canvas.filledCircle(position.x / 1000.0 + 0.5, position.y / 1000.0 + 0.5, 0.01);
    }
}

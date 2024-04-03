package Components;

import java.util.ArrayList;

public class Simulator implements DrawListener {
    private Draw canvas;

    private ArrayList<Entity> entities = new ArrayList<Entity>();

    public Simulator(Draw canvas) {
        this.canvas = canvas;
    }

    public void AddEntity(Entity entity) {
        entities.add(entity);
    }

    @Override
    public void update() {
        canvas.clear();
        for (Entity entity : entities) {

            if (entity.thirst > entity.hunger && entity.thirst > 50) {

                // Move to water
                entity.position.x += entity.speed;
            } else if(entity.hunger > entity.thirst && entity.hunger > 50) {

                // Move to nearest entity with predatorScore thats less by 25%
                Entity nearestPrey = null;
                int nearestPreyDistance = 0;

                for(Entity other : entities) {
                    if(other != entity && other.predatorScore < entity.predatorScore - (entity.predatorScore / 4)) {
                        if(nearestPrey == null || entity.position.magnitude(other.position) < nearestPreyDistance) {
                            nearestPrey = other;
                            nearestPreyDistance = entity.position.magnitude(other.position);
                        }
                    }
                }

                if (nearestPrey != null) {
                    // Move to nearest prey
                }
            }else {
                // Wander
                // Change direction and speed by a small random ammount

                entity.wanderDir += (Math.random() - 0.5) * 0.5;
                entity.wanderSpeed += (Math.random() - 0.5) * 0.75;

                // made it so if they hit the wall change wander dir by 90 deg

                entity.position.x += Math.cos(entity.wanderDir) * entity.wanderSpeed;
                entity.position.y += Math.sin(entity.wanderDir) * entity.wanderSpeed;
            }
            
            entity.thirst += 0.5;
            entity.hunger += 0.5;

            entity.display(canvas);
        }
        canvas.show();
    }
}

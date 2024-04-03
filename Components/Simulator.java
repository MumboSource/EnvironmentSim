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
        for (Entity entity : entities) {

            if(entity.thirst > entity.hunger && entity.thirst > 50) {

                // Move to water
                entity.position.x += entity.speed;
            }else if(entity.hunger > entity.thirst && entity.hunger > 50) {

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

                double rand = Math.random();

                if(rand < .5){
                    entity.position.x += entity.speed;
                }else {
                    entity.position.y += entity.speed;
                }
            }
            entity.display(canvas);
        }
        canvas.show();
    }
}

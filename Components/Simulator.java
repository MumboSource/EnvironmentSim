package Components;

import java.util.ArrayList;

public class Simulator implements DrawListener {
    private Draw canvas;

    private ArrayList<Entity> entities = new ArrayList<Entity>();
    private ArrayList<Vector2D> waterSources = new ArrayList<Vector2D>();

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

            Entity closestEntity = null;
            double closestDistance = 0;
            for(Entity other : entities) {
                if(other != entity) {
                    double distance = entity.position.magnitude(other.position);
                    if(closestEntity == null || distance < closestDistance) {
                        closestEntity = other;
                        closestDistance = distance;
                    }
                }
            }
        if (closestDistance < 10){
            if (closestEntity.predatorScore > entity.predatorScore + (entity.predatorScore / 4)){
                entities.remove(entity);
                closestEntity.hunger = 100;
            } else if (closestEntity.thirst < 50 && closestEntity.hunger < 50 && (Math.random() < 0.3)) {
               entities.add(new Entity(closestEntity, entity));
            } else {
                
            }
        }else if (entity.thirst > entity.hunger && entity.thirst > 50) {

                // Move to water

                Vector2D nearestWater = null;
                int nearestWaterDistance = 0;

                for(Vector2D waterSource : waterSources) {
                    if(nearestWater == null || entity.position.magnitude(waterSource) < nearestWaterDistance) {
                        nearestWater = waterSource;
                        nearestWaterDistance = entity.position.magnitude(waterSource);
                    }
                }

                if(nearestWater == null){
                    continue;
                }

                if(entity.position.x < nearestWater.x) {
                    entity.position.x += entity.speed;
                } else if(entity.position.x > nearestWater.x) {
                    entity.position.x -= entity.speed;
                }

                if(entity.position.y < nearestWater.y) {
                    entity.position.y += entity.speed;
                } else if(entity.position.y > nearestWater.y) {
                    entity.position.y -= entity.speed;
                }

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
            } else {
                // Meet
                for (Entity other : entities) {
                    if (entity == other) continue;

                    if (entity.position.magnitude(other.position) < 20) {
                        entity.meetOther(other);
                    }
                }

                // Wander
                // Change direction and speed by a small random ammount

                entity.wanderDir += (Math.random() - 0.5) * 0.2;
                entity.wanderSpeed += (Math.random() - 0.5) * 0.75;

                if (entity.position.x > 500 || entity.position.x < -500 || entity.position.y > 500 || entity.position.y < -500) entity.wanderSpeed = -entity.wanderSpeed * 0.5;

                entity.position.x += Math.cos(entity.wanderDir) * entity.wanderSpeed;
                entity.position.y += Math.sin(entity.wanderDir) * entity.wanderSpeed;
            }
            
            entity.thirst += 0.5;
            entity.hunger += 0.5;

            entity.display(canvas);
        }

        for(Vector2D waterSource : waterSources) {
            canvas.setPenColor(0, 0, 255);
            canvas.filledCircle(waterSource.x / 1000.0 + 0.5, waterSource.y / 1000.0 + 0.5, 0.01);
        }
        canvas.show();
    }
}

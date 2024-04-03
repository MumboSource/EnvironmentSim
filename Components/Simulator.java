package Components;

import java.util.ArrayList;

public class Simulator implements DrawListener {
    private Draw canvas;

    private ArrayList<Entity> entities = new ArrayList<Entity>();
    private ArrayList<Vector2D> waterSources = new ArrayList<Vector2D>();
    private ArrayList<Entity[]> matingPairs = new ArrayList<Entity[]>();


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
            double closestDistance = Integer.MAX_VALUE;
            for(Entity other : entities) {
                if (entity == other || other.toKill) continue;

                double distance = entity.position.magnitude(other.position);
                if(closestEntity == null || distance < closestDistance) {
                    closestEntity = other;
                    closestDistance = distance;
                }
            }
            
            // if theres a close enemy and they are not hungry or freaky enough they wont consider the other actions, use this to fix it
            boolean foundAction = false;

            if (closestDistance < 10) {
                if (closestEntity.predatorScore > entity.predatorScore + (entity.predatorScore / 4)) {
                    System.out.println("I was eaten");
                    entity.toKill = true;
                    closestEntity.hunger = 0;
                    foundAction = true;
                } else if (closestEntity.thirst < 50 && closestEntity.hunger < 50 && entity.freakyness > 50 && closestEntity.freakyness > 50 && (Math.random() < 0.3)) {
                    System.out.println("I got freaky");

                    matingPairs.add(new Entity[]{entity, closestEntity});
                    
                    entity.freakyness = 0;
                    closestEntity.freakyness = 0;
                    foundAction = true;
                }
            }
            if (!foundAction && entity.thirst > entity.hunger && entity.thirst > 50) {

                // Move to water

                Vector2D nearestWater = null;
                int nearestWaterDistance = 0;

                for(Vector2D waterSource : waterSources) {
                    if(nearestWater == null || entity.position.magnitude(waterSource) < nearestWaterDistance) {
                        nearestWater = waterSource;
                        nearestWaterDistance = entity.position.magnitude(waterSource);
                    }
                }

                if (nearestWater != null) {
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

                    if (nearestWaterDistance < 10) {
                        entity.thirst = 0;
                    }

                    foundAction = true;
                }

            }
            if (!foundAction && entity.hunger > entity.thirst && entity.hunger > 50) {

                // Move to nearest entity with predatorScore thats less by 25%
                Entity nearestPrey = null;
                int nearestPreyDistance = Integer.MAX_VALUE;

                for(Entity other : entities) {
                    if (entity == other || other.toKill) continue;

                    if (other.predatorScore < entity.predatorScore - (entity.predatorScore / 4)) {
                        if(nearestPrey == null || entity.position.magnitude(other.position) < nearestPreyDistance) {
                            nearestPrey = other;
                            nearestPreyDistance = entity.position.magnitude(other.position);
                        }
                    }
                }

                if (nearestPrey != null) {
                    // Move to nearest prey
                    foundAction = true;
                }
            }
            if (!foundAction && entity.freakyness > 60){                
                Entity nearestFreak = null;
                int nearestFreakDistance = Integer.MAX_VALUE;

                for(Entity other : entities) {
                    if (entity == other || other.toKill) continue;
                    if (other.freakyness > 55) {
                        if(nearestFreak == null || entity.position.magnitude(other.position) < nearestFreakDistance) {
                            nearestFreak = other;
                            nearestFreakDistance = entity.position.magnitude(other.position);
                        }
                    }
                }

                if (nearestFreak != null) {
                    if(entity.position.x < nearestFreak.position.x) {
                        entity.position.x += entity.speed;
                    } else if(entity.position.x > nearestFreak.position.x) {
                        entity.position.x -= entity.speed;
                    }

                    if(entity.position.y < nearestFreak.position.y) {
                        entity.position.y += entity.speed;
                    } else if(entity.position.y > nearestFreak.position.y) {
                        entity.position.y -= entity.speed;
                    }

                    foundAction = true;
                }
            }
            if (!foundAction) {
                // wander
                entity.wanderDir += (Math.random() - 0.5) * 0.2;
                entity.wanderSpeed += (Math.random() - 0.5) * 0.75;

                if (entity.position.x > 500 || entity.position.x < -500 || entity.position.y > 500 || entity.position.y < -500) entity.wanderSpeed = -entity.wanderSpeed * 0.5;

                entity.position.x += Math.cos(entity.wanderDir) * entity.wanderSpeed;
                entity.position.y += Math.sin(entity.wanderDir) * entity.wanderSpeed;
            }
            
            entity.thirst += entity.thirstIncrease;
            entity.hunger += entity.hungerIncrease;
            entity.freakyness += entity.freakynessIncrease;

            if(entity.thirst == 100 || entity.hunger == 100) {
                System.out.println("I starved/dehydrated");
                entity.toKill = true;
                continue;
            }

            if (!entity.toKill) entity.display(canvas);
        }

        for(Entity[] pair : matingPairs) {
            Entity parent1 = pair[0];
            Entity parent2 = pair[1];

            Entity child = new Entity(parent1, parent2);
            entities.add(child);
        }
        matingPairs.clear();

        for(Vector2D waterSource : waterSources) {
            canvas.setPenColor(0, 0, 255);
            canvas.filledCircle(waterSource.x / 1000.0 + 0.5, waterSource.y / 1000.0 + 0.5, 0.01);
        }
        entities.removeIf(b -> b.toKill); // gas
        canvas.show();
    }
}

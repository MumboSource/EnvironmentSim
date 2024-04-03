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
            entity.display(canvas);
        }
        canvas.show();

    }
}

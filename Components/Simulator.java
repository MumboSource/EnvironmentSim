package Components;

import java.util.ArrayList;

public class Simulator implements DrawListener {
    private Draw canvas;

    private ArrayList<Entity> entities = new ArrayList<Entity>();

    public Simulator(Draw canvas) {
        this.canvas = canvas;
    }

    @Override
    public void update() {

    }
}

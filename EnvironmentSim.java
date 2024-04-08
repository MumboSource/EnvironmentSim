import java.awt.Color;

import Components.Draw;
import Components.Entity;
import Components.Simulator;
import Components.Vector2D;

public class EnvironmentSim {
    public static void main(String[] args) {
        Draw canvas = new Draw();
        Simulator listener = new Simulator(canvas);

        canvas.setTitle("EnvironmentSim");
        canvas.enableDoubleBuffering();
        canvas.addListener(listener);
        canvas.enableTimer(60);

        for(int i = 0; i < 15; i++) {
            Entity prey = new Entity();

            prey.position.x = (int) ((Math.random()-0.5) * 1000);
            prey.position.y = (int) ((Math.random()-0.5) * 1000);

            prey.matingScoreIncrease = .9;
            prey.predatorScore = 0.1;
            prey.hungerIncrease = 0.5;

            prey.color = new Color(15, 255, 15);

            listener.AddEntity(prey);
        }

        for(int i = 0; i < 5; i++) {
            Entity predator = new Entity();

            predator.position.x = (int) ((Math.random()-0.5) * 1000);
            predator.position.y = (int) ((Math.random()-0.5) * 1000);

            predator.predatorScore = 0.9;
            predator.matingScoreIncrease = 0.1;
            predator.hungerIncrease = 0.9;

            predator.color = new Color(255, 15, 15);

            listener.AddEntity(predator);
        }

    }
}
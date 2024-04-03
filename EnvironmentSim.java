import Components.Draw;
import Components.Entity;
import Components.Simulator;

public class EnvironmentSim {
    public static void main(String[] args) {
        Draw canvas = new Draw();
        Simulator listener = new Simulator(canvas);

        canvas.setTitle("EnvironmentSim");
        canvas.enableDoubleBuffering();
        canvas.addListener(listener);
        canvas.enableTimer(60);

        Entity testPrey1 = new Entity();
        Entity testPrey2 = new Entity();

        
        Entity testPreditor1 = new Entity();
        Entity testPreditor2 = new Entity();

        testPrey1.freakynessIncrease = 3;
        testPrey2.freakynessIncrease = 3;
        testPrey1.predatorScore = 0.1;
        testPrey2.predatorScore = 0.1;

        testPreditor1.predatorScore = 3;
        testPreditor2.predatorScore = 3;
        testPreditor1.freakynessIncrease = 0.1;
        testPreditor2.freakynessIncrease = 0.1;

        listener.AddEntity(testPrey1);
        listener.AddEntity(testPrey2);
        listener.AddEntity(testPreditor1);
        listener.AddEntity(testPreditor2);
    }
}
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

        Entity testGuy = new Entity();
        listener.AddEntity(testGuy);
    }
}
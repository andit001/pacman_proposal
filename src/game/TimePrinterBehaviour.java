package game;

import framework.ComponentBehaviour;
import framework.Time;
import framework.Transform;

// Example component using Time to execute timed events.
public class TimePrinterBehaviour extends ComponentBehaviour {
    public double time;
    public int seconds;

    private Transform transform;

    boolean changed;
    @Override
    public void Start() {
        time = 0.0;

        // Example to show how to access other components.
        transform = (Transform) GetComponent(Transform.class.getSimpleName());
        // Equivalent:
//        transform = (Transform) GetComponent("Transform");
        if (transform == null) {
            System.out.println("Transform not found.");
            return;
        }
        System.out.println("Transform found!");
    }

    @Override
    public void Update() {
        time += Time.deltaTime;
        if (time > 1.0) {
            seconds++;
            time = 0.0;
            System.out.println(
                    "Seconds alive: " + seconds + " position: ("
                            + transform.position.x + "," + transform.position.y + ")"
            );
        }
    }
}

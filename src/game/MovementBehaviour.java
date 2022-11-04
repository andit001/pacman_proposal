package game;

import framework.ComponentBehaviour;
import framework.Input;
import framework.Time;
import framework.Vector2;

public class MovementBehaviour extends ComponentBehaviour {
    double speed = 100.0;

    @Override
    public void Update() {
        Vector2 direction = new Vector2();

        if (Input.isActionRight()) {
            direction.x = 1;
        }

        if (Input.isActionLeft()) {
            direction.x = -1;
        }

        if (Input.isActionUp()) {
            direction.y = -1;
        }

        if (Input.isActionDown()) {
            direction.y = 1;
        }

        transform.translate(
                direction.normalized().multiply(speed * Time.deltaTime)
        );
    }
}

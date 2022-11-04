package framework;
public class Transform extends ComponentBehaviour {
    public Vector2 position = new Vector2();
    public Vector2 scale = new Vector2();
    public double rotation;

    public void translate(Vector2 translation) {
        position.x += translation.x;
        position.y += translation.y;
    }
}
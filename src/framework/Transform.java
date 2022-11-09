package framework;
public class Transform extends ComponentBehaviour {
    public Vector2 position = new Vector2();
    public Vector2 scale = new Vector2();
    public double rotation;

    public Transform() { }
    public Transform(Transform other) {
        position = new Vector2(other.position);
        scale = new Vector2(other.scale);
        rotation = other.rotation;
    }

    public void translate(Vector2 translation) {
        position.x += translation.x;
        position.y += translation.y;
    }
}
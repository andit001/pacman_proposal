package framework;
public abstract class ComponentBehaviour {
    public final String GetName() {
        return this.getClass().getSimpleName();
    }

    public GameObject gameObject;
    // For convenience the transform is referenced by each component.
    public Transform transform;

    public ComponentBehaviour GetComponent(String typeName) {
        return gameObject.GetComponent(typeName);
    }

    public void Awake() {};
    public void Start() {};
    public void Update() {};
    public void LateUpdate() {};

    public void Destroy() {};

    public void OnCollision(GameObject other) {}
}

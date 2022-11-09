package framework;
import java.util.ArrayList;
import java.util.List;

public class GameObject {

    List<ComponentBehaviour> pendingComponents = new ArrayList<>();
    List<ComponentBehaviour> awakenedComponents = new ArrayList<>();
    List<ComponentBehaviour> components = new ArrayList<>();

    public Transform transform;

    public GameObject() {
        // Each game object has a transform.
        transform = new Transform();
        AddComponent(transform);
    }

    public void AddComponent(ComponentBehaviour component) {
        component.gameObject = this;
        component.transform = transform;
        pendingComponents.add(component);
    }

    public void RemoveComponent(ComponentBehaviour componentToRemove) {
        componentToRemove.gameObject = null;
        components.remove(componentToRemove);
        componentToRemove.Destroy();
    }

    public final ComponentBehaviour GetComponent(String typeName) {
        List<ComponentBehaviour> allComponents = new ArrayList<>();
        allComponents.addAll(pendingComponents);
        allComponents.addAll(awakenedComponents);
        allComponents.addAll(components);
        for (ComponentBehaviour component: allComponents) {
            if (component.GetName().equals(typeName)) {
                return component;
            }
        }

        return null;
    }

    protected void Awake() {
        Util.runMethodOnCollection(
                pendingComponents,
                awakenedComponents,
                component -> component.Awake()
        );
    }

    protected void Start() {
        Util.runMethodOnCollection(
                awakenedComponents,
                components,
                component -> component.Start()
        );
    }

    protected void Update() {
        Awake();
        Start();
        for (ComponentBehaviour component: components) {
            component.Update();
        }
    }

    protected void LateUpdate() {
        for (ComponentBehaviour component: components) {
            component.LateUpdate();
        }
    }

    protected void Destroy() {
        for (ComponentBehaviour component: components) {
            component.Destroy();
        }
    }
}

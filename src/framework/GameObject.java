package framework;
import java.util.ArrayList;
import java.util.List;

public class GameObject extends ComponentBehaviour {

    List<ComponentBehaviour> pendingComponents = new ArrayList<>();
    List<ComponentBehaviour> awakenedComponents = new ArrayList<>();
    List<ComponentBehaviour> components = new ArrayList();

    public Transform transform;

    public GameObject() {
        // Each game object has a transform.
        transform = new Transform();
        AddComponent(transform);
    }

    public void AddComponent(ComponentBehaviour component) {
        component.gameObject = this;
        component.transform = transform;
//        component.Awake();
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

    @Override
    public void Awake() {
        if (pendingComponents.size() == 0) {
            return;
        }

        List<ComponentBehaviour> batch = new ArrayList<>(pendingComponents);

        for (ComponentBehaviour component: batch) {
            component.Awake();
        }

        pendingComponents.removeAll(batch);
        awakenedComponents.addAll(batch);

        Awake();
    }

    @Override
    public void Start() {
        if (awakenedComponents.size() == 0) {
            return;
        }

        List<ComponentBehaviour> batch = new ArrayList<>(awakenedComponents);

        for (ComponentBehaviour component: batch) {
            component.Start();
        }

        awakenedComponents.removeAll(batch);
        components.addAll(batch);

        Start();
    }

    @Override
    public void Update() {
        Awake();
        Start();
        for (ComponentBehaviour component: components) {
            component.Update();
        }
    }

    @Override
    public void LateUpdate() {
        for (ComponentBehaviour component: components) {
            component.LateUpdate();
        }
    }

    @Override
    public void Destroy() {
        for (ComponentBehaviour component: components) {
            component.Destroy();
        }
    }
}

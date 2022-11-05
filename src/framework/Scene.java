package framework;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Scene extends ComponentBehaviour {
    private String sceneName;

    // It's very common for game objects to add other game objects while their ComponentBehaviour methods are being
    // executed. To handle this situation all game objects are added as pendingObjects first and will be processed on
    // the next Update() period. This way a list which is iterated over is not modified while doing so.
    List<GameObject> pendingObjects = new ArrayList<>();
    List<GameObject> awakenedObjects = new ArrayList<>();
    List<GameObject> gameObjects = new ArrayList<>();

    public Scene(String sceneName) {
        this.sceneName = sceneName;
    }

    public void AddGameObject(GameObject gameObject) {
        pendingObjects.add(gameObject);
    }

    public void RemoveGameObject(GameObject gameObject) {
        gameObjects.remove(gameObject);
        gameObject.Destroy();
    }

    @Override
    public final void Awake() {
        Util.runMethodOnCollection(
                pendingObjects,
                awakenedObjects,
                component -> component.Awake()
        );
    }
    @Override
    public final void Start() {
        Util.runMethodOnCollection(
                awakenedObjects,
                gameObjects,
                component -> component.Start()
        );
    }

    @Override
    public final void Update() {
        Awake();
        Start();

        for (GameObject gameObject: gameObjects) {
            gameObject.Update();
        }

        for (GameObject gameObject: gameObjects) {
            gameObject.LateUpdate();
        }
    }

    @Override
    public final void Destroy() {
        for (GameObject gameObject: gameObjects) {
            gameObject.Destroy();
        }
    }

    public final ComponentBehaviour FindGameObjectOfType(String typeName) {
        List<GameObject> allObjects = new ArrayList<>();
        allObjects.addAll(gameObjects);
        allObjects.addAll(pendingObjects);
        allObjects.addAll(awakenedObjects);
        for (GameObject gameObject: allObjects) {
            ComponentBehaviour componentBehaviour = gameObject.GetComponent(typeName);
            if (componentBehaviour != null) {
                return componentBehaviour;
            }
        }

        return null;
    }

    public final ArrayList<ComponentBehaviour> FindGameObjectsOfType(String typeName) {
        ArrayList<ComponentBehaviour> result = new ArrayList<>();
        List<GameObject> allObjects = new ArrayList<>();
        allObjects.addAll(gameObjects);
        allObjects.addAll(pendingObjects);
        allObjects.addAll(awakenedObjects);
        for (GameObject gameObject: allObjects) {
            ComponentBehaviour componentBehaviour = gameObject.GetComponent(typeName);
            if (componentBehaviour != null) {
                result.add(componentBehaviour);
            }
        }

        return result;
    }
}

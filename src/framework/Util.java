package framework;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Util {
    private Util() {}

    /**
     * Runs the method @method on each element in the sourceCollection and adds it to the destinationCollection.
     * If sourceCollection gets new elements while executing the method on each element, it is processed again until
     * sourceCollection is empty and everything's processed. Does nothing if the sourceCollection is empty.
     * @param sourceCollection
     * @param destinationCollection
     * @param method
     * @param <T>
     */
    public static <T extends ComponentBehaviour> void runMethodOnCollection(
            List<T> sourceCollection,
            List<T> destinationCollection,
            Consumer<T> method
    ) {
        if (sourceCollection.size() == 0) {
            return;
        }

        List<T> batch = new ArrayList<>(sourceCollection);

        for (T componentBehaviour: batch) {
            method.accept(componentBehaviour);
        }

        sourceCollection.removeAll(batch);
        destinationCollection.addAll(batch);

        runMethodOnCollection(sourceCollection, destinationCollection, method);
    }
}

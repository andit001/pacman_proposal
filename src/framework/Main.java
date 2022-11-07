package framework;

import javax.swing.*;
import java.awt.*;

/**
 * Main static methods to get the framework up and running.
 */
public class Main {

    // Can't be instantiated.
    private Main() {}

    // Used to exit the main loop.
    private static boolean isRunning = true;

    // New scene should be loaded.
    private static boolean shouldLoadNewScene = false;

    private static Scene currentScene;
    protected static SwingScene swingScene;

    private static JFrame mainFrame;

    /**
     * Sets the size of the frame of the window (without the border).
     * @param width with of the frame in pixels.
     * @param height height of the frame in pixels.
     */
    public static void setFrameSize(int width, int height) {
        swingScene.setPreferredSize(new Dimension(width, height));
        mainFrame.pack();
    }

    /**
     * Loads a Scene and makes it the active scene.
     * @see Scene
     * @param scene
     */
    public static void loadScene(Scene scene) {
        if (currentScene != null) {
            currentScene.Destroy();
        }

        currentScene = scene;
        shouldLoadNewScene = true;
    }

    /**
     * Gets the current scene.
     * @see Scene
     * @return The current Scene.
     */
    public static Scene getScene() {
        return currentScene;
    }

    /**
     * Can be used to stop the main loop.
     */
    public static void Exit() {
        isRunning = false;
    }

    /**
     * Should be called after a Scene is set. Starts the main loop of the framework.
     * @see Scene
     * @param windowName Name used as the window's title.
     */
    public static void Run(String windowName) {
        CreateAndShowUI(windowName);
        Time.Start();
        Input.initialize(swingScene);

        while(isRunning) {
            // If there's a new scene to load it is initialized first.
            if (shouldLoadNewScene) {
                currentScene.Awake();
                currentScene.Start();
                shouldLoadNewScene = false;
            }

            Time.Update();
            currentScene.Update();
            swingScene.Update();
        }

        currentScene.Destroy();
    }

    private static void CreateAndShowUI(String windowName) {
        mainFrame = new JFrame(windowName);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        swingScene = new SwingScene();

        mainFrame.add(swingScene);
        mainFrame.setResizable(false);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}
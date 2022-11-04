package framework;

import javax.swing.*;
import java.awt.*;

public class Main {

    private static boolean isRunning = true;
    private static Scene currentScene;
    protected static SwingScene swingScene;

    private static JFrame mainFrame;

    public static void setFrameSize(int width, int height) {
        swingScene.setPreferredSize(new Dimension(width, height));
        mainFrame.pack();
    }
    public static void loadScene(Scene scene) {
        if (currentScene != null) {
            currentScene.Destroy();
        }

        currentScene = scene;
    }

    public static Scene getScene() {
        return currentScene;
    }

    public static void Exit() {
        isRunning = false;
    }

    public static void Run(String windowName) {
        CreateAndShowUI(windowName);
        Time.Start();
        currentScene.Awake();
        currentScene.Start();
        Input.initialize(swingScene);

        while(isRunning) {
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
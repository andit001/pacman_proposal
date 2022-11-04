package framework;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class Input {
    private static SwingScene swingScene;

    private static boolean actionLeft;
    private static boolean actionRight;
    private static boolean actionUp;
    private static boolean actionDown;
    private static boolean actionExit;

    protected static void initialize(SwingScene swingScene) {
        assert(swingScene != null);
        Input.swingScene = swingScene;
        setupKeyBindings();
    }


    // Key bindings are hardcoded. Should suffice for the project as we don't need any customization.
    private static void setupKeyBindings() {
        swingScene.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false), "leftActionPressed");
        swingScene.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "leftActionPressed");
        swingScene.getActionMap().put("leftActionPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionLeft = true;
            }
        });
        swingScene.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true), "leftActionReleased");
        swingScene.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), "leftActionReleased");
        swingScene.getActionMap().put("leftActionReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionLeft = false;
            }
        });
        swingScene.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), "rightActionPressed");
        swingScene.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "rightActionPressed");
        swingScene.getActionMap().put("rightActionPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionRight = true;
            }
        });
        swingScene.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), "rightActionReleased");
        swingScene.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), "rightActionReleased");
        swingScene.getActionMap().put("rightActionReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionRight = false;
            }
        });
        swingScene.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false), "upActionPressed");
        swingScene.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "upActionPressed");
        swingScene.getActionMap().put("upActionPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionUp = true;
            }
        });
        swingScene.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true), "upActionReleased");
        swingScene.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true), "upActionReleased");
        swingScene.getActionMap().put("upActionReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionUp = false;
            }
        });
        swingScene.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false), "downActionPressed");
        swingScene.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), "downActionPressed");
        swingScene.getActionMap().put("downActionPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionDown = true;
            }
        });
        swingScene.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true), "downActionReleased");
        swingScene.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true), "downActionReleased");
        swingScene.getActionMap().put("downActionReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionDown = false;
            }
        });
        swingScene.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false), "exitActionPressed");
        swingScene.getActionMap().put("exitActionPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionExit = true;
            }
        });
        swingScene.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, true), "exitActionReleased");
        swingScene.getActionMap().put("exitActionReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionExit = false;
            }
        });
    }

    public static boolean isActionLeft() {
        return actionLeft;
    }

    public static boolean isActionRight() {
        return actionRight;
    }

    public static boolean isActionUp() {
        return actionUp;
    }

    public static boolean isActionDown() {
        return actionDown;
    }
}

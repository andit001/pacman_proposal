package framework;

public class Time {
    // Time the game was started at in seconds.
    public static double startTime;

    // Time since last frame in seconds.
    public static double deltaTime;
    public static double unscaledDeltaTime;

    // Multiplier for deltaTime. 1.0 means normal clock speed.
    public static double timeScale;
    private static double lastFrameTime;

    private static void DeltaTime() {
        double currentTime = System.currentTimeMillis() / 1000.0;
        unscaledDeltaTime = (currentTime - lastFrameTime);
        deltaTime = unscaledDeltaTime * timeScale;
        //System.out.println("currentTime: " + currentTime + " deltaTime: " + deltaTime + " lastFrameTime: " + lastFrameTime);

        lastFrameTime = currentTime;
    }

    public static void Start() {
        lastFrameTime = startTime = System.currentTimeMillis() / 1000.0;
        deltaTime = 0.0;
        timeScale = 1.0;
    }

    public static void Update() {
        DeltaTime();
    }
}

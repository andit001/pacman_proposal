package framework;

public class SpriteAnimator extends ComponentBehaviour {
    public enum AnimationType {
        Forward,
        Backward,
        BackAndForth
    }

    public AnimationType animationType;

    /**
     * Time [s] between animation steps.
     */
    public double animationSpeed = 0.125;

    private double timeSinceLastAnimationStep;

    private SpriteRenderer spriteRenderer;

    // Used for BackAndForth animation type.
    private boolean isInForwardAnimation;

    @Override
    public void Awake() {
        animationType = AnimationType.Forward;
        spriteRenderer = (SpriteRenderer) GetComponent(SpriteRenderer.class.getSimpleName());
        timeSinceLastAnimationStep = 0.0;
        isInForwardAnimation = true;
    }

    @Override
    public void Update() {
        Animate();
    }

    private void Animate() {
        timeSinceLastAnimationStep += Time.deltaTime;

        if (timeSinceLastAnimationStep > animationSpeed) {
            switch(animationType) {
                case Forward -> ForwardAnimation();
                case Backward -> BackwardAnimation();
                case BackAndForth -> BackAndForthAnimation();
            }

            timeSinceLastAnimationStep = 0.0;
        }
    }

    private void ForwardAnimation() {
        int currentTile = spriteRenderer.getCurrentTile();
        if (currentTile < spriteRenderer.getNumberOfTiles() - 1) {
            ++currentTile;
        } else {
            currentTile = 0;
        }

        spriteRenderer.setCurrentTile(currentTile);
    }

    private void BackwardAnimation() {
        int currentTile = spriteRenderer.getCurrentTile();
        if (currentTile > 0) {
            --currentTile;
        } else {
            currentTile = spriteRenderer.getNumberOfTiles() - 1;
        }

        spriteRenderer.setCurrentTile(currentTile);
    }

    private void BackAndForthAnimation() {
        if (isInForwardAnimation) {
            ForwardAnimation();

            // If the current tile is set to the first one, the next round of the
            // forward animation would start.
            if (spriteRenderer.getCurrentTile() == 0) {
                spriteRenderer.setCurrentTile(spriteRenderer.getNumberOfTiles() - 1);
                isInForwardAnimation = false;
            }
        } else {
            BackwardAnimation();

            int currentTile = spriteRenderer.getCurrentTile();

            // Similarly, if the current tile is set to the last one, the next round of the
            // backward animation would start.
            if (currentTile == spriteRenderer.getNumberOfTiles() - 1) {
                spriteRenderer.setCurrentTile(0);
                isInForwardAnimation = true;
            }
        }
    }
}

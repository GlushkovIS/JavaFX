package looseweight.animations;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Shake {
    private final TranslateTransition translateTransition;

    public Shake(Node node) {
        translateTransition = new TranslateTransition(Duration.millis(70), node);
        translateTransition.setFromX(-10f);
        translateTransition.setByX(10f);
        translateTransition.setCycleCount(3);
        translateTransition.setAutoReverse(true);
        node.setStyle("-fx-border-color: red; -fx-border-radius: 5px;");
    }

    public Shake(ImageView imageView) {
        translateTransition = new TranslateTransition(Duration.millis(70), imageView);
        translateTransition.setFromX(-10f);
        translateTransition.setByX(10f);
        translateTransition.setCycleCount(3);
        translateTransition.setAutoReverse(true);
    }

    public void PlayAnim() {
        translateTransition.playFromStart();
    }
}

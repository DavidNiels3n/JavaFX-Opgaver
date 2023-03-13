package dk.easv.javafxopgaver;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Shear;
import javafx.stage.Stage;
import java.io.IOException;


public class HelloApplication extends Application {


    private static final int CANVAS_WIDTH = 1080;
    private static final int CANVAS_HEIGHT = 760;
    private static final int IMAGE_HEIGHT = 200;
    private static final int IMAGE_WIDTH = 200;
    private static  int spooderX = CANVAS_WIDTH / 3;
    private static  int spooderY = CANVAS_HEIGHT / 3;
    private final int MOVE_PIXELS = 10;
    private final int SLIDER_SIZE = CANVAS_WIDTH / 2;
    private final int ROTATION_DEGREES = 45;
    private static  int angle = 0;




    @Override
    public void start(Stage stage) throws IOException {

        AnchorPane anchorPane = new AnchorPane();
        Scene scene = new Scene(anchorPane, CANVAS_WIDTH, CANVAS_HEIGHT);
        Rotate rotateTransform = new Rotate();
        Scale scaleTransformation = new Scale();
        Slider slider = new Slider(-10, 10,0 );
        Slider sliderSkew = new Slider(-2, 2,0 );
        Shear shear = new Shear();
        stage.setTitle("SpooderMan");
        stage.setScene(scene);
        stage.show();

        //Slider
        slider.setLayoutX(CANVAS_WIDTH / 4);
        slider.setLayoutY(CANVAS_HEIGHT - 100);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        slider.setMajorTickUnit(10);
        slider.setMinorTickCount(1);
        slider.setBlockIncrement(1);
        slider.setMinWidth(SLIDER_SIZE);
        slider.setStyle("-fx-fill: red");

        slider.valueProperty().addListener((observableValue, number, t1) -> {
            double sliderVal = slider.getValue();
            slider.setValue(sliderVal);
            scaleTransformation.setY(sliderVal);
            scaleTransformation.setX(sliderVal);
        });

        sliderSkew.setLayoutX(CANVAS_WIDTH / 4);
        sliderSkew.setLayoutY(CANVAS_HEIGHT - 150);
        sliderSkew.setShowTickMarks(true);
        sliderSkew.setShowTickLabels(true);
        sliderSkew.setMajorTickUnit(1);
        sliderSkew.setMinorTickCount(0);
        sliderSkew.setBlockIncrement(0.1);
        sliderSkew.setMinWidth(SLIDER_SIZE);
        sliderSkew.setStyle("-fx-text-fill: red;");

        sliderSkew.valueProperty().addListener((observableValue, number, t1) -> {
            double sliderVal1 = sliderSkew.getValue();
            sliderSkew.setValue(sliderVal1);
            shear.setY(sliderVal1);
            shear.setX(sliderVal1);
        });

        shear.setPivotX(50);
        shear.setPivotY(50);

        //rotate
        rotateTransform.setAngle(angle);
        rotateTransform.setPivotX(IMAGE_WIDTH / 2);
        rotateTransform.setPivotY(IMAGE_HEIGHT / 2);

        //Scale
        scaleTransformation.setPivotX(IMAGE_WIDTH / 2);
        scaleTransformation.setPivotY(IMAGE_HEIGHT / 2);


        ImageView imageView1 = createImageView("D:\\SkoleOpgaver\\JavaFX-Opgaver\\src\\main\\java\\dk\\easv\\javafxopgaver\\pngwing.com.png",IMAGE_WIDTH,IMAGE_HEIGHT,spooderX,spooderY);
        imageView1.getTransforms().add(rotateTransform);
        imageView1.getTransforms().add(scaleTransformation);
        imageView1.getTransforms().add(shear);
        ImageView imageView2 = createImageView("D:\\SkoleOpgaver\\JavaFX-Opgaver\\src\\main\\java\\dk\\easv\\javafxopgaver\\—Pngtree—realistic clear blue sky_7274571.png",CANVAS_WIDTH,CANVAS_HEIGHT,0,0);
        ImageView imageView3 = createImageView("D:\\SkoleOpgaver\\JavaFX-Opgaver\\src\\main\\java\\dk\\easv\\javafxopgaver\\—Pngtree—city and sea_3726164.png",CANVAS_WIDTH,CANVAS_HEIGHT,0,0);

        Button leftButton = createButton("Rotate to the left", 150, CANVAS_HEIGHT - 200, 200, 50, actionEvent -> {
            rotateTransform.setAngle(angle -= ROTATION_DEGREES);
        });

        Button rightButton = createButton("Rotate to the right", CANVAS_WIDTH - 350, CANVAS_HEIGHT - 200, 200, 50, actionEvent -> {
            rotateTransform.setAngle(angle += ROTATION_DEGREES);
        });

        Button moveUP = createButton("Up", CANVAS_WIDTH / 2 -25, CANVAS_HEIGHT - 350, 50, 50, actionEvent -> {
            imageView1.setLayoutY(spooderY -= MOVE_PIXELS);
        });

        Button moveDown = createButton("Down", CANVAS_WIDTH / 2 -25, CANVAS_HEIGHT - 250, 50, 50, actionEvent -> {
            imageView1.setLayoutY(spooderY += MOVE_PIXELS);
        });

        Button moveRight = createButton("Right", CANVAS_WIDTH / 2 +25, CANVAS_HEIGHT - 300, 50, 50, actionEvent -> {
            imageView1.setLayoutX(spooderX += MOVE_PIXELS);
        });

        Button moveLeft = createButton("Left", CANVAS_WIDTH / 2 - 75, CANVAS_HEIGHT - 300, 50, 50, actionEvent -> {
            imageView1.setLayoutX(spooderX -= MOVE_PIXELS);
        });

        Label upOrDownScale = createLabel("Scale slider: ", 50, CANVAS_HEIGHT-100, 0,200,0,"arial",14);
        Label labelSkew = createLabel("Skew slider: ", 50,CANVAS_HEIGHT-150,200,0,0,"arial", 14);

        //Adding to AnchorPane
        anchorPane.getChildren().addAll(imageView2,imageView3,imageView1,leftButton,rightButton,slider,upOrDownScale,
                sliderSkew, labelSkew, moveRight, moveLeft, moveUP, moveDown);



    }
    private Button createButton(String text, double layoutX, double layoutY, double minWidth, double minHeight, EventHandler<ActionEvent> actionHandler) {
        Button button = new Button(text);
        button.setLayoutX(layoutX);
        button.setLayoutY(layoutY);
        button.setMinSize(minWidth, minHeight);
        button.setOnAction(actionHandler);
        return button;
    }

    public ImageView createImageView(String imagePath, double fitWidth, double fitHeight, double layoutX, double layoutY) {
        Image image = new Image(imagePath);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(fitWidth);
        imageView.setFitHeight(fitHeight);
        imageView.setLayoutX(layoutX);
        imageView.setLayoutY(layoutY);
        return imageView;
    }

    private Label createLabel( String labelText,double cordX, double cordY,int r, int b, int g, String fontType, int fontSize){
        Label label = new Label();
        label.setLayoutX(cordX);
        label.setLayoutY(cordY);
        label.setText(labelText);
        label.setTextFill(Color.rgb(r,g,b));
        label.setFont(new Font(fontType,fontSize));
        return label;
    }
    public static void main(String[] args) {
        launch();
    }
}

package io.gmutant;

import javafx.application.Application;
import javafx.stage.Stage;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Lighting;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.shape.Sphere;
import javafx.scene.shape.Ellipse;
import javafx.scene.paint.PhongMaterial;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.util.Duration;

import static java.lang.Math.random;
import java.util.concurrent.ThreadLocalRandom;

		public class BlueAlien extends Application{ 
		   
		    private Timeline timeline;
		    private AnimationTimer timer;
		    private Integer i = ThreadLocalRandom.current().nextInt(0, 5);
		    private EventHandler<ActionEvent> onFinished;

		    String alienLtrs = "<|>)|-/|o0o][|a<^~^>z";//TODO: add these
		    char[] i2 = alienLtrs.toCharArray();

		    private void init(Stage primaryStage) {
		        Group root = new Group();
		        primaryStage.setResizable(true);

		        Scene scene = new Scene(root, 570, 670);
		 //     scene.getStylesheets().add(getClass().getResource("io.gmutant.css").toExternalForm());
		        primaryStage.setScene(scene);    
		        scene.setFill(Color.OLIVEDRAB);

				final Ellipse alien = new Ellipse(0.0, 0.0, 13.82, 19.447);
				alien.setFill(Color.DODGERBLUE);
				alien.setEffect(new Lighting());	
				alien.opacityProperty().set(0.872f);
				
				alien.setEffect(new BoxBlur(4, 4, 5));

				Sphere alien1 = new Sphere(25.577f, 25);
				PhongMaterial og1Matter = new PhongMaterial();
				og1Matter.setDiffuseColor(Color.DODGERBLUE);
				og1Matter.setSpecularColor(Color.LIGHTSKYBLUE);
				alien1.setMaterial(og1Matter);

				alien1.setTranslateX(0.1382f);
				alien1.setTranslateY(-44.1382f);

				Sphere a2leftEye = new Sphere(5.142f);
				PhongMaterial a2eyeLMatter = new PhongMaterial();
				a2eyeLMatter.setDiffuseColor(Color.BLACK);
				a2eyeLMatter.setSpecularColor(Color.ORANGE);
				a2eyeLMatter.setSpecularPower(10);
				a2leftEye.setMaterial(a2eyeLMatter);

				a2leftEye.setTranslateX(8.72f);
				a2leftEye.setTranslateY(-54.8f);

				Sphere a2rightEye = new Sphere(5.774f);
				PhongMaterial a2eyeRMatter = new PhongMaterial();
				a2eyeRMatter.setDiffuseColor(Color.BLACK);
				a2eyeRMatter.setSpecularColor(Color.ORANGERED);
				a2eyeRMatter.setSpecularPower(10);
				a2rightEye.setMaterial(a2eyeRMatter);

				a2rightEye.setTranslateX(-8.72f);
				a2rightEye.setTranslateY(-54.16f);

		        final Text text = new Text(i.toString());
		        Font mono = Font.font("Monspaced", 5.25f);
		        text.setFont(mono);
		        text.setStroke(Color.DARKBLUE);

		        Reflection r = new Reflection();
		        r.setFraction(0.99f);
		        r.setTopOffset(0.1f);
		        text.setEffect(r);

		        final StackPane stack = new StackPane();
		        stack.getChildren().addAll(alien, text, alien1, a2leftEye, a2rightEye);
		        stack.setLayoutX(78.618);
		        stack.setLayoutY(250);

		        timeline = new Timeline();
		        timeline.setCycleCount(Timeline.INDEFINITE);
		        timeline.setAutoReverse(true);

		        timer = new AnimationTimer() {
		            @Override
		            public void handle(long l) {
		            text.setText(i.toBinaryString(i));  
		            i++;
		            }
		        };
		        KeyValue keyValueX = new KeyValue(stack.scaleXProperty(), 3);
		        KeyValue keyValueY = new KeyValue(stack.scaleYProperty(), 3);

		        Duration duration = Duration.seconds(5);
		        
		        onFinished = e -> { int x = (int)(Math.random() * 27);
		    		if(x < 11 ){
		    			stack.setTranslateX(random()*500-100);
		    		} else {
		    			stack.setTranslateY(random()*500-100);
		    		}
		         //reset counter
		    		i = 5;
		        };

		        KeyFrame keyFrame = new KeyFrame(duration, onFinished , keyValueX, keyValueY);
		 
		        timeline.getKeyFrames().add(keyFrame);
		        root.getChildren().add(stack);
		    	}
			    public void play() {
			        timeline.play();
			        timer.start();
		    }
		    @Override public void stop() {
		        timeline.stop();
		        timer.stop();
		    }
		    public double getSampleWidth() { return 5.0; }
		    public double getSampleHeight() { return 5.0; }
		 
		    @Override public void start(Stage primaryStage) throws Exception {
		        init(primaryStage);
		        primaryStage.show();
		        play();
		    }
		    public static void main(String[] args) {
		    	launch(args); 
		    }

		}//end class
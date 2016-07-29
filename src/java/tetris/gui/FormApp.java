package tetris.gui;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.geometry.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;

import tetris.glass.*;

public class FormApp extends Application
{
  public static void main(String[] args)
  {
    Glass2D.SetDimensions(10, 20);
    launch(args);
  }
  
  @Override
  public void start(Stage primaryStage) 
  {
    Group root = new Group();
    Scene scene = new Scene(root, 300, 300); //, Color.BLACK

    final Canvas canvas = new Canvas(250,250);
    GraphicsContext gc = canvas.getGraphicsContext2D();
 
    gc.setFill(Color.FIREBRICK);
    gc.fillRect(75,75,100,100);
 
    root.getChildren().add(canvas); 
    primaryStage.setScene(scene);
    primaryStage.show();      
  }
}

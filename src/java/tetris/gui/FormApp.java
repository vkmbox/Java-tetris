package tetris.gui;

import javafx.application.Application;
import javafx.animation.*;
import javafx.event.*;
import javafx.stage.Stage;
import javafx.geometry.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.util.*;

import tetris.glass.*;

public class FormApp extends Application
{
  Timeline timeline;
  
  public static void main(String[] args)
  {
    Glass2D.SetDimensions(10, 20);
    launch(args);
  }
  
  @Override
  public void start(Stage primaryStage) 
  {
    /*class TimerEvent extends EventHandler<ActionEvent>
    {
      public void handle(ActionEvent event)
      {
        System.out.println("XX");
      }
    }*/
    
    timeline = new Timeline(
      new KeyFrame( Duration.millis(1250)
      , new EventHandler<ActionEvent>()
        {
          @Override
          public void handle(ActionEvent event)
          {
            System.out.println("XX");
          }
        }
      ));
    timeline.setCycleCount(Animation.INDEFINITE);  

    Group root = new Group();
    Scene scene = new Scene(root, 300, 300); //, Color.BLACK
    
    MenuBar  menuBar = new MenuBar();
    Menu menuFile = new Menu("File");
    MenuItem item = new MenuItem("Start");
    item.setOnAction( 
    new EventHandler<ActionEvent> ()
    {
      @Override
      public void handle(ActionEvent event)
      {
        if (item.getText().equals("Start"))
        {
          timeline.play();
          item.setText("Stop");
        }
        else
        {
          timeline.stop();
          item.setText("Start");
        }
      }
    }
    );
    
    menuFile.getItems().addAll(item);
    menuBar.getMenus().addAll(menuFile);

    final Canvas canvas = new Canvas(250,250);
    GraphicsContext gc = canvas.getGraphicsContext2D();
 
    gc.setFill(Color.FIREBRICK);
    gc.fillRect(75,75,100,100);
 
    root.getChildren().addAll(canvas,menuBar); 
    primaryStage.setScene(scene);
    primaryStage.show();      
  }
}

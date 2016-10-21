package tetris.gui;

import javafx.application.Application;
import javafx.animation.*;
import javafx.event.*;
import javafx.stage.Stage;
//import javafx.geometry.*;
//import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.util.*;

import tetris.glass.*;

public class FormApp extends Application
{
  static final int DIM_X = 12;
  static final int DIM_Y = 30;
  static final int PIX_IN_POINT = 20;
  
  Timeline timeline;
  Canvas canvas;
  
  public static void main(String[] args)
  {
    Glass2D.SetDimensions(DIM_X, DIM_Y);
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
    
    Group root = new Group();
    Scene scene = new Scene(root, DIM_X*PIX_IN_POINT+50, DIM_Y*PIX_IN_POINT); //, Color.BLACK
    canvas = new Canvas(DIM_X*PIX_IN_POINT, DIM_Y*PIX_IN_POINT);
    
    MenuBar  menuBar = new MenuBar();
    Menu menuFile = new Menu("File");
    
    timeline = new Timeline(
      new KeyFrame( Duration.millis(1250)
      , new EventHandler<ActionEvent>()
        {
          @Override
          public void handle(ActionEvent event)
          {
            try
            {
              Glass2D.getInstance().doStep();
              //Drawing picture
              GraphicsContext gc = canvas.getGraphicsContext2D();
              gc.fill();
              for ( GlassPoint gp : Glass2D.getInstance().getCurrent().getGlassPoints())
                writeGlassPoint(gp);
              for ( GlassPoint gp : Glass2D.getInstance().getPoints() )
                writeGlassPoint(gp);
              System.out.println("XX");
            }
            catch (NoPlaceForFigureException ex)
            {
              if (timeline != null) timeline.stop();
              Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "The game is over");
              alert.show(); //.showAndWait();
            }
          }
        }
      ));
    timeline.setCycleCount(Animation.INDEFINITE);  

    MenuItem item = new MenuItem("Start");
    item.setOnAction( 
    new EventHandler<ActionEvent> ()
    {
      @Override
      public void handle(ActionEvent event)
      {
        if (item.getText().equals("Start"))
        {
          Glass2D.getInstance().initialize();
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


//    GraphicsContext gc = canvas.getGraphicsContext2D();
//    gc.fillRect(75,75,100,100);
 
    root.getChildren().addAll(canvas,menuBar); 
    primaryStage.setScene(scene);
    primaryStage.show();      
  }
  
  private void writeGlassPoint(GlassPoint gp)
  {
    GraphicsContext gc = canvas.getGraphicsContext2D();
    gc.setFill(Color.rgb(gp.getColor().getRed(), gp.getColor().getGreen(), gp.getColor().getBlue()));
    gc.fillRect( gp.getPosX()*PIX_IN_POINT, gp.getPosY()*PIX_IN_POINT
               , (gp.getPosX()+1)*PIX_IN_POINT, (gp.getPosY()+1)*PIX_IN_POINT); //(75,75,100,100);
    //gc.fillRect(75,75,100,100);
    
  }
  
}

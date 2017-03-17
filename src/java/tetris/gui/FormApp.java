package tetris.gui;

import javafx.application.Application;
import javafx.animation.*;
import javafx.event.*;
import javafx.event.EventHandler;
import javafx.stage.Stage;
//import javafx.geometry.*;
//import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.scene.input.KeyEvent;
import javafx.util.*;

import tetris.glass.*;

public class FormApp extends Application
{
  static final int DIM_X = 10;
  static final int DIM_Y = 20;
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
    Scene scene = new Scene(root, (DIM_X+1)*PIX_IN_POINT, (DIM_Y+4)*PIX_IN_POINT); //, Color.BLACK
    canvas = new Canvas((DIM_X+1)*PIX_IN_POINT, (DIM_Y+4)*PIX_IN_POINT);
    
    MenuBar  menuBar = new MenuBar();
    Menu menuFile = new Menu("File");
    MenuItem item = new MenuItem("Start");
    menuFile.getItems().addAll(item);
    menuBar.getMenus().addAll(menuFile);
    root.getChildren().addAll(canvas,menuBar); 

    scene.setOnKeyPressed(new EventHandler<KeyEvent>()
    {
      @Override
      public void handle(KeyEvent event)
      {
        Figure current = Glass2D.getInstance().getCurrent();
        if (current == null) return;
        switch ( event.getCode() )
        {
          case DOWN: 
            if (Glass2D.getInstance().isIntersection(current, 0, 1) == false)
            { current.shiftY(); drawPoints(); }
            break;
          case LEFT:
            if (Glass2D.getInstance().isIntersection(current, -1, 0) == false)
            { current.shiftX(true); drawPoints(); }
            break;
          case RIGHT:
            if (Glass2D.getInstance().isIntersection(current, 1, 0) == false)
            { current.shiftX(false); drawPoints(); }
            break;
        }
      }
    }
    );
    
    timeline = new Timeline(
      new KeyFrame( Duration.millis(500)
      , new EventHandler<ActionEvent>()
        {
          @Override
          public void handle(ActionEvent event)
          {
            try
            {
              Glass2D.getInstance().doStep();
              //Drawing picture
              drawPoints();
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

//    GraphicsContext gc = canvas.getGraphicsContext2D();
//    gc.fillRect(75,75,100,100);
 
    //root.getChildren().addAll(canvas,menuBar); 
    primaryStage.setScene(scene);
    primaryStage.show();      
  }
  
  private void drawPoints()
  {
    GraphicsContext gc = canvas.getGraphicsContext2D();
    gc.setFill(Color.WHITE);
    gc.fillRect(0,0,(DIM_X+1)*PIX_IN_POINT, (DIM_Y+4)*PIX_IN_POINT);
    gc.setFill(Color.BLACK);
    gc.strokeRect(0,0,(DIM_X+1)*PIX_IN_POINT, (DIM_Y+4)*PIX_IN_POINT);
    for ( GlassPoint gp : Glass2D.getInstance().getCurrent().getGlassPoints())
      drawGlassPoint(gp);
    for ( GlassPoint gp : Glass2D.getInstance().getPoints() )
      drawGlassPoint(gp);    
  }
  
  private void drawGlassPoint(GlassPoint gp)
  {
    GraphicsContext gc = canvas.getGraphicsContext2D();
    gc.setFill(Color.rgb(gp.getColor().getRed(), gp.getColor().getGreen(), gp.getColor().getBlue()));
    gc.fillRect( gp.getPosX()*PIX_IN_POINT, 50+gp.getPosY()*PIX_IN_POINT
               , PIX_IN_POINT, PIX_IN_POINT); //(75,75,100,100);
    gc.setFill(Color.BLACK);
    gc.strokeRect( gp.getPosX()*PIX_IN_POINT, 50+gp.getPosY()*PIX_IN_POINT
               , PIX_IN_POINT, PIX_IN_POINT); //(75,75,100,100);
    //gc.fillRect(75,75,100,100);
    
  }
  
}

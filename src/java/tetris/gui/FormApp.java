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
  
  private static enum GameStatus
  { FINISHED, PLAYING, PAUSED }
  
  private GameStatus gameStatus;
  
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
    Group root = new Group();
    Scene scene = new Scene(root, (DIM_X+1)*PIX_IN_POINT, (DIM_Y+4)*PIX_IN_POINT); //, Color.BLACK
    canvas = new Canvas((DIM_X+1)*PIX_IN_POINT, (DIM_Y+4)*PIX_IN_POINT);
    gameStatus = GameStatus.FINISHED;
    
    MenuBar  menuBar = new MenuBar();
    Menu menuFile = new Menu("File");
    MenuItem itemStart = new MenuItem("Start");
    MenuItem itemPause = new MenuItem("Pause");
    menuFile.getItems().addAll(itemStart, itemPause);
    menuBar.getMenus().addAll(menuFile);
    root.getChildren().addAll(canvas,menuBar); 

    scene.setOnKeyPressed(new EventHandler<KeyEvent>() //null); setOnKeyPressed setOnKeyTyped
    {
      @Override
      public void handle(KeyEvent event)
      {
        Figure current = Glass2D.getInstance().getCurrent();
        if (current == null) return;
        switch ( event.getCode() )
        {
          case UP: 
            if (Glass2D.getInstance().isIntersection(current, false) == false)
            { current.rotateXY(false); drawPoints();}
            break;
          case DOWN: 
            if (Glass2D.getInstance().isIntersection(current, 0, 1) == false)
            { current.shiftY(); drawPoints();}
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
      new KeyFrame( Duration.millis(2500)
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
              //System.out.println("XX");
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

    itemStart.setOnAction
    ( new EventHandler<ActionEvent> ()
      {
        @Override
        public void handle(ActionEvent event)
        {
          switch (gameStatus)
          {  
            case FINISHED:
              Glass2D.getInstance().initialize();
              itemStart.setText("Stop");
              gameStatus = GameStatus.PLAYING;
              timeline.play();
              break;
            case PAUSED:
            case PLAYING:
              timeline.stop();
              Glass2D.getInstance().initialize();
              drawPoints();
              itemStart.setText("Start");
              itemPause.setText("Pause");
              gameStatus = GameStatus.FINISHED;
              break;
          }
        }
      }
    );
    
    itemPause.setOnAction
    ( new EventHandler<ActionEvent> ()
      {
        @Override
        public void handle(ActionEvent event)
        {
          switch (gameStatus)
          {  
            case PLAYING:
              timeline.stop();
              itemPause.setText("Resume");
              gameStatus = GameStatus.PAUSED;
              break;
            case PAUSED:
              timeline.play();
              itemPause.setText("Pause");
              gameStatus = GameStatus.PLAYING;
              break;
          }
        }
      }
    );
    
    itemPause.setOnMenuValidation
    ( new EventHandler<Event> () 
      {
        @Override
        public void handle(Event event)
        { itemPause.setDisable(gameStatus == GameStatus.FINISHED); }        
      }
    );

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
    if (Glass2D.getInstance().getCurrent() != null)
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

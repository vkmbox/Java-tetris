package tetris.glass;

import java.util.*;
import tetris.glass.GlassPoint.*;

public class Figure2DLeftCorner extends Figure
{
  public Figure2DLeftCorner(int pPosX, Figure.Direction pFgXY)
  {
    super(Figure.FigureType.SQUARE, Color.RED, pPosX, -1, 0, Figure.Direction.NORTH, pFgXY);
    points = Arrays.asList
      ( new FigurePoint(new GlassPoint(Color.RED),-1,0,0), new FigurePoint(new GlassPoint(Color.RED),0,0,0)
      , new FigurePoint(new GlassPoint(Color.RED),1,0,0), new FigurePoint(new GlassPoint(Color.RED),1,1,0));
    savePointsTo(glassPoints);
  }
  
}

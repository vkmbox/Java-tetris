package tetris.glass;

import java.util.*;
import tetris.glass.GlassPoint.*;

public class Figure2DStick extends Figure
{
  public Figure2DStick(int pPosX, Figure.Direction pFgXY)
  {
    super(Figure.FigureType.STICK, Color.BLUE, pPosX, -1, 0, Figure.Direction.NORTH, pFgXY);
    points = Arrays.asList
      ( new FigurePoint(new GlassPoint(Color.BLUE),-1,0,0), new FigurePoint(new GlassPoint(Color.BLUE),0,0,0)
      , new FigurePoint(new GlassPoint(Color.BLUE),1,0,0), new FigurePoint(new GlassPoint(Color.BLUE),2,0,0));
    savePointsTo(glassPoints);
  }
}

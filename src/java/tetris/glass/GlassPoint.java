package tetris.glass;

import java.awt.Color;

public class GlassPoint
{
  //Set relative position for Direction.NORTH
  public GlassPoint(Color pColor) //(int pX, int pY, int pZ)
  {
    color = pColor;
  }
  
  private int posX;
  public int getPosX()
  { return posX; }
  public void setPosX(int pPosX)
  { posX = pPosX; }
  
  private int posY;
  public int getPosY()
  { return posY; }
  public void setPosY(int pPosY)
  { posY = pPosY; }
  
  private int posZ;
  public int getPosZ()
  { return posZ; }
  public void setPosZ(int pPosZ)
  { posZ = pPosZ; }
  
  private final Color color;
  public Color getColor(){ return color; }
}

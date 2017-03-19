package tetris.glass;

public class GlassPoint
{
  public static final class Color
  {
    public static final Color YELLOW = new Color(255, 255, 0);
    public static final Color BLUE = new Color(0, 0, 255);
    public static final Color GREEN = new Color(0, 255, 0);
    public static final Color RED = new Color(255, 0, 0);
    
    public Color(int r, int g, int b) 
    { this.R = r; this.G = g; this.B = b; }
    
    private final int R;
    public int getRed() { return this.R; }  
    
    private final int G;
    public int getGreen() { return this.G; }  
    
    private final int B;
    public int getBlue() { return this.B; }  
  }
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

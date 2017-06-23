package tetris.gui;

import javafx.scene.control.Alert;
import java.text.MessageFormat;
import tetris.dm.DataModule;

public class DialogStatistics
{
  public static void showModal(String userName)
  {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    String header = MessageFormat.format("User {0} statistics", userName); 
    alert.setTitle(header);
    alert.setHeaderText(header);
    DataModule dm = DataModule.getInstance();
    String contentText = dm.getUserStat(userName);
    alert.setContentText(contentText);
    alert.showAndWait();    
  }
}
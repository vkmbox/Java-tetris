package tetris.gui;

import javafx.scene.control.Alert;

public class DialogAlert
{
  public static void showModal(String title, String headerText, String contentText)
  {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(headerText);
    alert.setContentText(contentText);
    alert.showAndWait();
  }
  
}

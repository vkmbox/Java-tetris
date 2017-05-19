package tetris.gui;

import java.util.Optional;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import tetris.dm.DataModule;

public class DialogLogin
{
  
  private static TextField userin;
  private static PasswordField passwin;
  private static TextField userup;
  private static PasswordField passwup1;
  private static PasswordField passwup2;
  
  public static enum LoginMode{ OnLogin, OnChangePassword };
  
  private static boolean validateAndStore(String mode, LoginMode loginMode)
  {
    if ( (mode.equals("Sign up") && (userup.getText() == null || userup.getText().isEmpty()))
      || (mode.equals("Sign in") && (userin.getText() == null || userin.getText().isEmpty()))
       )
    {
      DialogAlert.showModal(mode, mode + " error", "Username is empty"); 
      return false;
    }

    if ( (mode.equals("Sign up") && (passwup1.getText() == null || passwup1.getText().isEmpty()))
      || (mode.equals("Sign in") && (passwin.getText() == null || passwin.getText().isEmpty()))
       )
    { 
      DialogAlert.showModal(mode, mode + " error", "Password is empty"); 
      return false;
    }
    
    if ( (mode.equals("Sign up") && !passwup1.getText().equals(passwup2.getText()) )
       )
    { 
      DialogAlert.showModal(mode, mode + " error", "Passwords are not equals"); 
      return false;
    }
    
    DataModule dm = DataModule.getInstance();
    switch ( mode )
    {
      case "Sign in":
        boolean result = dm.checkUserPassw(userin.getText(), passwin.getText().getBytes());
        if (result == false)
          DialogAlert.showModal(mode, mode + " error", "Incorrect username or password"); 
        return result;
      case "Sign up":
        if (loginMode == LoginMode.OnLogin)
          dm.saveUserLogin(userup.getText(), passwup1.getText().getBytes());
        else
          dm.mergeUserLogin(userup.getText(), passwup1.getText().getBytes());
        return true;
    }
    return true;
  }
  
  //public static Optional<Pair<String, String>> showModal()
  public static Optional<String> showModal( LoginMode mode, String userName )
  {
    // Create the custom dialog.
    //Dialog<Pair<String, String>> dialog = new Dialog<>();
    Dialog<String> dialog = new Dialog<>();
    dialog.setTitle( mode == LoginMode.OnLogin ? "Login Dialog":"ChangePassword");
    dialog.setHeaderText("Look, a Custom Login Dialog");

    // Set the icon (must be included in the project).
    //dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));

    // Set the button types.
    ButtonType loginButtonType = 
      new ButtonType(mode == LoginMode.OnLogin ?"Login":"Save", ButtonBar.ButtonData.OK_DONE);
    //ButtonType buttonTypeThree = new ButtonType("Three");
    //ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);    
    dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

    // Create the username and password labels and fields.
    TabPane tabPane = new TabPane(); Tab tab;
    
    if (mode == LoginMode.OnLogin)
    {  
      tab = new Tab();

      GridPane gridin = new GridPane();
      gridin.setHgap(10);
      gridin.setVgap(10);
      gridin.setPadding(new Insets(20, 150, 10, 10));

      userin = new TextField();
      userin.setPromptText("Username");
      passwin = new PasswordField();
      passwin.setPromptText("Password");

      gridin.add(new Label("Username:"), 0, 0);
      gridin.add(userin, 1, 0);
      gridin.add(new Label("Password:"), 0, 1);
      gridin.add(passwin, 1, 1);

      tab.setText("Sign in");
      tab.setContent(gridin);
      tabPane.getTabs().add(tab);
    }
    
    tab = new Tab();
    GridPane gridup = new GridPane();
    gridup.setHgap(10);
    gridup.setVgap(10);
    gridup.setPadding(new Insets(30, 150, 10, 10));

    userup = new TextField();
    userup.setPromptText("Username");
    userup.setText(userName);
    userup.setEditable( mode == LoginMode.OnLogin );
    passwup1 = new PasswordField();
    passwup1.setPromptText("Password");
    passwup2 = new PasswordField();
    passwup2.setPromptText("Repeat password");

    gridup.add(new Label("Username:"), 0, 0);
    gridup.add(userup, 1, 0);
    gridup.add(new Label("Password:"), 0, 1);
    gridup.add(passwup1, 1, 1);
    gridup.add(new Label("Repeat password:"), 0, 2);
    gridup.add(passwup2, 1, 2);
    
    tab.setText("Sign up");
    tab.setContent(gridup);
    tabPane.getTabs().add(tab);
    
    dialog.getDialogPane().setContent(tabPane);

    // Enable/Disable login button depending on whether a username was entered.
    Button loginButton = (Button)dialog.getDialogPane().lookupButton(loginButtonType);
    loginButton.addEventFilter(ActionEvent.ACTION, event -> {
      if (!validateAndStore(tabPane.getSelectionModel().getSelectedItem().getText(), mode)) {
         event.consume();
      }
    });    
    //loginButton.setOnMouseClicked(((event) -> {System.out.println("Here");})); // addEventHandler(EventType.ROOT, null);
    //loginButton.setDisable(true);

    // Do some validation (using the Java 8 lambda syntax).
    /*username.textProperty().addListener((observable, oldValue, newValue) -> {
        loginButton.setDisable(newValue.trim().isEmpty());
    });*/
    
    // Request focus on the username field by default.
    Platform.runLater(() -> userin.requestFocus());

    /*dialog.setOnCloseRequest((DialogEvent event) -> 
    { if (event.toString() == "DIALOG_CLOSE_REQUEST") 
        event.consume();
    });*/
    // Convert the result to a username-password-pair when the login button is clicked.
    dialog.setResultConverter(dialogButton -> {
        if (dialogButton == loginButtonType) {
            //return new Pair<>(userin.getText(), passwin.getText());
            return tabPane.getSelectionModel().getSelectedItem().getText() == "Sign in"?userin.getText():userup.getText();
        }
        return null;
    });

    return dialog.showAndWait();
    //Optional<Pair<String, String>> result = dialog.showAndWait();
    //return result;
    //result.ifPresent(usernamePassword -> {
     //   System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
    //});    
  }
}
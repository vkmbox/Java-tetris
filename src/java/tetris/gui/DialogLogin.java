package tetris.gui;

import java.util.Optional;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;


public class DialogLogin
{
  public static void showModal()
  {
    // Create the custom dialog.
    Dialog<Pair<String, String>> dialog = new Dialog<>();
    dialog.setTitle("Login Dialog");
    dialog.setHeaderText("Look, a Custom Login Dialog");

    // Set the icon (must be included in the project).
    //dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));

    // Set the button types.
    ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
    dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

    // Create the username and password labels and fields.
    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(20, 150, 10, 10));

    TextField username = new TextField();
    username.setPromptText("Username");
    PasswordField password = new PasswordField();
    password.setPromptText("Password");

    grid.add(new Label("Username:"), 0, 0);
    grid.add(username, 1, 0);
    grid.add(new Label("Password:"), 0, 1);
    grid.add(password, 1, 1);

    // Enable/Disable login button depending on whether a username was entered.
    Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
    loginButton.setDisable(true);

    // Do some validation (using the Java 8 lambda syntax).
    username.textProperty().addListener((observable, oldValue, newValue) -> {
        loginButton.setDisable(newValue.trim().isEmpty());
    });

    dialog.getDialogPane().setContent(grid);

    // Request focus on the username field by default.
    Platform.runLater(() -> username.requestFocus());

    // Convert the result to a username-password-pair when the login button is clicked.
    dialog.setResultConverter(dialogButton -> {
        if (dialogButton == loginButtonType) {
            return new Pair<>(username.getText(), password.getText());
        }
        return null;
    });

    Optional<Pair<String, String>> result = dialog.showAndWait();

    result.ifPresent(usernamePassword -> {
        System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
    });    
  }
}
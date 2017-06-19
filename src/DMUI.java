import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

import java.util.Optional;

/**
 * Created by bigzero on 6/19/17.
 */
public class DMUI extends Application
{
    public static void main(String[] args)
    {

        launch(args);

    }
    // Hack: Set connection as public static so it may be used

    private DMUI_ChessBoard board;
    private static boolean playerIsWhite;

    public static boolean getPlayerWhite(){return playerIsWhite;}

    @Override
    public void start(Stage mainStage)
    {
        mainStage.setTitle("Chess Game");
        mainStage.getIcons().add( new Image("image/icons/app_icon.png") );

        BorderPane root = new BorderPane();
        Scene mainScene = new Scene(root);
        mainStage.setScene(mainScene);

        mainScene.getStylesheets().add("image/stylesheet.css");
        MenuBar menuBar = generateMenuBar();
        root.setTop(menuBar);

        chooseTypePlayer();

        board = new DMUI_ChessBoard(playerIsWhite);
        root.setCenter(board); // sized 400x400

        mainStage.show();
    }

    public static int choosePromoted(){
        int select;
        Alert newGameAlert = new Alert(Alert.AlertType.CONFIRMATION);
        newGameAlert.setTitle("Select promoted - DMChessAI");
        newGameAlert.setHeaderText(null);

        Image image = new Image("image/icons/about.png");
        ImageView imageView = new ImageView(image);
        newGameAlert.setGraphic(imageView);
        newGameAlert.setContentText("Choose piece promote: ");

        ButtonType buttonTypeQueen = new ButtonType("Queen");
        ButtonType buttonTypeRook = new ButtonType("Rook");
        ButtonType buttonTypeBiShop = new ButtonType("Bishop");
        ButtonType buttonTypeKnight = new ButtonType("Knight");

        newGameAlert.getButtonTypes().setAll(buttonTypeQueen, buttonTypeRook, buttonTypeKnight, buttonTypeBiShop);
        Optional<ButtonType> result = newGameAlert.showAndWait();


        if (result.get() == buttonTypeQueen)
        {
            select = 0;
        }
        else if (result.get() == buttonTypeRook)
        {
            select = 1;
        }
        else if (result.get() == buttonTypeKnight)
        {
            select = 2;
        }
        else
        {
            select = 3;
        }
        return select;
    }

    public void chooseTypePlayer()
    {
        Alert newGameAlert = new Alert(Alert.AlertType.CONFIRMATION);
        newGameAlert.setTitle("Start new game - DMChessAI");
        newGameAlert.setHeaderText(null);

        Image image = new Image("image/icons/about.png");
        ImageView imageView = new ImageView(image);
        newGameAlert.setGraphic(imageView);
        newGameAlert.setContentText("Choose type: ");

        ButtonType buttonTypeWhite = new ButtonType("White");
        ButtonType buttonTypeBlack = new ButtonType("Black");

        newGameAlert.getButtonTypes().setAll(buttonTypeWhite, buttonTypeBlack);
        Optional<ButtonType> result = newGameAlert.showAndWait();

        if (result.get() == buttonTypeWhite)
        {
            this.playerIsWhite = true;
        }
        else
        {
            this.playerIsWhite = false;
        }
    }
    public static void endGame(boolean peopleWin)
    {
        Alert newGameAlert = new Alert(Alert.AlertType.CONFIRMATION);
        newGameAlert.setTitle("End game - DMChessAI");
        newGameAlert.setHeaderText(null);

        Image image = new Image("image/icons/about.png");
        ImageView imageView = new ImageView(image);
        newGameAlert.setGraphic(imageView);
        if (peopleWin)
            newGameAlert.setContentText("YOU WIN");
        else
            newGameAlert.setContentText("COMPUTER WIN");

        ButtonType buttonTypeQueen = new ButtonType("EXIT");

        newGameAlert.getButtonTypes().setAll(buttonTypeQueen);
        Optional<ButtonType> result = newGameAlert.showAndWait();

    }
    private MenuBar generateMenuBar()
    {
        MenuBar menuBar = new MenuBar();

        Menu gameMenu = new Menu("File");
        menuBar.getMenus().add(gameMenu);

        MenuItem menuItemQuit = new MenuItem("Quit");
        menuItemQuit.setOnAction(e -> onQuit());

        menuItemQuit.setAccelerator( new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN) );
        gameMenu.getItems().add(menuItemQuit);

        Menu menuHelp = new Menu("Help");
        menuBar.getMenus().add(menuHelp);

        MenuItem menuItemAbout = new MenuItem("About");

        menuItemAbout.setAccelerator( new KeyCodeCombination(KeyCode.F1) );
        menuItemAbout.setOnAction(e -> onDisplayAbout());
        menuHelp.getItems().add(menuItemAbout);

        return menuBar;
    }
    public void onQuit()
    {
        Platform.exit();
        System.exit(0);
    }
    public void onDisplayAbout()
    {
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setTitle("About this program");
        infoAlert.setHeaderText(null);

        Stage alertStage = (Stage) infoAlert.getDialogPane().getScene().getWindow();
        Image image = new Image("image/icons/about.png");
        ImageView imageView = new ImageView(image);
        infoAlert.setGraphic(imageView);
        alertStage.getIcons().add( new Image("image/icons/app_icon.png") );


        infoAlert.setContentText("Programmed by DMTeam.\n\n" +
                "Programming based on:\n" + "Java & JavaFX Software\n" +
                "1. DUY Le Trinh Khanh \n2.MINH Quang Thai\nOpen Source: https://github.com/duyltk/DMChessJV");
        infoAlert.showAndWait();
    }
}

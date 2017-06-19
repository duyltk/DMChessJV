import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.application.Application;

import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

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


    @Override
    public void start(Stage mainStage)
    {

        mainStage.setTitle("Chess Game");
        mainStage.getIcons().add( new Image("image/icons/app_icon.png") );

        BorderPane root = new BorderPane();
        Scene mainScene = new Scene(root);
        mainStage.setScene(mainScene);

        mainScene.getStylesheets().add("image/stylesheet.css");

        board = new DMUI_ChessBoard();
        root.setCenter(board); // sized 400x400


        mainStage.show();
    }
}

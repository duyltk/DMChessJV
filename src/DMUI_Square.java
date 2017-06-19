/**
 * Created by bigzero on 6/19/17.
 */
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class DMUI_Square extends Button{
    private int x;
    private int y;
    public String piece;
    public String pathImgPiece;
    public DMUI_Square(boolean light, int x, int y)
    {
        super();
        this.x = x;
        this.y = y;
        this.getStyleClass().add("chess-space");

        if (light)
            this.getStyleClass().add("chess-space-light");
        else
            this.getStyleClass().add("chess-space-dark");

        setPathGraphic();

    }

    public void setPathGraphic(){
        piece = DMChess.Board[y][x];

        pathImgPiece = "image/pieces/";

        if (!" ".equals(piece))
        {
            if (Character.isLowerCase(piece.charAt(0)))
            {
                if (DMUI.getPlayerWhite())
                    pathImgPiece = pathImgPiece +  "white_";
                else
                    pathImgPiece = pathImgPiece + "black_";

            }
            else
            {
                if (DMUI.getPlayerWhite())
                    pathImgPiece = pathImgPiece +  "black_";
                else
                    pathImgPiece = pathImgPiece + "white_";
            }

            String tempPiece = piece;
            switch (tempPiece.toLowerCase())
            {
                case "p":
                    pathImgPiece = pathImgPiece + "pawn.png";
                    break;
                case "r":
                    pathImgPiece = pathImgPiece + "rook.png";
                    break;
                case "b":
                    pathImgPiece = pathImgPiece + "bishop.png";
                    break;
                case "k":
                    pathImgPiece = pathImgPiece + "knight.png";
                    break;
                case "q":
                    pathImgPiece = pathImgPiece + "queen.png";
                    break;
                case "a":
                    pathImgPiece = pathImgPiece + "king.png";
                    break;
            }
            this.setGraphic( new ImageView (pathImgPiece));
        }
        else {
            this.setGraphic( new ImageView ());
        }
    }
}

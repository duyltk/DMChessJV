/**
 * Created by bigzero on 6/19/17.
 */
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;

public class DMUI_ChessBoard extends GridPane{
    public DMUI_Square[][] Square = new DMUI_Square[8][8];
    public boolean flagClick = false;
    public DMUI_Square activeSquare = null;
    String ListMove = "";

    public DMUI_ChessBoard(boolean playerIsWhite)
    {
        super();

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                boolean light = ((x + y) % 2 != 0); // checkerboard space colors
                Square[x][y] = new DMUI_Square(light, x, y);

                this.add(Square[x][y], x, 7 - y);
                final int xVal = x;
                final int yVal = y;

                try {
                    Square[x][y].setOnAction(e -> onSpaceClick(xVal, yVal));
                }catch(Exception e){}
            }
        }
        if (!DMUI.getPlayerWhite()) {
            waitThread();
        }
    }
    public void waitThread(){
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                runAI();
            }
        });
        new Thread(sleeper).start();
    }

    public void runAI()
    {
        if (DMChess.movePieces().length() == 0) {
            DMUI.endGame(true);
            return;
        }

        String move = DMChess.alphabeta(DMChess.globalDepth, Integer.MIN_VALUE, Integer.MAX_VALUE, "", 0);
        DMChess.applyMove(move.substring(0, 5));

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Square[x][y].setPathGraphic();
            }
        }
        DMChess.flipBoard();
        if(DMChess.movePieces().length() == 0)
        {

            DMUI.endGame(false);
        }
        DMChess.flipBoard();
    }


    public void onSpaceClick(int x, int y)
    {

        if (Character.isLowerCase(Square[x][y].piece.charAt(0)))
        {
            if (!flagClick)
            {
                Square[x][y].getStyleClass().add("chess-space-active");
                activeSquare = Square[x][y];
                flagClick = true;

                DMChess.flipBoard();

                switch(activeSquare.piece.toUpperCase())
                {
                    case "P":
                        ListMove = DMChess.movePawn((7-y) * 8 +(7-x));
                        break;
                    case "R":
                        ListMove = DMChess.moveRook((7-y) * 8 +(7-x));
                        break;
                    case "B":
                        ListMove = DMChess.moveBishop((7-y) * 8 +(7-x));
                        break;
                    case "K":
                        ListMove = DMChess.moveKnight((7-y) * 8 +(7-x));
                        break;
                    case "Q":
                        ListMove = DMChess.moveQueen((7-y) * 8 +(7-x));
                        break;
                    case "A":
                        ListMove = DMChess.moveKing((7-y) * 8 +(7-x));
                        break;
                }
                DMChess.flipBoard();

                for (int i = 0; i < ListMove.length(); i+=5)
                {
                    if (ListMove.charAt(i + 4) != 'C' && ListMove.charAt(i + 4) != 'P'){
                        int col = 7-Integer.parseInt(String.valueOf(ListMove.charAt(i + 3)));
                        int row = 7-Integer.parseInt(String.valueOf(ListMove.charAt(i + 2)));

                        if (((col + row) % 2 != 0))
                            Square[col][row].getStyleClass().add("chess-space-hover-light");
                        else
                            Square[col][row].getStyleClass().add("chess-space-hover-dark");
                    }
                    else if(ListMove.charAt(i + 4) == 'C'){
                        //Colume Previous King, Rook => Next King, Rook
                        int colRook = 7-Integer.parseInt(String.valueOf(ListMove.charAt(i + 1)));

                        if (colRook % 2 == 0){
                            Square[colRook][0].getStyleClass().add("chess-space-hover-dark");
                        }
                        else
                            Square[colRook][0].getStyleClass().add("chess-space-hover-light");

                    } else
                    {
                        int nextcol = 7-Integer.parseInt(String.valueOf(ListMove.charAt(i + 1)));

                        if (nextcol % 2 == 0){
                            Square[nextcol][7].getStyleClass().add("chess-space-hover-dark");
                        }
                        else
                            Square[nextcol][7].getStyleClass().add("chess-space-hover-light");
                    }
                }
            }
            else {
                if (Square[x][y] == activeSquare)
                {
                    activeSquare.getStyleClass().removeAll("chess-space-active");
                    activeSquare = null;
                    flagClick = false;
                    ListMove = "";
                }
                else if(Square[x][y].piece.equals("r") && activeSquare.piece.equals("a")){
                    int updateRook = 0, updateKing = 0;
                    String applyMoveKing = "";
                    for (int i = 0; i < ListMove.length(); i+=5){
                        applyMoveKing = ListMove.substring(i, i + 5);
                        if (applyMoveKing.charAt(4) == 'C' && (7-x) == Integer.parseInt(String.valueOf(applyMoveKing.charAt(i + 1))))
                        {
                            DMChess.flipBoard();
                            DMChess.applyMove(applyMoveKing);
                            DMChess.flipBoard();

                            for (int k = 0; k < 8; k++) {
                                for (int l = 0; l < 8; l++) {
                                    Square[k][l].setPathGraphic();
                                }
                            }
                            flagClick = false;
                            waitThread();
                        }
                    }
                }
            }
        }
        else {
            if (flagClick){
                boolean flagPromote = false;
                for (int i = 0; i < ListMove.length(); i+= 5)
                {
                    if(ListMove.charAt(i + 4) == 'P')
                    {
                        if (flagPromote)
                            continue;
                        if((7-x) == Integer.parseInt(String.valueOf(ListMove.charAt(i + 1)))){
                            flagPromote = true;
                            int select_Promoted = DMUI.choosePromoted();
                            String temp[] = {"Q", "R", "K", "B"};
                            DMChess.flipBoard();
                            DMChess.applyMove(ListMove.substring(i, i + 3) + temp[select_Promoted] + "P");
                            DMChess.flipBoard();


                            for (int k = 0; k < 8; k++) {
                                for (int l = 0; l < 8; l++) {
                                    Square[k][l].setPathGraphic();
                                }
                            }
                            flagClick = false;
                            waitThread();
                        }
                        continue;
                    }
                    int col = 7-Integer.parseInt(String.valueOf(ListMove.charAt(i + 3)));
                    int row = 7-Integer.parseInt(String.valueOf(ListMove.charAt(i + 2)));
                    if (x == col && y == row){
                        if (activeSquare.piece == "r"){
                            if (activeSquare.getX() == 7){
                                DMChess.castlingLLong = false;
                            }
                            else
                                DMChess.castlingLShort = false;
                        }
                        else if (activeSquare.piece == "r"){
                            DMChess.castlingLLong = false;
                            DMChess.castlingLShort = false;
                        }
                        DMChess.flipBoard();
                        DMChess.applyMove(ListMove.substring(i, i + 5));
                        DMChess.flipBoard();

                        for (int k = 0; k < 8; k++) {
                            for (int l = 0; l < 8; l++) {
                                Square[k][l].setPathGraphic();
                            }
                        }
                        flagClick = false;

                        waitThread();
                    }
                }
            }
        }
        if (!flagClick)
        {
            for (int i = 0; i < 8; i++)
            {
                for (int j = 0; j < 8; j++)
                {
                    Square[i][j].getStyleClass().removeAll("chess-space-hover-light");
                    Square[i][j].getStyleClass().removeAll("chess-space-hover-dark");
                }
            }
        }
    }

}

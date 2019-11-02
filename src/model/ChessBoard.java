package model;

import pieces.*;

import java.util.ArrayList;
import java.util.List;

public class ChessBoard {
    private Box[][] boxes = new Box[8][8];
    private List<Piece> removedPieces;
    private List<Piece> piecesInGame;
    private boolean isCheckOnWhite;
    private boolean isCheckOnBlack;
    private int wKingPosX;
    private int wKingPosY;
    private int bKingPosX;
    private int bKingPosY;

    public Box[][] getBoxes() {
        return boxes;
    }

    public void setBoxes(Box[][] boxes) {
        this.boxes = boxes;
    }

    public List<Piece> getRemovedPieces() {
        return removedPieces;
    }

    public void setRemovedPieces(List<Piece> removedPieces) {
        this.removedPieces = removedPieces;
    }

    public ChessBoard(){
        setUpTheBoard();
    }

    private void setUpTheBoard() {
        piecesInGame = new ArrayList<>();
        for (int j=1; j<9;j++){
            if (j==1 || j==2 || j==8 || j==7) {
                for (int i = 1; i < 9; i++) {
                    if ((i + j) % 2 == 0) {
                        boxes[i][j].setColor(Color.BLACK);
                    } else {
                        boxes[i][j].setColor(Color.WHITE);
                    }
                    if ((i == 1 && j == 1) || (i == 8 && j == 1)) {
                        Piece wRook = new Rook(Color.WHITE);
                        boxes[i][j].setPiece(wRook);
                        piecesInGame.add(wRook);
                    }
                    if ((i == 1 && j == 8) || (i == 8 && j == 8)) {
                        Piece bRook = new Rook(Color.BLACK);
                        boxes[i][j].setPiece(bRook);
                        piecesInGame.add(bRook);
                    }
                    if ((i == 2 && j == 1) || (i == 7 && j == 1)) {
                        Piece wBishop = new Bishop(Color.WHITE);
                        boxes[i][j].setPiece(wBishop);
                        piecesInGame.add(wBishop);
                    }
                    if ((i == 3 && j == 1) || (i == 6 && j == 1)) {
                        Piece wKnight = new Knight(Color.WHITE);
                        boxes[i][j].setPiece(wKnight);
                        piecesInGame.add(wKnight);
                    }
                    if (i == 4 && j == 1) {
                        Piece wQueen = new Queen(Color.WHITE);
                        boxes[i][j].setPiece(wQueen);
                        piecesInGame.add(wQueen);
                    }
                    if (i == 5 && j == 1) {
                        Piece wKing = new King(Color.WHITE);
                        boxes[i][j].setPiece(wKing);
                        piecesInGame.add(wKing);
                        wKingPosX = i;
                        wKingPosY = j;
                    }
                    if (j == 2) {
                        Piece wPawn = new Pawn(Color.WHITE);
                        boxes[i][j].setPiece(wPawn);
                        piecesInGame.add(wPawn);
                    }
                    if (j == 7) {
                        Piece bPawn = new Pawn(Color.BLACK);
                        boxes[i][j].setPiece(bPawn);
                        piecesInGame.add(bPawn);
                    }
                    if ((i == 2 && j == 8) || (i == 7 && j == 8)) {
                        Piece bBishop = new Bishop(Color.BLACK);
                        boxes[i][j].setPiece(bBishop);
                        piecesInGame.add(bBishop);
                    }
                    if ((i == 3 && j == 8) || (i == 6 && j == 8)) {
                        Piece bKinght = new Knight(Color.BLACK);
                        boxes[i][j].setPiece(bKinght);
                        piecesInGame.add(bKinght);
                    }
                    if (i == 4 && j == 8) {
                        Piece bQueen = new Queen(Color.BLACK);
                        boxes[i][j].setPiece(bQueen);
                        piecesInGame.add(bQueen);
                    }
                    if (i == 5 && j == 8) {
                        Piece bKing = new King(Color.BLACK);
                        boxes[i][j].setPiece(bKing);
                        piecesInGame.add(bKing);
                        bKingPosX = i;
                        bKingPosY = j;
                    }
                }
            }
        }
    }

    public void movePiece(int sourceX, int sourceY, int targetX, int targetY) throws GameException {
        Piece piece = boxes[sourceX][sourceY].getPiece();
        Color clr = piece.getPieceColor();
        if (clr == Color.BLACK && !isCheckOnBlack || (clr == Color.WHITE && !isCheckOnWhite)){
            if (movePiece(sourceX, sourceY, targetX, targetY, piece)) {
                return;
            }
        }else{
            if (piece instanceof King){
                if (movePiece(sourceX, sourceY, targetX, targetY, piece)) {
                    if (piece.getPieceColor() == Color.BLACK){
                        bKingPosX = targetX;
                        bKingPosY = targetY;
                    }
                    return;
                }
            }
        }
        throw new GameException("Invalid move");
    }

    private boolean movePiece(int sourceX, int sourceY, int targetX, int targetY, Piece piece) {
        if(piece.canMove(sourceX, sourceY, targetX, targetY)){
            if(boxesFromToAreEmpty(sourceX, sourceY, targetX, targetY)) {
                if (boxes[sourceX][sourceY].getPiece() != null) {
                    removePiece(targetX, targetY);
                }
                boxes[targetX][targetY].setPiece(piece);
                removePiece(sourceX, sourceY);
                checkForCheck();
                return true;
            }
        }
        return false;
    }

    private boolean boxesFromToAreEmpty(int fromX, int fromY, int toX, int toY) {
        //only on Y axis
        if ((fromY - toY) == 0){
            for(int i=fromX+1;i<toX;i++){
                if(isBoxFull(i, fromY)){
                    return false;
                }
            }
            return true;
        }
        //only on X axis
        if ((fromX - toX) == 0 ){
            for(int i=fromY+1;i<toY;i++){
                if(isBoxFull(fromX, i)){
                    return false;
                }
            }
            return true;
        }
        //diagonal move
        int dX = Math.abs(toX - fromX);
        int dY = Math.abs(toY - fromY);
        if(dX == dY){
            if (fromX < toX){
                for(int i=fromX+1;i<toX;i++){
                    if (checkY(fromY, toY, i)) return false;
                }
            }
            else{
                for(int i=fromX -1;i>toX;i--){
                    if(checkY(fromY, toY, i)) return false;
                }
            }
            return true;
        }
        return false;
    }

    private boolean checkY(int fromY, int toY, int i) {
        if (fromY < toY){
            for(int j=fromY+1; j<toY;j++){
                if (isBoxFull(i, j)){
                    return true;
                }
            }
        }else{
            for (int j =fromY-1;j>toY;j--){
                if(isBoxFull(i, j)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isBoxFull(int x, int y) {
        return boxes[x][y].getPiece() != null;
    }

    private void checkForCheck(){
        checkAroundKing(wKingPosX, wKingPosY, Color.WHITE);
        checkAroundKing(bKingPosX, bKingPosY, Color.BLACK);
    }

    private void checkAroundKing(int kingX, int kingY, Color color) {
        //todo implement check
        //it must check for each figure in opposite color if it can attack king
        for (int i = kingX - 1; i < kingX + 1; i++) {
            for (int j = kingY - 1; j < kingY + 1; j++) {
                if (i!= kingX && j!= kingY){
                    Piece pis = boxes[i][j].getPiece();
                    if (pis != null){
                        if (pis.canMove(i, j, kingX, kingY)){
                            if (color == Color.WHITE){
                                isCheckOnWhite = true;
                                return;
                            }
                            if (color == Color.BLACK){
                                isCheckOnBlack = true;
                                return;
                            }
                        }
                    }
                }
            }
        }

    }

    private void removePiece(int sourceX, int sourceY){
        removedPieces.add(boxes[sourceX][sourceY].getPiece());
        boxes[sourceX][sourceY].setPiece(null);
    }


}

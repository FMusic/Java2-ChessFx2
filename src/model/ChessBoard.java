package model;

import pieces.*;

import java.util.List;

public class ChessBoard {
    private Box[][] boxes = new Box[8][8];
    private List<Piece> removedPieces;

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
        //setting up the board
        for (int i=1; i<9; i++){
            for (int j=1; j<9;j++){
                if ((i+j)%2 == 0){
                    boxes[i][j].setColor(Color.BLACK);
                }else{
                    boxes[i][j].setColor(Color.WHITE);
                }
                if ((i==1 && j==1) || (i==8 && j==1)){
                    boxes[i][j].setPiece(new Rook(Color.WHITE));
                }
                if((i==1 && j==8)|| (i==8 && j==8)){
                    boxes[i][j].setPiece(new Rook(Color.BLACK));
                }
                if((i==2 && j==1) || (i==7 && j==1)){
                    boxes[i][j].setPiece(new Bishop(Color.WHITE));
                }
                if((i==3 && j==1) || (i==6 && j==1)){
                    boxes[i][j].setPiece(new Knight(Color.WHITE));
                }
                if(i==4 && j==1){
                    boxes[i][j].setPiece(new Queen(Color.WHITE));
                }
                if(i==5 && j==1){
                    boxes[i][j].setPiece(new King(Color.WHITE));
                }
                if(j==2){
                    boxes[i][j].setPiece(new Pawn(Color.WHITE));
                }
                if(j==7){
                    boxes[i][j].setPiece(new Pawn(Color.BLACK));
                }
                if((i==2 && j==8) || (i==7 && j==8)){
                    boxes[i][j].setPiece(new Bishop(Color.BLACK));
                }
                if((i==3 && j==8) || (i==6 && j==8)){
                    boxes[i][j].setPiece(new Knight(Color.BLACK));
                }
                if(i==4 && j==8){
                    boxes[i][j].setPiece(new Queen(Color.BLACK));
                }
                if(i==5 && j==8){
                    boxes[i][j].setPiece(new King(Color.BLACK));
                }
            }
        }
    }

    public boolean movePiece(Color pieceColor, int sourceX, int sourceY, int targetX, int targetY){
        Piece piece = boxes[sourceX][sourceY].getPiece();
        if(piece.canMove(sourceX, sourceY, targetX, targetY)){
            if(boxes[sourceX][sourceY].getPiece()!=null){
                removePiece(targetX, targetY);
            }
            boxes[targetX][targetY].setPiece(piece);
            removePiece(sourceX, sourceY);
            checkForCheck();
            return true;
        }
        return false;
    }

    public boolean checkForCheck(){
        return false;
    }

    public void removePiece(int sourceX, int sourceY){
        removedPieces.add(boxes[sourceX][sourceY].getPiece());
        boxes[sourceX][sourceY].setPiece(null);
    }


}

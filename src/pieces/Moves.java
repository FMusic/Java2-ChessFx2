package pieces;

public class Moves {
    public static boolean isBishopMove(int sourceX, int sourceY, int targetX, int targetY){
        int dx = Math.abs(targetX - sourceX);
        int dy = Math.abs(sourceY - targetY);
        if (dx == dy){
            return true;
        }
        return false;
    }

    public static boolean isRookMove(int sourceX, int sourceY, int targetX, int targetY){
        if (sourceX==targetX || sourceY == targetY){
            return true;
        }
        return false;
    }

    public static boolean isQuuenMove(int sourceX, int sourceY, int targetX, int targetY){
        if (isBishopMove(sourceX, sourceY, targetX, targetY) || isRookMove(sourceX, sourceY, targetX, targetY)){
            return true;
        }
        return false;
    }
}

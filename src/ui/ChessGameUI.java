package ui;

import javafx.scene.control.Control;

public class ChessGameUI {
    public ChessGameUI(PlayerNumber numOfHumanPlayers, ModeOfPlay mode, Control ctrl) {
        setWindowProperties();
    }

    private void setWindowProperties() {
    }

    public enum ModeOfPlay{
        WEB,
        DESKTOP
    }

    public enum PlayerNumber{
        SINGLE,
        TWO
    }
}

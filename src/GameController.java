import javax.swing.*;

public class GameController {

    public Player player1;
    public Player player2;
    public int player1Points = 0;
    public int player2Points = 0;
    public Player currentPlayer;
    public PlayerMarker[][] gameBoard;
    private GameRules gameRules;

    public GameController() {
    }

    public void startGame(GameController controller) {
        PlayerMarker[] optionMarker = {PlayerMarker.X, PlayerMarker.O, PlayerMarker.Y, PlayerMarker.Z};
        String player1Name = JOptionPane.showInputDialog(null, "Player 1 name:", "Tic Tac Toe", JOptionPane.QUESTION_MESSAGE);
        int player1Marker = JOptionPane.showOptionDialog(null, "Choose your characther:", "Tic Tac Toe",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, optionMarker, optionMarker[0]);
        if (player1Marker == -1 || player1Name == null) {
            JOptionPane.showMessageDialog(null, "Wrong input");
            System.exit(0);
        }
        String player1MarkerString = String.valueOf(optionMarker[player1Marker]);
        optionMarker = changeOptionMarker(player1Marker);
        String player2Name = JOptionPane.showInputDialog(null, "Player 2 name:", "Tic Tac Toe", JOptionPane.QUESTION_MESSAGE);
        int player2Marker = JOptionPane.showOptionDialog(null, "Choose your characther:", "Tic Tac Toe",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, optionMarker, optionMarker[0]);
        if (player2Marker == -1 || player2Name == null) {
            JOptionPane.showMessageDialog(null, "Wrong input");
            System.exit(0);
        }

        String player2MarkerString = String.valueOf(optionMarker[player2Marker]);
        createPlayer1(player1Name, player1MarkerString);
        createPlayer2(player2Name, player2MarkerString);

        currentPlayer = player1;
        gameBoard = new PlayerMarker[3][3];
        gameRules = new GameRules();
        new Gui(controller);
    }

    public void placeMark(int x, int y) {
        if (gameRules.isValidMove(x, y, gameBoard, player1.getPlayerMark(), player2.getPlayerMark())) {
            gameBoard[x][y] = currentPlayer.getPlayerMark();
        }
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else currentPlayer = player1;
    }

    public String checkWin() {
        switch (gameRules.isGameEnd(gameBoard, currentPlayer.getPlayerMark(), player1.getPlayerMark())) {
            case '1':
                player2Points++;
                currentPlayer = player2;
                 return "2 Won!";
            case '2':
                player1Points++;
                currentPlayer = player1;
                return "1 Won!";
            case 'D':
                return "Draw!";
            default:
                return "";
        }
    }

    public void newGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameBoard[i][j] = null;
            }
        }
    }

    public PlayerMarker giveUp() {
        if (currentPlayer == player1) {
            player2Points++;
            return player1.getPlayerMark();
        } else {
            player1Points++;
            return player2.getPlayerMark();
        }
    }

    private PlayerMarker[] changeOptionMarker(int playerXMarker) {
        switch (playerXMarker) {
            case 0:
                return new PlayerMarker[]{PlayerMarker.O, PlayerMarker.Y, PlayerMarker.Z};
            case 1:
                return new PlayerMarker[]{PlayerMarker.X, PlayerMarker.Y, PlayerMarker.Z};
            case 2:
                return new PlayerMarker[]{PlayerMarker.X, PlayerMarker.O, PlayerMarker.Z};
            case 3:
                return new PlayerMarker[]{PlayerMarker.X, PlayerMarker.O, PlayerMarker.Y};
        }
        return null;
    }

    private void createPlayer1(String name, String marker) {
        switch (marker) {
            case "X":
                player1 = new Player(name.trim(), PlayerMarker.X);
                break;
            case "O" :
                player1 = new Player(name.trim(), PlayerMarker.O);
                break;
            case "Y":
                player1 = new Player(name.trim(), PlayerMarker.Y);
                break;
            case "Z":
                player1 = new Player(name.trim(), PlayerMarker.Z);
                break;
        }
    }

    private void createPlayer2(String name, String marker) {
        switch (marker) {
            case "X":
                player2 = new Player(name.trim(), PlayerMarker.X);
                break;
            case "O":
                player2 = new Player(name.trim(), PlayerMarker.O);
                break;
            case "Y":
                player2 = new Player(name.trim(), PlayerMarker.Y);
                break;
            case "Z":
                player2 = new Player(name.trim(), PlayerMarker.Z);
                break;
        }
    }
}
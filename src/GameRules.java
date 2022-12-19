public class GameRules {

    public boolean isValidMove(int X, int Y, PlayerMarker[][] board, PlayerMarker player1, PlayerMarker player2) {
        return board[X][Y] != player1 && board[X][Y] != player2;
    }

    public char isGameEnd(PlayerMarker[][] board, PlayerMarker playerMark, PlayerMarker player1) {
        if (checkWinner(board)) {
            if (playerMark == player1) {
                return '1';
            } else return '2';
        } else if (isBoardFull(board)) {
            return 'D';
        } else return 'C';
    }

    private boolean isBoardFull(PlayerMarker[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkWinner(PlayerMarker[][] board) {
        return (board[0][0] != null && board[0][0] == board[0][1] && board[0][0] == board[0][2])
                || (board[1][0] != null && board[1][0] == board[1][1] && board[1][0] == board[1][2])
                || (board[2][0] != null && board[2][0] == board[2][1] && board[2][0] == board[2][2])
                || (board[0][0] != null && board[0][0] == board[1][0] && board[0][0] == board[2][0])
                || (board[0][1] != null && board[0][1] == board[1][1] && board[0][1] == board[2][1])
                || (board[0][2] != null && board[0][2] == board[1][2] && board[0][2] == board[2][2])
                || (board[0][0] != null && board[0][0] == board[1][1] && board[0][0] == board[2][2])
                || (board[0][2] != null && board[0][2] == board[1][1] && board[0][2] == board[2][0]);
    }
}

/* (0,0) (0,1) (0,2)
   (1,0) (1,1) (1,2)
   (2,0) (2,1) (2,2)
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Gui extends JFrame {

    private GameController controller;
    JPanel groundPanel = new JPanel(new BorderLayout());
    JPanel gamePanel = new JPanel(new GridLayout(3, 3));
    JPanel bottomPanel = new JPanel(new GridLayout(1, 2));
    JPanel topPanel = new JPanel(new GridLayout(2, 3));
    JLabel gameInfo = new JLabel("<----- Turn");
    JLabel player1name = new JLabel();
    JLabel player2name = new JLabel();
    JLabel player1Points = new JLabel();
    JLabel player2Points = new JLabel();
    JLabel empty = new JLabel(" ");
    JButton newGameButton = new JButton("New game");
    JButton giveUpButton = new JButton("Give up!");
    JButton button1 = new JButton();
    JButton button2 = new JButton();
    JButton button3 = new JButton();
    JButton button4 = new JButton();
    JButton button5 = new JButton();
    JButton button6 = new JButton();
    JButton button7 = new JButton();
    JButton button8 = new JButton();
    JButton button9 = new JButton();
    List<JButton> listOfButton = List.of(button1, button2, button3, button4, button5, button6, button7, button8, button9);
    Font fontInfo = new Font("Tahoma", Font.BOLD, 20);
    Font fontButton = new Font("Tahoma", Font.BOLD, 70);

    Gui(GameController controller) {
        this.controller = controller;
        player2name.setText("( " + controller.player2.getPlayerMark() + " ) " + controller.player2.getPlayerName());
        player1name.setText(controller.player1.getPlayerName() + " ( " + controller.player1.getPlayerMark() + " )");
        setPoints();
        gameInfo.setHorizontalAlignment(JLabel.CENTER);
        gameInfo.setFont(fontInfo);
        player2name.setFont(fontInfo);
        player1name.setFont(fontInfo);
        player2name.setHorizontalAlignment(JLabel.RIGHT);
        player1Points.setFont(fontInfo);
        player2Points.setFont((fontInfo));
        player2Points.setHorizontalAlignment(JLabel.RIGHT);

        for (JButton jp : listOfButton) {
            jp.setFont(fontButton);
            gamePanel.add(jp);
        }

        topPanel.add(player1name);
        topPanel.add(gameInfo);
        topPanel.add(player2name);
        topPanel.add(player1Points);
        topPanel.add(empty);
        topPanel.add(player2Points);

        bottomPanel.add(newGameButton);
        bottomPanel.add(giveUpButton);
        groundPanel.add(gamePanel, BorderLayout.CENTER);
        groundPanel.add(topPanel, BorderLayout.NORTH);
        groundPanel.add(bottomPanel, BorderLayout.SOUTH);
        add(groundPanel);

        button1.addActionListener(e -> {
            controller.placeMark(0, 0);
            updateBoard();
            checkWinner();
            button1.setEnabled(false);
        });
        button2.addActionListener(e -> {
            controller.placeMark(0, 1);
            updateBoard();
            checkWinner();
            button2.setEnabled(false);
        });
        button3.addActionListener(e -> {
            controller.placeMark(0, 2);
            updateBoard();
            checkWinner();
            button3.setEnabled(false);
        });
        button4.addActionListener(e -> {
            controller.placeMark(1, 0);
            updateBoard();
            checkWinner();
            button4.setEnabled(false);
        });
        button5.addActionListener(e -> {
            controller.placeMark(1, 1);
            updateBoard();
            checkWinner();
            button5.setEnabled(false);
        });
        button6.addActionListener(e -> {
            controller.placeMark(1, 2);
            updateBoard();
            checkWinner();
            button6.setEnabled(false);
        });
        button7.addActionListener(e -> {
            controller.placeMark(2, 0);
            updateBoard();
            checkWinner();
            button7.setEnabled(false);
        });
        button8.addActionListener(e -> {
            controller.placeMark(2, 1);
            updateBoard();
            checkWinner();
            button8.setEnabled(false);
        });
        button9.addActionListener(e -> {
            controller.placeMark(2, 2);
            updateBoard();
            checkWinner();
            button9.setEnabled(false);
        });

        newGameButton.addActionListener(e -> {
            for (JButton jb : listOfButton) {
                jb.setText("");
                jb.setBackground(null);
                jb.setEnabled(true);
                controller.newGame();
                giveUpButton.setEnabled(true);
            }
            if (controller.currentPlayer.getPlayerMark() == controller.player1.getPlayerMark()) {
                gameInfo.setText("Turn ----->");
                controller.currentPlayer = controller.player2;
            } else {
                gameInfo.setText("<----- Turn");
                controller.currentPlayer = controller.player1;
            }

        });

        giveUpButton.addActionListener(e -> {
            PlayerMarker whoGiveUp = controller.giveUp();
            giveUpButton.setEnabled(false);
            if (whoGiveUp == controller.player1.getPlayerMark()) {
                gameInfo.setText("WINNER! ----->");
                setPoints();
            } else {
                gameInfo.setText("<----- WINNER!");
                setPoints();
            }
        });

        setLocationRelativeTo(null);
        setTitle("Tic Tac Toe");
        setVisible(true);
        setSize(500, 500);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void updateBoard() {
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                listOfButton.get(counter).setText(String.valueOf(controller.gameBoard[i][j]));
                counter++;
            }
        }
        nullCheck();
    }

    private void checkWinner() {
        String win = controller.checkWin();
        if (win.equals("1 Won!")) {
            gameInfo.setText("<----- WINNER!");
            setPoints();
            colorWinningRow();
            giveUpButton.setEnabled(false);
            disableButtons();
        } else if (win.equals("2 Won!")) {
            gameInfo.setText("WINNER! ----->");
            setPoints();
            colorWinningRow();
            giveUpButton.setEnabled(false);
            disableButtons();
        } else if (win.equals("Draw!")) {
            gameInfo.setText("DRAW!");
            giveUpButton.setEnabled(false);
            disableButtons();
        } else {
            if (gameInfo.getText().equals("<----- Turn")) {
                gameInfo.setText("Turn ----->");
            } else gameInfo.setText("<----- Turn");
        }
    }

    private void colorWinningRow() {
        if (!button1.getText().equals("") && button1.getText().equals(button2.getText()) && button1.getText().equals(button3.getText())) {
            button1.setBackground(Color.GREEN);
            button2.setBackground(Color.GREEN);
            button3.setBackground(Color.GREEN);
        } else if (!button4.getText().equals("") && button4.getText().equals(button5.getText()) && button4.getText().equals(button6.getText())) {
            button4.setBackground(Color.GREEN);
            button5.setBackground(Color.GREEN);
            button6.setBackground(Color.GREEN);
        } else if (!button7.getText().equals("") && button7.getText().equals(button8.getText()) && button7.getText().equals(button9.getText())) {
            button7.setBackground(Color.GREEN);
            button8.setBackground(Color.GREEN);
            button9.setBackground(Color.GREEN);
        } else if (!button1.getText().equals("") && button1.getText().equals(button4.getText()) && button1.getText().equals(button7.getText())) {
            button1.setBackground(Color.GREEN);
            button4.setBackground(Color.GREEN);
            button7.setBackground(Color.GREEN);
        } else if (!button2.getText().equals("") && button2.getText().equals(button5.getText()) && button2.getText().equals(button8.getText())) {
            button2.setBackground(Color.GREEN);
            button5.setBackground(Color.GREEN);
            button8.setBackground(Color.GREEN);
        } else if (!button3.getText().equals("") && button3.getText().equals(button6.getText()) && button3.getText().equals(button9.getText())) {
            button3.setBackground(Color.GREEN);
            button6.setBackground(Color.GREEN);
            button9.setBackground(Color.GREEN);
        } else if (!button1.getText().equals("") && button1.getText().equals(button5.getText()) && button1.getText().equals(button9.getText())) {
            button1.setBackground(Color.GREEN);
            button5.setBackground(Color.GREEN);
            button9.setBackground(Color.GREEN);
        } else if (!button3.getText().equals("") && button3.getText().equals(button5.getText()) && button3.getText().equals(button7.getText())) {
            button3.setBackground(Color.GREEN);
            button5.setBackground(Color.GREEN);
            button7.setBackground(Color.GREEN);
        }
    }

    private void disableButtons() {
        for (JButton button : listOfButton) {
            button.setEnabled(false);
        }

    }

    private void nullCheck() {
        for (int i = 0; i < listOfButton.size(); i++) {
            if (listOfButton.get(i).getText().equals("null")) {
                listOfButton.get(i).setText("");
            }
        }
    }

    private void setPoints(){
        player1Points.setText("Points: " + controller.player1Points);
        player2Points.setText("Points: " + controller.player2Points);
    }
}
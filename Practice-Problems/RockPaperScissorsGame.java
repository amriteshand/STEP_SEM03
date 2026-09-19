import java.util.Random;

public class RockPaperScissorsGame {

    static String playRound(String playerMove, String computerMove) {

        if (playerMove.equals(computerMove)) {
            return "Draw";
        }

        if ((playerMove.equals("Rock") && computerMove.equals("Scissors"))
                || (playerMove.equals("Paper") && computerMove.equals("Rock"))
                || (playerMove.equals("Scissors") && computerMove.equals("Paper"))) {
            return "Player Wins";
        }

        return "Computer Wins";
    }

    public static void main(String[] args) {

        String[] moves = {"Rock", "Paper", "Scissors"};
        String[] playerMoves = {
                "Rock", "Paper", "Scissors", "Rock", "Paper"
        };

        Random random = new Random();

        int wins = 0;
        int losses = 0;
        int draws = 0;

        System.out.println(
                "Round | Player Move | Computer Move | Result");

        for (int i = 0; i < playerMoves.length; i++) {

            String playerMove = playerMoves[i];
            String computerMove =
                    moves[random.nextInt(3)];

            String result =
                    playRound(playerMove, computerMove);

            if (result.equals("Player Wins")) {
                wins++;
            } else if (result.equals("Computer Wins")) {
                losses++;
            } else {
                draws++;
            }

            System.out.println(
                    (i + 1) + " | " + playerMove
                    + " | " + computerMove
                    + " | " + result);
        }

        double winPercentage =
                (wins * 100.0) / playerMoves.length;

        System.out.println(
                "Wins: " + wins
                + " | Losses: " + losses
                + " | Draws: " + draws
                + " | Win % = "
                + String.format("%.1f", winPercentage) + "%");
    }
}
public class RuleBasedSystem {

  public static void main(String[] args) {

      StrikePrediction sp = new StrikePrediction();
      sp.Initialize();

      System.out.println("Prediction for Punch: " + sp.ProcessMove("Punch"));
      System.out.println("Prediction for LowKick: " + sp.ProcessMove("LowKick"));

      String[] moves = {"Punch", "LowKick", "LowKick", "LowKick", "LowKick", "LowKick"};

      int successfulPredictions = testMoves(sp, moves);

      System.out.println("Number of successful predictions: " + successfulPredictions);
  }

  private static int testMoves(StrikePrediction sp, String[] moves) {
      int successfulPredictions = 0;

      for (String move : moves) {
          if (sp.ProcessMove(move).equals(move)) {
              System.out.println("Well done!");
              successfulPredictions++;
          } else {
              System.out.println("Try better next time!");
          }
      }

      return successfulPredictions;
  }
}

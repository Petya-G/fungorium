package tecton;

public class ThreadConsumingTecton extends Tecton {
  
  /**
   * Minden kör végén meghívódik
   */
  @Override
  public void endRound() {
    threads.clear();
  }
}

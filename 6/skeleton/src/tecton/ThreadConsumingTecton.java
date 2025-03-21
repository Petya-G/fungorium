package tecton;

public class ThreadConsumingTecton extends Tecton {
  @Override
  public void endRound() {
    threads.clear();
  }
}

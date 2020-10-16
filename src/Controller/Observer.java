package Controller;

public interface Observer {
    void update();
    void notifyGameOver();
    void notifyRoundOver();
    void notifyGameWon();
}

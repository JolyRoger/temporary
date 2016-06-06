package org.torquemada.q;

/**
 * Created by torquemada on 6/2/16.
 */
public interface IBoard {
    void init();
    void assignLevel(int[] dimension, int[] levelData);
    void select(int id, boolean select);
    void selectToMove(int selectedId);
    boolean isReadyToMove(int selectedId);
    void moveBall(int from, int to);
    void clearLevel();
}

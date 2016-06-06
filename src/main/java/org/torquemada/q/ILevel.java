package org.torquemada.q;

/**
 * Created by torquemada on 6/2/16.
 */
public interface ILevel {
    void setDimension(int[] dimension);
    void setLevelData(int[] levelData);
    void select(int id, boolean select);
    void init();
    void selectToMove(int selectedId);
    void moveBall(int from, int to);
    void removeBall(int from);
    boolean isReadyToMove(int selectedId);
}

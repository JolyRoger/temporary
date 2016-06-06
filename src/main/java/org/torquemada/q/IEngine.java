package org.torquemada.q;

/**
 * Created by torquemada on 6/2/16.
 */
public interface IEngine {
    void run();
    void notifySelect(int id);
    void notifySelectNext(boolean next);
    void notifySelectToMove();

    void notifyLeft();
    void notifyUp();
    void notifyRight();
    void notifyDown();
}

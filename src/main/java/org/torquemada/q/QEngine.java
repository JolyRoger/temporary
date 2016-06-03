package org.torquemada.q;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by torquemada on 6/2/16.
 */
public class QEngine implements IEngine {

    private int selectedId;
    private int[] dimension = {6, 6};
    private int [] levelData = {
            1, 1, 33, 1, 1, 1,
            1, 3, 0, 0, 0, 1,
            1, 0, 0, 0, 0, 44,
            1, 0, 0, 0, 0, 1,
            1, 1, 0, 4, 0, 1,
            0, 1, 1, 1, 1, 0
    };

    private ArrayList<Integer> balls = new ArrayList<>();
    private ArrayList<Integer> looses = new ArrayList<>();

    @Autowired
    private IBoard board;

    @Override
    public void run() {
        for (int i = 0; i < levelData.length; i++) {
            if (isBall(levelData[i])) balls.add(i);
            if (isLoose(levelData[i])) looses.add(i);
        }

        EventQueue.invokeLater(() -> {
            board.assignLevel(dimension, levelData);
            board.initUI();
            notifySelect(balls.get(0));
        });
    }

    @Override
    public void notifySelect(int id) {
        selectedId = id;
        for (Integer ballId : balls) {
            board.select(ballId, ballId == id);
        }
    }

    @Override
    public void notifySelectNext(boolean next) {
        for (int i = 0; i < balls.size(); i++) {
            if (selectedId == balls.get(i)) {
                if (next) {
                    notifySelect(balls.get((i+1) % balls.size()));
                    break;
                } else {
                    notifySelect(balls.get((balls.size() + (i-1)) % balls.size()));
                    break;
                }
            }
        }
    }

    @Override
    public void notifySelectToMove() {
        board.selectToMove(selectedId);
    }

    private boolean isBall(int i) {
        return i>1 && i<10;
    }
    private boolean isLoose(int i) {
        return i>10 && i%11 == 0;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        IEngine qEngine = context.getBean(IEngine.class);
        qEngine.run();
    }
}

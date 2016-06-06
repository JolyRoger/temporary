package org.torquemada.q;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.awt.*;
import java.util.ArrayList;
import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;

/**
 * Created by torquemada on 6/2/16.
 */
public class QEngine implements IEngine {

    private int selectedId;
    private boolean selectedToMove;
    private int currentLevel = 1;
    private int[] dimension;
    private int [] levelData;

    private ArrayList<Integer> balls = new ArrayList<>();
    private ArrayList<Integer> looses = new ArrayList<>();

    @Autowired
    private IBoard board;

    @Override
    public void run() {
        board.init();
        setNewLevel();
    }

    private void setNewLevel() {
        Resources.LevelData data = Resources.getLevelData(currentLevel);
        levelData = data.levelData;
        dimension = data.dimension;

        for (int i = 0; i < levelData.length; i++) {
            if (isBall(levelData[i])) balls.add(i);
            if (isLoose(levelData[i])) looses.add(i);
        }


        board.assignLevel(dimension, levelData);
        notifySelect(balls.get(0));
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
        selectedToMove = board.isReadyToMove(selectedId);
    }

    @Override
    public void notifyLeft() {
        if (selectedToMove) {
            roll(selectedId-1, i -> i / dimension[0] == selectedId / dimension[0], i -> --i);
        } else {
            notifySelectNext(false);
        }
    }

    @Override
    public void notifyUp() {
        if (selectedToMove) {
            roll(selectedId - dimension[0], i -> i >= 0, i -> i - dimension[0]);
        } else {
            notifySelectNext(false);
        }
    }

    @Override
    public void notifyRight() {
        if (selectedToMove) {
            roll(selectedId+1, i -> i / dimension[0] == selectedId / dimension[0], i -> ++i);
        } else {
            notifySelectNext(true);
        }
    }

    @Override
    public void notifyDown() {
        if (selectedToMove) {
            roll(selectedId + dimension[0], i -> i < levelData.length, i -> i + dimension[0]);
        } else {
            notifySelectNext(true);
        }
    }

    private void roll(int initial, IntPredicate cond, IntUnaryOperator step) {
        int from = selectedId;
        int to = selectedId;

        for (int i = initial; cond.test(i); i = step.applyAsInt(i)) {
            if (levelData[i] == 0) { to = i; continue; }
            if (levelData[i] == levelData[selectedId] * 11) {
                to = i;
                ballInLoose(from, to);
                return;
            }
            break;
        }
        levelData[from] += levelData[to];
        levelData[to] = levelData[from] - levelData[to];
        levelData[from] = levelData[from] - levelData[to];
        board.moveBall(from, to);
        selectedId = to;
        balls.remove((Integer) from);
        balls.add(to);
    }

    private void ballInLoose(int from, int to) {
        levelData[from] = 0;
        balls.remove((Integer) from);
        board.moveBall(from, -1);
        if (balls.size() > 0) {
            selectedToMove = false;
            notifySelect(balls.get(0));
        } else {
            win();
        }
    }

    private void win() {
        clearLevel();
        selectedToMove = false;
        currentLevel++;
        setNewLevel();
    }

    private void clearLevel() {
        board.clearLevel();
    }

    private boolean isBall(int i) {
        return i>1 && i<10;
    }
    private boolean isLoose(int i) {
        return i>10 && i%11 == 0;
    }

    public static void main(String[] args) {
        Resources.loadResources();
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        IEngine qEngine = context.getBean(IEngine.class);
        EventQueue.invokeLater(qEngine::run);
    }
}

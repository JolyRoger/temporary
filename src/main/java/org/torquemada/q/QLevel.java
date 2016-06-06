package org.torquemada.q;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;
import org.torquemada.q.squares.*;

import javax.swing.*;
import java.awt.*;

/**
 * Created by torquemada on 5/28/16.
 */
@Component
public class QLevel extends JPanel implements ILevel {

    private int[] dimension;
    private int[] levelData;
    private Square[] squares;

    @Autowired
    private IBoard board;

    @Override
    public void init() {
        GridLayout layout = new GridLayout(dimension[1], dimension[0]);
        setLayout(layout);
        setSize(new Dimension(dimension[0] * 64, dimension[1] * 64));

        squares = new Square[levelData.length];

        for (int i = 0; i < squares.length; i++) {
            squares[i] = create(i);
            add(squares[i]);
        }
    }

    @Lookup public Empty empty() { return null; }
    @Lookup public Solid solid() { return null; }
    @Lookup public Ball ball() { return null; }
    @Lookup public Loose loose() { return null; }

    private Square create(int i) {
        int id = levelData[i];
        SquareType type = SquareType.getType(id);
        switch (type) {
            case empty : return empty().withId(i);
            case solid : return solid().withId(i);

            case blueball : return ball().withColor(ValidColor.BLUE).withId(i);
            case redball: return ball().withColor(ValidColor.RED).withId(i);
            case whiteball: return ball().withColor(ValidColor.WHITE).withId(i);
            case yellowball: return ball().withColor(ValidColor.YELLOW).withId(i);
            case greenball: return  ball().withColor(ValidColor.GREEN).withId(i);
            case orangeball: return ball().withColor(ValidColor.ORANGE).withId(i);

            case blueloose : return loose().withColor(ValidColor.BLUE).withId(i);
            case redloose : return loose().withColor(ValidColor.RED).withId(i);
            case whiteloose : return loose().withColor(ValidColor.WHITE).withId(i);
            case yellowloose : return loose().withColor(ValidColor.YELLOW).withId(i);
            case greenloose : return loose().withColor(ValidColor.GREEN).withId(i);
            case orangeloose : return loose().withColor(ValidColor.ORANGE).withId(i);
        }
        return empty();
    }

    @Override
    public void setDimension(int[] dimension) {
        this.dimension = dimension;
    }

    @Override
    public void setLevelData(int[] levelData) {
        this.levelData = levelData;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    @Override
    public void select(int id, boolean select) {
        Ball ball = ((Ball) squares[id]);
        ball.select(select);
        ball.repaint();
    }

    @Override
    public void selectToMove(int selectedId) {
        Ball ball = ((Ball) squares[selectedId]);
        ball.selectToMove();
        ball.repaint();
    }

    @Override
    public void removeBall(int from) {
        remove(from);
        add(create(from), from);
        revalidate();
    }

    public void moveBall(int from, int to) {
        Square sq = squares[from];
        squares[from] = squares[to];
        squares[to] = sq;

        java.awt.Component fromComponent = getComponent(from);
        java.awt.Component toComponent = getComponent(to);
        add(fromComponent, to);
        add(toComponent, from);

        revalidate();
    }

    @Override
    public boolean isReadyToMove(int selectedId) {
        return ((Ball) squares[selectedId]).isReadyToMove();
    }

    @Override
    public void clear() {
        removeAll();
    }

    private void printSquares() {
        for (int i = 0; i < squares.length; i++) {
            if (i % 6 == 0) System.out.println();
            Square sq = squares[i];
            System.out.print(sq.getClass().getSimpleName() + "\t");
        }
        System.out.println();
    }
}

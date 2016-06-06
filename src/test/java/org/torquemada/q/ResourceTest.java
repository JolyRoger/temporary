package org.torquemada.q;

import org.junit.Test;

import static junit.framework.TestCase.*;

/**
 * Created by torquemada on 6/6/16.
 */
public class ResourceTest {

    @Test
    public void testLoadResources() {
        Resources.loadResources();
        assertTrue (Resources.levelMap.size() == 59);
        Resources.LevelData data = Resources.getLevelData(1);
        System.out.println(data.levelData.length);
        System.out.print(data.dimension.length);
        System.out.println(": " + data.dimension[0] + ", " + data.dimension[1]);

        for (int i = 0; i < data.dimension[0] * data.dimension[1]; i++) {
            if (i % data.dimension[1] == 0)
                System.out.println();
            System.out.print(data.levelData[i] + " ");
        }
    }
}

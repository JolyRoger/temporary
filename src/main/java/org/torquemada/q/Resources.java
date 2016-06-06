package org.torquemada.q;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by torquemada on 6/5/16.
 */
public class Resources {

    static HashMap<Integer, LevelData> levelMap = new HashMap<>();

    public static class LevelData {
        int[] levelData;
        int[] dimension;
        int number;

        LevelData(int number, int[] levelData, int[] dimension) {
            this.levelData = levelData;
            this.dimension = dimension;
            this.number = number;
        }
    }

    static void loadResources() {
        List<String> stringLevels = null;

        try {
            stringLevels = FileUtils.readLines(new File("src/main/resources/standart.lev"), "UTF-8");
        } catch (IOException e) {
            System.err.println("Can't load levels from standard.lev");
            System.exit(1);
        }

        boolean readLevelNumber;
        boolean readDimension = false;
        boolean readLevelData = false;

        int currentNumber = 0;
        int stringLevelDataNumber = 0;
        int[] currentDimension = new int[2];
        int[] currentLevelData = null;
        String[] currentSplitData;

        int j = 0;
        for (String stringLevel : stringLevels) {
            readLevelNumber = stringLevel.startsWith("%");

            if (readLevelNumber) {
                if (readLevelData) {
                    readLevelData = false;
                    levelMap.put(currentNumber, new LevelData(currentNumber, currentLevelData, currentDimension.clone()));
                }
                currentNumber = Integer.parseInt(stringLevel.replace('%', ' ').trim());
                stringLevelDataNumber = 0;
                readDimension = true;
                continue;
            }
            if (readDimension) {
                currentSplitData = stringLevel.split("\\s");
                currentDimension[0] = Integer.parseInt(currentSplitData[0]);
                currentDimension[1] = Integer.parseInt(currentSplitData[1]);
                currentLevelData = new int[currentDimension[0] * currentDimension[1]];
                readDimension = false;
                readLevelData = true;
                continue;
            }
            if (readLevelData) {
                currentSplitData = stringLevel.split("\\s");
                int i = currentDimension[0] * stringLevelDataNumber++;
                for (int loop = i; loop < i + currentSplitData.length; loop++) {
                    currentLevelData[loop] = Integer.parseInt(currentSplitData[loop - i]);
                }
            }
        }
    }

    public static LevelData getLevelData(int number) {
        return levelMap.get(number);
    }
}

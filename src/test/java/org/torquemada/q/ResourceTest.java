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
    }
}

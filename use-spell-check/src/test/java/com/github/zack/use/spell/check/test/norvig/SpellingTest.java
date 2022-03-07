package com.github.zack.use.spell.check.test.norvig;

import com.github.zack.use.spell.check.norvig.Spelling;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * @author zhouze
 * @date 2022/3/7
 */
public class SpellingTest {
    static final Logger logger = Logger.getLogger(SpellingTest.class.getName());

    @Order(1)
    @DisplayName("test spelling")
    @Test
    void spellingTest() throws IOException {
        long start = System.currentTimeMillis();
        String arg = "bocsh";
        String correct = new Spelling(Objects.requireNonNull(getClass().getClassLoader().getResource("bigArticle.txt")).getFile())
                .correct(arg);

        System.out.println(correct);
        System.out.println("Done in " + (System.currentTimeMillis() - start)/1000 + "s ");
    }
}

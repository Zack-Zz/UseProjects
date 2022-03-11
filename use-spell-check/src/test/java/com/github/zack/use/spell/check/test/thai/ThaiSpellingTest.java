package com.github.zack.use.spell.check.test.thai;

import com.github.zack.use.spell.check.WordMap;
import com.github.zack.use.spell.check.thai.ThaiSpelling;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

/**
 * @author zhouze
 * @date 2022/3/9
 */
public class ThaiSpellingTest {

    @Order(1)
    @DisplayName("test edits function")
    @Test
    void editsTest() throws IOException {
        long start = System.currentTimeMillis();
        String arg = "เซ็นเซอร์วัดแรงดัน";
        List<String> editWords = new ThaiSpelling(null)
                .edits(arg);

        System.out.println(editWords.size());
        System.out.println(editWords);

        System.out.println("Done in " + (System.currentTimeMillis() - start) / 1000 + "s ");
    }

    @Order(2)
    @DisplayName("test correct function")
    @Test
    void correctTest() throws IOException {
        long start = System.currentTimeMillis();

        System.out.println(start / 1000);

        WordMap words = new WordMap();
        words.put("เซ็นเซอร์อากาศ", (short) 1);
        words.put("เซนเซอร์วัดอุณหภูมิน้ำ", (short) 1);
        words.put("เซ็นเซอร์วัดลมยาง", (short) 1);
        words.put("เซนเซอร์วัดระดับกันชน", (short) 1);
        words.put("เซ็นเซอร์วัดแรงดัน", (short) 1);

        ThaiSpelling thaiSpelling = new ThaiSpelling(words);

        String arg = "เซ็นเซอร์วัดแรงนน";
        String correct = thaiSpelling
                .correct(arg);

        System.out.println(correct);

        String arg1 = "เซ็นเซอร์วัดแรนงดั";
        String correct1 = thaiSpelling
                .correct(arg1);

        System.out.println(correct1);

        long end = System.currentTimeMillis();
        System.out.println(end / 1000);
        System.out.println("Done in " + (end - start) / 1000 + "s ");
    }
}

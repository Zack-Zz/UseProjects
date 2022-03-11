package com.github.zack.use.spell.check.test.norvig;

import com.github.zack.use.spell.check.WordMap;
import com.github.zack.use.spell.check.norvig.EnglishSpelling;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * @author zhouze
 * @date 2022/3/7
 */
public class EnSpellingTest {
    static final Logger logger = Logger.getLogger(EnSpellingTest.class.getName());

    @Order(1)
    @DisplayName("test spelling")
    @Test
    void spellingTest() throws IOException {
        final WordMap nWords = new WordMap();
        File f = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("bigArticle.txt")).getFile());
        FileReader in = new FileReader(f);
        char[] buffer = new char[(int)f.length()];
        in.read(buffer);
        int begin = 0;
        boolean isUpper = false;
        for (int i = 0; i < buffer.length; i++) {
            while ((('a' > buffer[i] || buffer[i] > 'z') && ('A' > buffer[i] || buffer[i] > 'Z')) && i < buffer.length - 1 ) i++;
            begin = i;
            while ((('a' <= buffer[i] && buffer[i] <= 'z') || (isUpper = ('A' <= buffer[i] && buffer[i] <='Z'))) && i < (buffer.length-1)){
                if(isUpper) buffer[i] = Character.toLowerCase(buffer[i]);
                i++;
            }
            String word  = new String(buffer, begin, i - begin);
            nWords.put(word, (short) (nWords.get(word) + 1));
        }
        in.close();


        long start = System.currentTimeMillis();
        String arg = "bocsh";
        String correct = new EnglishSpelling(nWords)
                .correct(arg);

        System.out.println(correct);
        System.out.println("Done in " + (System.currentTimeMillis() - start)/1000 + "s ");
    }
}

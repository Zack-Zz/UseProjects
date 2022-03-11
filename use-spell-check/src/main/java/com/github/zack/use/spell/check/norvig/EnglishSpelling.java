package com.github.zack.use.spell.check.norvig;

import com.github.zack.use.spell.check.AbstractSpellingTemplate;
import com.github.zack.use.spell.check.WordMap;

/**
 * @author zhouze
 * @date 2022/3/7
 */
public class EnglishSpelling extends AbstractSpellingTemplate {

    static char[] letters = new char[]{
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
            'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
            't', 'u', 'v', 'w', 'x', 'y', 'z'
    };

    public EnglishSpelling(WordMap wordMap) {
        super(wordMap);
    }

    @Override
    protected char[] getLetterArray() {
        return new char[0];
    }


}

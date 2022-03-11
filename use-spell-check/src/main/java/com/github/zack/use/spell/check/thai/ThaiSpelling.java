package com.github.zack.use.spell.check.thai;

import com.github.zack.use.spell.check.AbstractSpellingTemplate;
import com.github.zack.use.spell.check.WordMap;

/**
 * @author zhouze
 * @date 2022/3/7
 */
public class ThaiSpelling extends AbstractSpellingTemplate {

    static char[] letters = new char[]{
            'ก', 'ข', 'ฃ', 'ค', 'ฅ', 'ฆ', 'ง', 'จ', 'ฉ',
            'ช', 'ซ', 'ฌ', 'ญ', 'ฎ', 'ฏ', 'ฐ', 'ฑ', 'ฒ', 'ณ',
            'ด', 'ต', 'ถ', 'ท', 'ธ', 'น', 'บ', 'ป', 'ผ', 'ฝ',
            'พ', 'ฟ', 'ภ', 'ม', 'ย', 'ร', 'ฤ', 'ล', 'ฦ', 'ว',
            'ศ', 'ษ', 'ส', 'ห', 'ฬ', 'อ', 'ฮ', 'ฯ', 'ะ', 'ั',
            'า', 'ำ', 'ิ', 'ี', 'ึ', 'ื', 'ุ', 'ู', 'ฺ',
            '฿', 'เ', 'แ', 'โ', 'ใ', 'ไ', 'ๅ',
            'ๆ', '็', '่', '้', '๊', '๋', '์', 'ํ', '๎', '๏',
            '๐', '๑', '๒', '๓', '๔', '๕', '๖', '๗', '๘', '๙',
            '๚', '๛'
    };

    public ThaiSpelling(WordMap wordMap) {
        super(wordMap);
    }

    @Override
    protected char[] getLetterArray() {
        return letters;
    }

}

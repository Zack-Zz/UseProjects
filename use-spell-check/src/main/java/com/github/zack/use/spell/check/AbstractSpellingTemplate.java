package com.github.zack.use.spell.check;

import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;

/**
 * template
 *
 * @author zhouze
 * @date 2022/3/9
 */
public abstract class AbstractSpellingTemplate {

    private final WordMap dictionaryWords;

    public AbstractSpellingTemplate(WordMap wordMap) {
        this.dictionaryWords = wordMap;
    }

    public WordMap getDictionaryWords() {
        return dictionaryWords;
    }

    /**
     * correct
     *
     * @param word
     * @return
     */
    public String correct(String word) {
        if (dictionaryWords.containsKey(word)) {
            return word;
        }
        List<String> list = edits(word);
        IdentityHashMap<Short, String> candidates = new IdentityHashMap<>();
        for (String s : list) {
            if (dictionaryWords.containsKey(s)) {
                candidates.put(dictionaryWords.get(s), s);
            }
        }

        if (candidates.size() > 0) {
            return candidates.get(Collections.max(candidates.keySet()));
        }
        for (String s : list) {
            for (String w : edits(s)) {
                if (dictionaryWords.containsKey(w)) {
                    candidates.put(dictionaryWords.get(w), w);
                }
            }
        }

        return candidates.size() > 0 ? candidates.get(Collections.max(candidates.keySet())) : word;
    }

    /**
     * edits words
     *
     * @param word
     * @return
     */
    public List<String> edits(String word) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < word.length(); ++i) {
            result.add(word.substring(0, i) + word.substring(i + 1));
        }
        for (int i = 0; i < word.length() - 1; ++i) {
            result.add(word.substring(0, i) + word.charAt(i + 1) + word.charAt(i) + word.substring(i + 2));
        }
        for (int i = 0; i < word.length(); ++i) {
            for (char c : getLetterArray()) {
                result.add(word.substring(0, i) + c + word.substring(i + 1));
            }
        }
        for (int i = 0; i <= word.length(); ++i) {
            for (char c : getLetterArray()) {
                result.add(word.substring(0, i) + c + word.substring(i));
            }
        }
        return result;
    }

    /**
     * get first letter
     *
     * @return
     */
    protected abstract char[] getLetterArray();

}

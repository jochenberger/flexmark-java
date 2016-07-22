package com.vladsch.flexmark.ext.gfm.strikethrough.internal;

import com.vladsch.flexmark.ext.gfm.strikethrough.Strikethrough;
import com.vladsch.flexmark.internal.Delimiter;
import com.vladsch.flexmark.internal.util.sequence.SubSequence;
import com.vladsch.flexmark.parser.delimiter.DelimiterProcessor;
import com.vladsch.flexmark.parser.delimiter.DelimiterRun;

public class StrikethroughDelimiterProcessor implements DelimiterProcessor {

    @Override
    public char getOpeningCharacter() {
        return '~';
    }

    @Override
    public char getClosingCharacter() {
        return '~';
    }

    @Override
    public int getMinLength() {
        return 2;
    }

    @Override
    public int getDelimiterUse(DelimiterRun opener, DelimiterRun closer) {
        if (opener.length() >= 2 && closer.length() >= 2) {
            // Use exactly two delimiters even if we have more, and don't care about internal openers/closers.
            return 2;
        } else {
            return 0;
        }
    }

    @Override
    public void process(Delimiter opener, Delimiter closer, int delimiterUse) {
        // wrap nodes between delimiters in strikethrough.
        Strikethrough strikethrough = new Strikethrough(opener.getTailChars(delimiterUse), SubSequence.NULL, closer.getLeadChars(delimiterUse));
        opener.moveNodesBetweenDelimitersTo(strikethrough, closer);
    }
}

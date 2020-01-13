package org.wahlzeit.extension;

import java.lang.annotation.Repeatable;

public @interface PatternInstance {
    String patternName();
    String[] participants();
}

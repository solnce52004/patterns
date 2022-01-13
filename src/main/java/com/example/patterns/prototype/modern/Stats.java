package com.example.patterns.prototype.modern;

import java.util.Date;


public interface Stats<S> {
    S add(S delta);

    S invert(Date forDate);
}

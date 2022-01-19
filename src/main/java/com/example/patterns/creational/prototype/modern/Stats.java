package com.example.patterns.creational.prototype.modern;

import java.util.Date;


public interface Stats<S> {
    S add(S delta);

    S invert(Date forDate);
}

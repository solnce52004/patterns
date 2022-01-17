package com.example.patterns.adapter.v2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Adapter2 {
    public static void main(String[] args) {
        //отверстие
        RoundHole hole = new RoundHole(5);

        //колышки
        RoundPeg smallRPeg = new RoundPeg(5);//круглый
        SquarePeg smallSqPeg = new SquarePeg(2);//квадратный
        SquarePeg largeSqPeg = new SquarePeg(20);//квадратный

        SquarePegAdapter ssAdapted = new SquarePegAdapter(smallSqPeg);
        SquarePegAdapter lsAdapted = new SquarePegAdapter(largeSqPeg);

        log.info(hole.fits(smallRPeg));// Круглое к круглому и диаметр совпал — true.
        // hole.fits(ssPeg); // Не скомпилируется.
        log.info(hole.fits(ssAdapted));//диаметр совпал
        log.info(hole.fits(lsAdapted));//диаметр НЕ совпал (но хотя бы скомпилируется)
    }
}

//Адаптер позволяет использовать КвадратныеКолышки и КруглыеОтверстия вместе.
@AllArgsConstructor
class SquarePegAdapter extends RoundPeg {
    private final SquarePeg peg;

    @Override
    public double getRadius() {
        // Рассчитываем минимальный радиус, в который пролезет этот колышек.
        return (Math.sqrt(Math.pow((peg.getWidth() / 2), 2) * 2));
    }
}

/**
 * КвадратныеКолышки несовместимы с Круглыми Отверстиями (они остались в проекте
 * после бывших разработчиков). Но мы должны как-то интегрировать их в нашу
 * систему.
 */
@AllArgsConstructor
@Getter
class SquarePeg {
    private final double width;

    public double getSquare() {
        return Math.pow(this.width, 2);
    }
}

//КруглыеКолышки совместимы с КруглымиОтверстиями.
@AllArgsConstructor
@NoArgsConstructor
@Getter
class RoundPeg {
    private double radius;
}

//КруглоеОтверстие совместимо с КруглымиКолышками.
@AllArgsConstructor
@Getter
class RoundHole {
    private final double radius;

    public boolean fits(RoundPeg peg) {
        return (this.getRadius() >= peg.getRadius());
    }
}


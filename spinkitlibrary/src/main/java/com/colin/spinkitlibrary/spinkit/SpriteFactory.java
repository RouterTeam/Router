package com.colin.spinkitlibrary.spinkit;

import com.colin.spinkitlibrary.spinkit.sprite.Sprite;
import com.colin.spinkitlibrary.spinkit.style.ChasingDots;
import com.colin.spinkitlibrary.spinkit.style.Circle;
import com.colin.spinkitlibrary.spinkit.style.CubeGrid;
import com.colin.spinkitlibrary.spinkit.style.DoubleBounce;
import com.colin.spinkitlibrary.spinkit.style.FadingCircle;
import com.colin.spinkitlibrary.spinkit.style.FoldingCube;
import com.colin.spinkitlibrary.spinkit.style.MultiplePulse;
import com.colin.spinkitlibrary.spinkit.style.MultiplePulseRing;
import com.colin.spinkitlibrary.spinkit.style.Pulse;
import com.colin.spinkitlibrary.spinkit.style.PulseRing;
import com.colin.spinkitlibrary.spinkit.style.RotatingCircle;
import com.colin.spinkitlibrary.spinkit.style.RotatingPlane;
import com.colin.spinkitlibrary.spinkit.style.ThreeBounce;
import com.colin.spinkitlibrary.spinkit.style.WanderingCubes;
import com.colin.spinkitlibrary.spinkit.style.Wave;

/**
 * Created by ybq.
 */
public class SpriteFactory {

    public static Sprite create(Style style) {
        Sprite sprite = null;
        switch (style) {
            case ROTATING_PLANE:
                sprite = new RotatingPlane();
                break;
            case DOUBLE_BOUNCE:
                sprite = new DoubleBounce();
                break;
            case WAVE:
                sprite = new Wave();
                break;
            case WANDERING_CUBES:
                sprite = new WanderingCubes();
                break;
            case PULSE:
                sprite = new Pulse();
                break;
            case CHASING_DOTS:
                sprite = new ChasingDots();
                break;
            case THREE_BOUNCE:
                sprite = new ThreeBounce();
                break;
            case CIRCLE:
                sprite = new Circle();
                break;
            case CUBE_GRID:
                sprite = new CubeGrid();
                break;
            case FADING_CIRCLE:
                sprite = new FadingCircle();
                break;
            case FOLDING_CUBE:
                sprite = new FoldingCube();
                break;
            case ROTATING_CIRCLE:
                sprite = new RotatingCircle();
                break;
            case MULTIPLE_PULSE:
                sprite = new MultiplePulse();
                break;
            case PULSE_RING:
                sprite = new PulseRing();
                break;
            case MULTIPLE_PULSE_RING:
                sprite = new MultiplePulseRing();
                break;
            default:
                break;
        }
        return sprite;
    }
}

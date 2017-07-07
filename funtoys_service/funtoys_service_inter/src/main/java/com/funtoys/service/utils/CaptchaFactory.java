package com.funtoys.service.utils;

import com.github.bingoohuang.patchca.color.RandomColorFactory;
import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;
import com.github.bingoohuang.patchca.filter.FilterFactory;
import com.github.bingoohuang.patchca.filter.predefined.*;
import com.github.bingoohuang.patchca.word.RandomWordFactory;

import java.util.List;

/**
 * Created by jun_h on 2017/7/7.
 */
public class CaptchaFactory {
    public static void instanceConfigurableCaptcha(ConfigurableCaptchaService cs) {
        cs.setColorFactory(new RandomColorFactory());
        RandomWordFactory wf = new RandomWordFactory();
        wf.setCharacters("1234567890");
        wf.setMaxLength(4);
        wf.setMinLength(4);
        cs.setWordFactory(wf);
    }

    public static void instanceFilterFactory(List<FilterFactory> factories, ConfigurableCaptchaService cs) {
        factories.add(new CurvesRippleFilterFactory(cs.getColorFactory()));
        factories.add(new MarbleRippleFilterFactory());
        factories.add(new DoubleRippleFilterFactory());
        factories.add(new WobbleRippleFilterFactory());
        factories.add(new DiffuseRippleFilterFactory());
    }

}

package com.bob.rules.config;

import org.drools.compiler.kproject.ReleaseIdImpl;
import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by bob on 16/8/10.
 */
@Configuration
public class DynamicruleConfig {

    /**
     * 动态规则
     *
     * @return
     */
    @Bean(name = "billKieContainer")
    public KieContainer billKieContainer() {
        ReleaseIdImpl releaseId = new ReleaseIdImpl("com.u51.finance", "droolsruleconfig-billdata", "0.0.1-SNAPSHOT");
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.newKieContainer(releaseId);
        KieScanner kieScanner = ks.newKieScanner(kContainer);
        kieScanner.start(5 * 1000l);
        return kContainer;
    }
}
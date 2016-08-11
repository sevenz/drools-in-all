package com.bob.rules;

import com.bob.rules.reguralrule.Applicant;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by bob on 16/8/10.
 */
public class ApplicantTest {

    @Test
    public void testAge() throws Exception {

        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        StatelessKieSession kieSession = kieContainer.newStatelessKieSession();
        Applicant applicant = new Applicant("abcd", 12, true);
        assertTrue(applicant.isValid());
        kieSession.execute(applicant);
        kieSession.execute(kieServices.getCommands().newInsert(applicant));
        assertFalse(applicant.isValid());
    }
}
package com.bob.rules;

import com.bob.rules.reguralrule.Account;
import com.bob.rules.reguralrule.AccountPeriod;
import com.bob.rules.reguralrule.CashFlow;
import com.bob.rules.reguralrule.CashFlowType;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by bob on 16/8/10.
 */
public class CashFlowTest {

    @Test
    public void testName() throws Exception {

        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession ksession = kieContainer.newKieSession();

        AccountPeriod acp = new AccountPeriod(date("2013-01-01"), date("2013-03-31"));

        Account ac = new Account(1, 0);

        CashFlow cf1 = new CashFlow(date("2013-01-12"), 100, CashFlowType.CREDIT, 1);
        CashFlow cf2 = new CashFlow(date("2013-02-2"), 200, CashFlowType.DEBIT, 1);
        CashFlow cf3 = new CashFlow(date("2013-05-18"), 50, CashFlowType.CREDIT, 1);
        CashFlow cf4 = new CashFlow(date("2013-03-07"), 75, CashFlowType.CREDIT, 1);

        FactHandle fh = ksession.insert(acp);
        ksession.insert(ac);

        ksession.insert(cf1);
        ksession.insert(cf2);
        ksession.insert(cf3);
        ksession.insert(cf4);

        ksession.fireAllRules();

        acp.setStart(date("2013-04-01"));
        acp.setSend(date("2013-06-31"));
        ksession.update(fh, acp);

        ksession.fireAllRules();
    }

    private Date date(String str) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(str);
    }
}
package com.bob.rules.reguralrule;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * 现金流水
 */
@Data
@AllArgsConstructor
public class CashFlow {

    private Date date;

    private double amount;

    private CashFlowType type;

    private long accountNo;
}
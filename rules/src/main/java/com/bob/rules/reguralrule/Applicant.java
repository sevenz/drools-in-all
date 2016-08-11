package com.bob.rules.reguralrule;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by bob on 16/8/10.
 */
@Data
@AllArgsConstructor
public class Applicant {

    private String name;

    private int age;

    private boolean valid;
}
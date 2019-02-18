/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test1;

import java.util.Set;

/**
 *
 * @author Robert.Tenadze
 */
public class Rule {

    private Expression expression;
    private String ruleResult;

    public Rule(Expression expression, String ruleResult) {
        this.expression = expression;
        this.ruleResult = ruleResult;
    }

    public void calculate(Set<String> knownFacts) {
        if (expression.calculate(knownFacts)) {
            knownFacts.add(ruleResult);
        }
    }
}

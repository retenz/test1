/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test1;

import java.util.Collection;
import java.util.Set;

/**
 *
 * @author Robert.Tenadze
 */
public class AndExpression implements Expression {

    private Collection<Expression> expressions;

    public AndExpression(Collection<Expression> expressions) {
        this.expressions = expressions;
    }

    @Override
    public boolean calculate(Set<String> knownFacts) {
        boolean result = true;
        for (Expression ex : expressions) {
            result = result && ex.calculate(knownFacts);
        }

        return result;
    }

}

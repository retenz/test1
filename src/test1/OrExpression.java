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
public class OrExpression implements Expression {

    Collection<Expression> expressions;

    public OrExpression(Collection<Expression> expressions) {
        this.expressions = expressions;
    }

    @Override
    public boolean calculate(Set<String> knownFacts) {
        boolean result = false;
        for (Expression ex : expressions) {
            result = result || ex.calculate(knownFacts);
        }

        return result;
    }

}

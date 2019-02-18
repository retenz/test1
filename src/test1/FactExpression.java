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
public class FactExpression implements Expression {

    private String fact;

    public FactExpression(String fact) {
        this.fact = fact;
    }

    @Override
    public boolean calculate(Set<String> knownFacts) {
        return knownFacts.contains(fact);
    }

}

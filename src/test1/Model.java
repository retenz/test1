/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test1;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Robert.Tenadze
 */
public class Model {

    private Collection<Rule> rules;
    private Set<String> knownFacts;

    public Model(Collection<Rule> rules, Set<String> knownFacts) {
        this.rules = rules;
        this.knownFacts = knownFacts;
    }

    public void calculate() {
        int knownFactsSize = 0;
        while (knownFactsSize != knownFacts.size()) {
            knownFactsSize = knownFacts.size();
            for (Rule rule : rules) {
                rule.calculate(knownFacts);
            }
        }
    }

    public void deduceFacts() {
        Iterator it = knownFacts.iterator();
        // We have at least one fact or it will be exception
        System.out.print(it.next());
        while (it.hasNext()) {
            System.out.print(", " + it.next());
        }
    }
}

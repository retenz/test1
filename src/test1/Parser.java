/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import test1.exception.EmptyFileException;
import test1.exception.FaultInFileException;

/**
 *
 * @author Robert.Tenadze
 */
public class Parser {

    private enum State {
        RULE,
        FACTS,
        FINISH;
    }

    // читает, парсит и проверяет весь файл на верность заполнения
    public Model parse(String fileName) throws FileNotFoundException, IOException, EmptyFileException, FaultInFileException {

        ArrayList<Rule> rules = new ArrayList<>();
        HashSet<String> knownFacts = new HashSet<>();
        String line;
        State state = State.RULE;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {

            for (int lineNumber = 1; (line = reader.readLine()) != null; lineNumber++) {
                switch (state) {
                    case RULE:
                        if (Pattern.matches("-{64}", line)) {
                            state = State.FACTS;
                            break;
                        }
                        if (!Pattern.matches("^\\s*((_*[A-Za-z]+(_*\\w+)*)+?(\\s*(&&|\\|\\|)\\s*((_*[A-Za-z]+(_*\\w+)*)+?))*)++\\s*(_*->\\s*[A-Za-z]+(_*\\w+)*)*\\s*$", line)) {
                            throw new FaultInFileException("Строка №" + lineNumber + " не соответствует формату строки с выражением!\n");
                        }
                        rules.add(parseRule(line));
                        break;
                    case FACTS:
                        if (!Pattern.matches("^\\s*(_*[A-Za-z]+(_*\\w+)*)+?((\\s*,\\s*)_*[A-Za-z]+(_*\\w+)*)*\\s*$", line)) {
                            throw new FaultInFileException("В исходном файле отсутствует строка с известными фактами или не соответствует её формату!\n"
                                    + "Строка с известными фактами должна стоять сразу после строки-разделитель и\n"
                                    + "быть последней строкой в файле.\n");
                        }
                        parseKnownFacts(line, knownFacts);
                        state = State.FINISH;
                        break;
                    case FINISH:
                        throw new FaultInFileException("Были введены лишние данные после строки с известными фактами!\n"
                                + "Строка с известными фактами должна стоять сразу после строки-разделитель и\n"
                                + "быть последней строкой в файле.\n");
                }
            }

            if (state != State.FINISH) {
                throw new FaultInFileException("Ошибка в формате входных данных.\n"
                        + "Файл состоит из логических выражений.\n"
                        + "Далее идёт строка-разделитель, которая состоит из 64 '-'.\n"
                        + "Последняя строка - строка с известными фактами.\n");
            }
            return new Model(rules, knownFacts);
        }
    }

    private Rule parseRule(String line) {

        String[] lineParts;
        lineParts = line.split("->");
        parseOrExpression(lineParts[0]);
        return new Rule(parseOrExpression(lineParts[0]), lineParts[1].trim());
    }

    private Expression parseOrExpression(String line) {

        ArrayList<Expression> andExpressions = new ArrayList<>();
        for (String str : line.split("\\|\\|")) {
            andExpressions.add(parseAndExpression(str));

        }
        return new OrExpression(andExpressions);
    }

    private Expression parseAndExpression(String line) {

        ArrayList<Expression> factExpressions = new ArrayList<>();
        for (String str : line.split("&&")) {
            factExpressions.add(new FactExpression(str.trim()));
        }
        return new AndExpression(factExpressions);
    }

//    private Expression parseExpression(String line) {
//
//        ArrayList<Expression> andExpressions = new ArrayList<>();
//        for (String s : line.split("\\|\\|")) {
//            ArrayList<Expression> facts = new ArrayList<>();
//            for (String v : s.split("&&")) {
//                facts.add(new FactExpression(v.trim()));
//            }
//            andExpressions.add(new AndExpression(facts));
//
//        }
//        return new OrExpression(andExpressions);
//    }
//    private Expression parseExpression(String line) { //парсит строку уравнения (каждая переменная и логический знак- это отдельный элемент)
//
//        ArrayList<String> logicOperations = new ArrayList<>();
//        ArrayList<Expression> facts = new ArrayList<>();
//        Pattern patElement = Pattern.compile("((_*[A-Za-z]+(_*\\w+)*)|((&&|\\|\\|)))");
//        Matcher matElement = patElement.matcher(line);
//        while (matElement.find()) {
//            String s = matElement.group();
//            switch (s) {
//                case "&&":
//                    logicOperations.add("&&");
//                    break;
//                case "||":
//                    logicOperations.add("||");
//                    break;
//                default:
//                    facts.add(new FactExpression(s));
//                    break;
//            }
//        }
//        Expression resultExpression = facts.get(0);
//        for (int i = 0; i < logicOperations.size(); i++) {
//            switch (logicOperations.get(i)) {
//                case "&&":
//                    resultExpression = new AndExpression(resultExpression, facts.get(i + 1));
//                    break;
//                case "||":
//                    resultExpression = new OrExpression(resultExpression, facts.get(i + 1));
//                    break;
//            }
//
//        }
//        return resultExpression;
//    }
//    private Expression parseExpression(String line) { //парсит строку уравнения (каждая переменная и логический знак- это отдельный элемент)
//
//        ArrayList<LogicOperation> logicOperations = new ArrayList<>();
//        ArrayList<String> facts = new ArrayList<>();
//        Pattern patElement = Pattern.compile("((_*[A-Za-z]+(_*\\w+)*)|((&&|\\|\\|)))");
//        Matcher matElement = patElement.matcher(line);
//        while (matElement.find()) {
//            String s = matElement.group();
//            switch (s) {
//                case "&&":
//                    logicOperations.add(new LogicOperationAND());
//                    break;
//                case "||":
//                    logicOperations.add(new LogicOperationOR());
//                    break;
//                default:
//                    facts.add(s);
//                    break;
//            }
//        }
//        return new Expression(logicOperations, facts);
//    }
    private void parseKnownFacts(String line, Collection<String> knownFacts) {

        Pattern patFacts = Pattern.compile("(_*[A-Za-z]+(_*\\w+)*)");
        Matcher matFacts = patFacts.matcher(line);
        while (matFacts.find()) {
            knownFacts.add(matFacts.group());
        }

    }
}

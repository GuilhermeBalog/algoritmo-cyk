/**
 * @author Gabriel de Castro Michelassi - 11208162
 * @author Guilherme Balog Gardino - 11270649
*/

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class glc {
    public static void main(String[] args) {
        // abrir arquivo
        String specificationFilePath = "inpt-glc.txt";
        String testCasesFilePath = "inp-cadeias.txt";
        String outputFileFilePath = "out-status.txt";

        Scanner specificationFile = null;
        Scanner testCasesFile = null;
        PrintWriter outputFile = null;

        try{
            specificationFile = new Scanner (new BufferedReader(new FileReader(specificationFilePath)));
            testCasesFile = new Scanner (new BufferedReader(new FileReader(testCasesFilePath)));
            outputFile = new PrintWriter(outputFileFilePath);
            
            validateInputsWithCfg(specificationFile, testCasesFile, outputFile);
        } catch(FileNotFoundException ex){
            System.out.println("File not found");
        }finally{
            if(specificationFile != null){
                specificationFile.close();
            }
            if(testCasesFile != null){
                testCasesFile.close();
            }
            if(outputFile != null){
                outputFile.close();
            }
        }
    }

    private static void validateInputsWithCfg(Scanner specificationFile, Scanner testCasesFile, PrintWriter outputFile) {

        int numberOfGrammars = specificationFile.nextInt();

        for (int i = 0; i < numberOfGrammars; i++) {
            ContextFreeGrammar grammar = buildCfgFromFile(specificationFile);
            String[] testCases = getTestCases(testCasesFile);
            boolean[] testsResults = new boolean[testCases.length];

            for (int j = 0; j < testCases.length; j++) {
                testsResults[i] = grammar.validate(testCases[j]);
            }
            
            outputFile.println(buildReportLine(testsResults));
        }
    }

    public static ContextFreeGrammar buildCfgFromFile(Scanner file) {
        int n_variables = file.nextInt();
        int n_symbols = file.nextInt();
        int n_rules = file.nextInt();

        file.nextLine();

        String[] variables = file.nextLine().split(" ");
        String[] symbols = file.nextLine().split(" ");
        @SuppressWarnings("unchecked")
        List<String>[] rules = new LinkedList[n_rules];

        for (int i = 0; i < n_rules; i++) {
            rules[i] = new LinkedList<String>();

            String[] rule = file.nextLine().split(" ");
            
            for(String productionMember : rule){
                if(productionMember.equals("=>"))
                    continue;

                rules[i].add(productionMember);
            }
        }

        return new ContextFreeGrammar(variables, symbols, rules);
    }

    public static String[] getTestCases(Scanner file) {
        int n_tests = file.nextInt();
        file.nextLine();        

        String[] tests = new String[n_tests];
        
        for (int i = 0; i < n_tests; i++) {
            tests[i] = file.nextLine();
        }

        return tests;
    }

    public static String buildReportLine(boolean[] results) {
        String resp = "";
        for (boolean i : results) {
            if (i)
                resp += "1 ";
            else
                resp += "0 ";
        }
        return resp.trim();
    }
}

import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;

public class glc {
    public static final String emptyString = "&";

    private String[] variables;
    private String[] terminalSymbols;
    private Map<String, String[]> productionRules;
    private String initialVariable;

    glc(String[] variables, String[] terminalSymbols, Map<String, String[]> productionRules) {
        this.variables = variables;
        this.terminalSymbols = terminalSymbols;
        this.productionRules = productionRules;
        this.initialVariable = variables[0];
    }

    public boolean validate(String input){
        if(input.equals(emptyString)){
            return doesEmptyProductionExists();
        }
        
        String[] splitInput = input.split(" ");
        String[][] table = new String[splitInput.length][splitInput.length];

        for (String symbol : splitInput) {
            // seguir o algoritmo
        }        

        return true;
    }

    private boolean doesEmptyProductionExists() {
        String[] productions = this.productionRules.get(initialVariable);

        for (String production : productions) 
            if(production.equals(emptyString))
                return true;

        return false;
    }

    public static void main(String[] args) {
        // abrir arquivo
        Scanner specificationFile = null;
        Scanner testCasesFile = null;
        PrintWriter outputFile = null;

        //pegar do arquivo
        validateInputsWithGlc(specificationFile, testCasesFile, outputFile);
    }

    private static void validateInputsWithGlc(Scanner specificationFile, Scanner testCasesFile, PrintWriter outputFile) {

        int numberOfGrammars = specificationFile.nextInt();

        for (int i = 0; i < numberOfGrammars; i++) {
            glc grammar = buildGlcFromFile(specificationFile);
            String[] testCases = getTestCases(testCasesFile);
            boolean[] testsResults = new boolean[testCases.length];

            for (int j = 0; j < testCases.length; j++) {
                testsResults[i] = grammar.validate(testCases[i]);
            }
            
            outputFile.println(buildReportLine(testsResults));
        }
    }

    public static glc buildGlcFromFile(Scanner file) {
        // carregar as coisas do arquivo
        
        return new glc(null, null, null);
    }

    public static String[] getTestCases(Scanner file) {
        // carregar

        return new String[0];
    }

    public static String buildReportLine(boolean[] results) {
        return "";
    }
}

import java.io.PrintWriter;
import java.util.Scanner;

public class glc {
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
            ContextFreeGrammar grammar = buildGlcFromFile(specificationFile);
            String[] testCases = getTestCases(testCasesFile);
            boolean[] testsResults = new boolean[testCases.length];

            for (int j = 0; j < testCases.length; j++) {
                testsResults[i] = grammar.validate(testCases[i]);
            }
            
            outputFile.println(buildReportLine(testsResults));
        }
    }

    public static ContextFreeGrammar buildGlcFromFile(Scanner file) {
        // carregar as coisas do arquivo
        
        return new ContextFreeGrammar(null, null, null);
    }

    public static String[] getTestCases(Scanner file) {
        // carregar

        return new String[0];
    }

    public static String buildReportLine(boolean[] results) {
        return "";
    }
}

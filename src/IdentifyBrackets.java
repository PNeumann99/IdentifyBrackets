import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Programm, dass eine Textdatei einliest und die darin enthaltenen Klammerpaare identifiziert.
 * Es wird zwischen drei Klammertypen ’(’ und ’)’, ’{’ und ’}’, sowie ’[’ und ’]’ unterschieden.
 * Am Ende des Programms werden die gefundenen Klammerpaare zeilenweise mitsamt Positionen
 * auf der Konsole ausgegeben.
 *
 * @author akubf
 */
public class IdentifyBrackets {


    /**
     * Liest eine Textdatei ein und identifiziert die darin enthaltenen Klammerpaare.
     * Es wird zwischen drei Klammertypen ’(’ und ’)’, ’{’ und ’}’, sowie ’[’ und ’]’ unterschieden.
     * Die Vorgehensweise entspricht dem Algorithmus zur Erkennung von Klammerausdrücken mit den in Aufgabe 2 genannten
     * Änderungen. Am Ende werden die gefundenen Klammerpaare zeilenweise mitsamt Positionen
     * auf der Konsole ausgegeben.
     *
     * @param args der Dateipfad zum zu überprüfenden Klammerausdruck.
     * @throws IOException              falls das Einlesen des Klammerausdrucks fehlschlägt.
     * @throws IllegalArgumentException falls der Klammerausdruck nicht korrekt ist.
     */
    public static void main(String[] args) throws IOException, IllegalArgumentException {
        String file = args[0]; // Pfad der auszulesenden Datei aus Kommandozeilenparameter auslesen.
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file))); // um die Datei auszulesen

        Stack<Character> brackets = new Stack<>(); // Stack für die Klammern
        Stack<String> positions = new Stack<>(); // Stack für die Positionen der Klammern aus dem ersten Stack (parallel)

        String output = ""; // dies wird die Ausgabe des Programms

        String line = in.readLine(); // erste Zeile auslesen
        int lineNr = 1; // Index für die Zeile, ist für die korrekte Speicherung der Positionen der Klammern nötig

        while (line != null) { // Zeilenweise die Datei auslesen

            // buchstabenweise über die aktuelle Zeile iterieren
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '(' || line.charAt(i) == '[' || line.charAt(i) == '{') { // wurde eine Klammer (auf) gefunden?
                    brackets.push(line.charAt(i)); // Klammer auf den Stack
                    positions.push(pos(lineNr, i + 1)); // Position der Klammer auf den Stack
                } else if (line.charAt(i) == ')' || line.charAt(i) == ']' || line.charAt(i) == '}') { // wurde eine geschlossene Klammer gefunden?
                    // jetzt prüfen, ob die Klammer zur letzten offenen Klammer passt (top des Stacks)
                    if (line.charAt(i) == ')') {
                        if (brackets.top() == '(') {
                            output += match(brackets.top(), positions.top(), pos(lineNr, i + 1)) + "\n"; // Klammern zusammengehörig in den Output packen
                            brackets.pop(); // Klammer vom Stack nehmen
                            positions.pop(); // position vom Stack nehmen
                        } else throw new IllegalArgumentException("Der Klammerausdruck ist nicht korrekt.");
                    } else if (line.charAt(i) == ']') {
                        if (brackets.top() == '[') {
                            output += match(brackets.top(), positions.top(), pos(lineNr, i + 1)) + "\n"; // Klammern zusammengehörig in den Output packen
                            brackets.pop(); // Klammer vom Stack nehmen
                            positions.pop(); // position vom Stack nehmen
                        } else throw new IllegalArgumentException("Der Klammerausdruck ist nicht korrekt.");
                    } else {
                        if (brackets.top() == '{') {
                            output += match(brackets.top(), positions.top(), pos(lineNr, i + 1)) + "\n"; // Klammern zusammengehörig in den Output packen
                            brackets.pop(); // Klammer vom Stack nehmen
                            positions.pop(); // position vom Stack nehmen
                        } else throw new IllegalArgumentException("Der Klammerausdruck ist nicht korrekt.");
                    }
                }
            }
            line = in.readLine(); // nächste Zeile auslesen
            lineNr++; // Zeilenindex inkrementieren
        }
        if (!brackets.isEmpty()) throw new IllegalArgumentException("Der Klammerausdruck ist nicht korrekt.");
        System.out.println(output); // Ausgabe auf dem Bildschirm
    }

    /**
     * Funktion die die Positionsteil des Outputs als String baut,
     * sodass er für später auf dem Stack gespeichert werden kann.
     *
     * @param line Die Zeile der gefundenen Klammer
     * @param col  Die Spalte der gefundenen Klammer
     * @return die Position der Klammer im Format "at line 1, column 10"
     */
    private static String pos(int line, int col) {
        return "at " + line + ", column " + col;
    }

    /**
     * Funktion, die ein gefundenes Klammerpaar als String für den Output des Programms baut.
     *
     * @param bracket Der Typ der gefundenen Klammer (auf), akzeptiert: '(', '[', '{'
     * @param pos1    Die Position der 1. Klammer (also Klammer auf)
     * @param pos2    Die Position der 2. Klammer (also Klammer zu)
     * @return Das gefundene Klammerpaar als String mitsamt Positionen im Format
     * "Matching brackets: ’[’ at line 1, column 10 and ’]’ at line 1, column 11"
     */
    private static String match(char bracket, String pos1, String pos2) {
        char bracket2;
        if (bracket == '(') {
            bracket2 = ')';
        } else if (bracket == '[') {
            bracket2 = ']';
        } else if (bracket == '{') {
            bracket2 = '}';
        } else {
            return null;
        }

        return "Matching brackets: '" + bracket + "' " + pos1 + " and '"
                + bracket2 + "' " + pos2;
    }
}


public class KMPSearch {

  /*
   * Bus Service Questions:
   *
   * 1. How many total vehicles is there information on?
   *    //987
   *
   * 2. Does the file contain information about the vehicle number 16555?
   *    //YES
   *
   * 3. Locate the first record about a bus heading to HAMPTON PARK
   *    //19605
   *
   * 4. Does the file contain information about the vehicle number 9043409?
   *    //No
   */

    /*
     * The method checks whether a pattern pat occurs at least once in String txt.
     *
     */

    static KMPSearch currentKMP;
    static int[][] DFA;
    int R = 256;

    public KMPSearch(String pat) {
        int M = pat.length();
        DFA = new int[R][M];
        DFA[pat.charAt(0)][0] = 1;
        for (int x = 0, j = 1; j < M; j++) {
            for (int c = 0; c <R; c++) {
                DFA[c][j] = DFA[c][x];
            }
            DFA[pat.charAt(j)][j] = j + 1;
            x = DFA[pat.charAt(j)][x];
        }
    }

    public static boolean contains(String txt, String pat) {
        if (txt.length() <= 0 || txt.equals("") || pat.equals(""))
            return false;
        //TODO: Implementation
        currentKMP = new KMPSearch(pat);
        int i; int j;
        int m = pat.length();
        int n = txt.length();
        for (i = 0, j = 0; i < n && j < m; i++) {
            j = DFA[txt.charAt(i)][j];
        }
        if (j == m) return true;
        return false;
    }

    /*
     * The method returns the index of the first ocurrence of a pattern pat in String txt.
     * It should return -1 if the pat is not present
     */
    public static int searchFirst(String txt, String pat) {
        //TODO: Implementation
        if (txt.length() <= 0 || txt.equals("") || pat.equals(""))
            return -1;
        currentKMP = new KMPSearch(pat);
        int i; int j;
        int m = pat.length();
        int n = txt.length();
        for (i = 0, j = 0; i < n && j < m; i++) {
            j = DFA[txt.charAt(i)][j];
        }
        if (j == m) return i - m;
        return -1;
    }


    /*
     * The method returns the number of non-overlapping occurences of a pattern pat in String txt.
     */
//    public static int searchAll(String txt, String pat) {
//        if (txt.length() <= 0 || txt.equals("") || pat.equals(""))
//            return 0;
//        currentKMP = new KMPSearch(pat);
//        int i; int j; int count = 0;
//        int m = pat.length();
//        int n = txt.length();
//        for (int c = 0; c < n; c++) {
//            for (i = c, j = 0; i < n && j < m; i++) {
//                j = DFA[txt.charAt(i)][j];
//            }
//            if (j == m) {
//                count++;
//                c = i;
//            }
//        }
//        if (count == 0) return count;
//        return count;
//    }
}
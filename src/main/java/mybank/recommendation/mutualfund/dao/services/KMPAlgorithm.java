package mybank.recommendation.mutualfund.dao.services;

import org.springframework.stereotype.Service;

@Service
public class KMPAlgorithm {
    public int[] buildLPS(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];
        int j = 0; // length of the previous longest prefix suffix
        lps[0] = 0; // lps[0] is always 0

        // Loop to calculate lps[i] for i = 1 to m-1
        for (int i = 1; i < m; i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = lps[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            lps[i] = j;
        }
        return lps;
    }

    // KMP search function
    public boolean KMPSearch(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();

        if (m == 0) return false; // Empty pattern

        int[] lps = buildLPS(pattern);
        int i = 0; // index for text
        int j = 0; // index for pattern

        while (i < n) {
            if (pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }

            if (j == m) {
                return true; // Pattern found in text
            } else if (i < n && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        return false; // Pattern not found in text
    }
}

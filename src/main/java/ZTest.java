import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.stat.StatUtils;

import java.util.List;

/**
 * The ZTest class runs a Z-test on the pattern (or sample) loaded by LoadPattern, with additional necessary values
 * provided as CLA (command-line arguments).
 */
public class ZTest {
    private final double m0;
    private final double variance;
    private final double significanceLevel;
    private final List<Double> pattern;

    public ZTest(double m0, double variance, double significanceLevel, List<Double> pattern) {
        this.m0 = m0;
        this.variance = variance;
        this.significanceLevel = 1 - (significanceLevel / 100);
        this.pattern = pattern;

        RunTest();
    }

    public void PrintPattern() {
        System.out.print("Pattern:\t");
        for(Double i : pattern) {
            System.out.print(i + " ");
        }
        System.out.print("\n");
    }

    private void RunTest() {
        int n = pattern.size();
        double criticalValue = new NormalDistribution().inverseCumulativeProbability(1 - significanceLevel);
        double criticalValue2 = new NormalDistribution().inverseCumulativeProbability(1 - (significanceLevel / 2));
        double standardError = variance / Math.sqrt(n);
        double mean = StatUtils.mean(pattern.stream()
                .mapToDouble(Double::doubleValue)
                .toArray());
        double zValue = (mean - m0) / standardError;

        PrintPattern();
        System.out.print("###\n" +
                "m0 = " + m0 + "\n" +
                "variance = " + variance + "\n" +
                "1 - significance = " + significanceLevel + "\n" +
                "n = " + n + "\n" +
                "mean = " + mean + "\n" +
                "SE = " + variance + " / " + "sqrt(" + n + ") = " + standardError + "\n" +
                "z = (" + mean + " - " + m0 + ") / SE = " + zValue + "\n" +
                "c (1 - " + significanceLevel + ") = " + criticalValue + "\n" +
                "c2 (1 - " + significanceLevel + " / 2) = " + criticalValue2 + "\n" +
                "###\n\n");

        System.out.print("Left-tailed test:\n" +
                " H0: m <= m0\tH1: m > m0\n\n" +
                " =============|---\n");
        if (zValue < criticalValue) {
            System.out.print("       z      c\n" +
                    "  Null hypothesis accepted.\n");
        } else {
            System.out.print("              c z\n" +
                    "  Null hypothesis rejected.\n");
        }

        System.out.print("\nRight-tailed test:\n" +
                " H0: m >= m0\tH1: m < m0\n\n" +
                " ---|=============\n");
        if (zValue > -criticalValue) {
            System.out.print("   c      z\n" +
                    "  Null hypothesis accepted.\n");
        } else {
            System.out.print("  z c\n" +
                    "  Null hypothesis rejected.\n");
        }

        System.out.print("\nTwo-tailed test:\n" +
                " H0: m = m0\tH1: m =/= m0\n\n" +
                " ---|=========|---\n");
        if (zValue < -criticalValue2) {
            System.out.print("  z c2        c2\n" +
                    "  Null hypothesis rejected.\n");
        } else if (zValue > criticalValue2) {
            System.out.print("    c2        c2 z\n" +
                    "  Null hypothesis rejected.\n");
        } else {
            System.out.print("    c2   z    c2\n" +
                    "  Null hypothesis accepted.\n");
        }
    }
}

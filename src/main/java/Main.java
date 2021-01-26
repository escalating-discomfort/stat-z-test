public class Main {

    public static void main(String[] args) {
        if (args.length == 4) {
            ZTest zTest = new ZTest(Double.parseDouble(args[0]),  // m0
                    Double.parseDouble(args[1]),                  // variance
                    Double.parseDouble(args[2]),                  // significance level
                    new LoadPattern(args[3]).getValues());        // pattern file path
        }
    }
}

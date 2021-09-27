public class PatternTester {
    public static void main(String[] args) {
        CharSequence pattern = "hello";
        //CharSequence text = "xhelloxxhellxhelloxxxhexxhellox";
        CharSequence text = "xxxxxxxx";
        CharacterComparator comparator = new CharacterComparator();
        PatternMatching.boyerMoore(pattern,text,comparator);

    }
}

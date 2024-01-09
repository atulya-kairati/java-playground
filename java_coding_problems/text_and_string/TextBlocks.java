package text_and_string;

public class TextBlocks {
    public static void main(String[] args) {
        // text block
        String block1 = """
                hi
                I
            am
                a
                weirdo""";
                // unintentional indentation will be string by the compiler
                // unintentional indentation -> least no of white space (in this case the "am" line)

        String block2 = """
                        But
                    I
                    like
                    being
                        weird
                """; // these are also considered when striping white space
                // And placing end quotes in a new line adds a \n

        String block3 = """
                because \
                it \
                makes \
                everyone \
                uncomfortable \
                :) \
                """; // \ can be used to have newlines in the code but not in the string itself

        String block4 = """
                \"""Manus Chaubey\"""  \s
                """; // \""" can be used to escape """
                    // \s can be used to preserve trailing whitespace

        // there can be no comments in text block
        // text blocks are essentially the same as string literals with better multiline supports

        System.out.println(block1);
        System.out.println(block2);
        System.out.println(block3);
        System.out.println(block4);
    }
}

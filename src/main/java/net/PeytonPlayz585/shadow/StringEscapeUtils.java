package net.PeytonPlayz585.shadow;

public class StringEscapeUtils {
	
	public static final CharSequenceTranslator UNESCAPE_JAVA = new AggregateTranslator(new OctalUnescaper(),     /* .between('\1', '\377'), */ new UnicodeUnescaper(), new LookupTranslator(EntityArrays.JAVA_CTRL_CHARS_UNESCAPE()), new LookupTranslator( new String[][] {{"\\\\", "\\"}, {"\\\"", "\""}, {"\\'", "'"}, {"\\", ""} }));
	
	/**
     * Unescapes any Java literals found in the {@link String}.
     * For example, it will turn a sequence of {@code '\'} and
     * {@code 'n'} into a newline character, unless the {@code '\'}
     * is preceded by another {@code '\'}.
     *
     * @param input  the {@link String} to unescape, may be null
     * @return a new unescaped {@link String}, {@code null} if null string input
     */
    public static final String unescapeJava(final String input) {
        return UNESCAPE_JAVA.translate(input);
    }
}
package net.PeytonPlayz585.shadow;

public class EntityArrays {
	
	private static final String[][] JAVA_CTRL_CHARS_ESCAPE = {{"\b", "\\b"}, {"\n", "\\n"}, {"\t", "\\t"}, {"\f", "\\f"}, {"\r", "\\r"}};

	private static final String[][] JAVA_CTRL_CHARS_UNESCAPE = invert(JAVA_CTRL_CHARS_ESCAPE);
	
	/**
     * Used to invert an escape array into an unescape array
     * @param array String[][] to be inverted
     * @return String[][] inverted array
     */
    public static String[][] invert(final String[][] array) {
        final String[][] newarray = new String[array.length][2];
        for (int i = 0; i<array.length; i++) {
            newarray[i][0] = array[i][1];
            newarray[i][1] = array[i][0];
        }
        return newarray;
    }
	
	/**
     * Reverse of {@link #JAVA_CTRL_CHARS_ESCAPE()} for unescaping purposes.
     * @return the mapping table
     */
    public static String[][] JAVA_CTRL_CHARS_UNESCAPE() {
        return JAVA_CTRL_CHARS_UNESCAPE.clone();
    }
}
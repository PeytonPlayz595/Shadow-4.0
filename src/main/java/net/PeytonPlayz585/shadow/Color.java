package net.PeytonPlayz585.shadow;

public class Color {
    private int rgb;

    public static final Color BLACK = new Color(0, 0, 0);
    public static final Color WHITE = new Color(255, 255, 255);

    public Color(int r, int g, int b) {
        this.rgb = ((r & 0xFF) << 16) | ((g & 0xFF) << 8) | (b & 0xFF);
    }

    public Color(int r, int g, int b, int a) {
        this.rgb = ((a & 0xFF) << 24) | ((r & 0xFF) << 16) | ((g & 0xFF) << 8) | (b & 0xFF);
    }

    public int getRed() {
        return (rgb >> 16) & 0xFF;
    }

    public int getGreen() {
        return (rgb >> 8) & 0xFF;
    }

    public int getBlue() {
        return rgb & 0xFF;
    }

    public int getRGB() {
        return rgb;
    }

    public int getAlpha() {
        return (rgb >> 24) & 0xFF;
    }

    public static Color decode(String nm) throws NumberFormatException {
        nm = nm.trim();
        if (nm.charAt(0) == '#') {
            nm = nm.substring(1);
        }

        int colorValue = Integer.parseInt(nm, 16);
        return new Color((colorValue >> 16) & 0xFF, (colorValue >> 8) & 0xFF, colorValue & 0xFF);
    }

    public Color brighter() {
        int r = getRed();
        int g = getGreen();
        int b = getBlue();

        int i = (int) (1.0 / (1.0 - 0.7));
        if (r == 0 && g == 0 && b == 0) {
            return new Color(i, i, i);
        }

        if (r > 0 && r < i) {
            r = i;
        }

        if (g > 0 && g < i) {
            g = i;
        }

        if (b > 0 && b < i) {
            b = i;
        }

        return new Color(Math.min((int) (r / 0.7), 255), Math.min((int) (g / 0.7), 255),
                Math.min((int) (b / 0.7), 255));
    }

    public Color darker() {
        return new Color(Math.max((int) (getRed() * 0.7), 0), Math.max((int) (getGreen() * 0.7), 0),
                Math.max((int) (getBlue() * 0.7), 0));
    }

    public boolean equals(Object obj) {
        if (obj instanceof Color) {
            return ((Color) obj).getRGB() == rgb;
        }
        return false;
    }

    public String toString() {
        return "Color[r=" + getRed() + ",g=" + getGreen() + ",b=" + getBlue() + "]";
    }
}
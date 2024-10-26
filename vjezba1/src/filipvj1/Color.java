package filipvj1;

public class Color {

    int value;

    public int getRGB() {
        return value;
    }

    public int getRed() {
        return (getRGB() >> 16) & 0xFF;
    }


    public int getGreen() {
        return (getRGB() >> 8) & 0xFF;
    }


    public int getBlue() {
        return (getRGB() >> 0) & 0xFF;
    }

    public static float[] RGBtoHSB(int r, int g, int b, float[] hsbvals) {
        float hue, saturation, brightness;
        if (hsbvals == null) {
            hsbvals = new float[3];
        }
        int cmax = (r > g) ? r : g;
        if (b > cmax) cmax = b;
        int cmin = (r < g) ? r : g;
        if (b < cmin) cmin = b;

        brightness = ((float) cmax) / 255.0f;
        if (cmax != 0)
            saturation = ((float) (cmax - cmin)) / ((float) cmax);
        else
            saturation = 0;
        if (saturation == 0)
            hue = 0;
        else {
            float redc = ((float) (cmax - r)) / ((float) (cmax - cmin));
            float greenc = ((float) (cmax - g)) / ((float) (cmax - cmin));
            float bluec = ((float) (cmax - b)) / ((float) (cmax - cmin));
            if (r == cmax)
                hue = bluec - greenc;
            else if (g == cmax)
                hue = 2.0f + redc - bluec;
            else
                hue = 4.0f + greenc - redc;
            hue = hue / 6.0f;
            if (hue < 0)
                hue = hue + 1.0f;
        }
        hsbvals[0] = hue;
        hsbvals[1] = saturation;
        hsbvals[2] = brightness;
        return hsbvals;
    }
    public Color(int r, int g, int b) {
        this(r, g, b, 255);
    }
    public Color(int r, int g, int b, int a) {
        value = ((a & 0xFF) << 24) |
                ((r & 0xFF) << 16) |
                ((g & 0xFF) << 8)  |
                ((b & 0xFF) << 0);
        testColorValueRange(r,g,b,a);
    }
    private static void testColorValueRange(int r, int g, int b, int a) {
        boolean rangeError = false;
        String badComponentString = "";

        if ( a < 0 || a > 255) {
            rangeError = true;
            badComponentString = badComponentString + " Alpha";
        }
        if ( r < 0 || r > 255) {
            rangeError = true;
            badComponentString = badComponentString + " Red";
        }
        if ( g < 0 || g > 255) {
            rangeError = true;
            badComponentString = badComponentString + " Green";
        }
        if ( b < 0 || b > 255) {
            rangeError = true;
            badComponentString = badComponentString + " Blue";
        }
        if ( rangeError == true ) {
            throw new IllegalArgumentException("filipvj1.Color parameter outside of expected range:"
                    + badComponentString);
        }
    }

    public static Color decode(String nm) throws NumberFormatException {
        Integer intval = Integer.decode(nm);
        int i = intval.intValue();
        return new Color((i >> 16) & 0xFF, (i >> 8) & 0xFF, i & 0xFF);
    }

    public double[] RGBtoCMYK(){
        double r = getRed()/255.0;
        double g = getGreen()/255.0;
        double b = getBlue()/255.0;

        double k = 1 - Math.max(Math.max(r, g), b);

        if (k == 1.0) {
            return new double[]{ 0, 0, 0, 100 };
        }

        double c = ((1 - r - k) / (1 - k) * 100);
        double m = ((1 - g - k) / (1 - k) * 100);
        double y = ((1 - b - k) / (1 - k) * 100);

        return new double[]{ c, m, y, (int)k };
    }

    public double[] RGBtoHSL() {
        double r = getRed()/255.0;
        double g = getGreen()/255.0;
        double b = getBlue()/255.0;
        double max = Math.max(r,Math.max(g,b));
        double min = Math.min(r,Math.min(g,b));
        double h,s,l;

        l = (max+min)/2.0;

        double d= max - min;

        if (d ==0){
            s = 0;
        }else {
            s = (l<0.5) ? d / (max+min) : d/ (2.0 - max-min);
        }
        if (d==0){
            h=0;
        }else {
            if (max == r){
                h =(g - b) / d +(g < b ? 6:0);
            }else if (max == g){
                h =(b - r) / d + 2;
            }else {
                h =(r - g) / d + 4;
            }
            h = h/6;
        }

        return new double[]{h*360, s*100, l *100};
    }
    }
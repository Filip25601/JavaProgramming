import filipvj1.Color;


public class Main {
    public static void main(String[] args) {
        String hexColor = "0x1FF0FF";
        Color c = Color.decode(hexColor);
        float[] hsbCode = new float[3];
        Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), hsbCode);
        System.out.println("Boja u HEX formatu: 0x" +
                Integer.toHexString(c.getRGB() & 0x00FFFFFF));
        System.out.println("Boja u RGB formatu: " + c.getRed() + ", " +
                c.getGreen() + ", " + c.getBlue());
        System.out.println("Boja u HSB formatu: " + hsbCode[0] * 360 + "°, " +
                hsbCode[1] * 100 + "%, " + hsbCode[2] * 100 + "%");
        double[] cmyk = c.RGBtoCMYK();
        System.out.println("Boja u CMYK formatu: ");
        System.out.printf("C = %.2f%%\n", cmyk[0]);
        System.out.printf("M = %.2f%%\n", cmyk[1]);
        System.out.printf("Y = %.2f%%\n", cmyk[2]);
        System.out.printf("K = %.2f%%\n", cmyk[3]);
        double[] hsl=c.RGBtoHSL();
        System.out.println("Boja u HSL formatu: ");
        System.out.printf("H = %.2f°\n", hsl[0]);
        System.out.printf("S = %.2f%%\n", hsl[1]);
        System.out.printf("L = %.2f%%\n", hsl[2]);

    }
}
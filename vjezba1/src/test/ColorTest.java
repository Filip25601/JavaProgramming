package test;
import filipvj1.Color;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class ColorTest {
    @Test
    public void testRGBtoHSL(){
        Color color = new Color(31,240,255);
        double[]hsl = color.RGBtoHSL();
        assertEquals(184.0, hsl [0], 0.1);
        assertEquals(100, hsl [1], 0.1);
        assertEquals(56.0, hsl [2], 0.1);
    }

    @Test
    public void testRGBtoCMYK(){
        Color color = new Color(31, 240,255);
        double[]cmyk = color.RGBtoCMYK();
        assertEquals(87.84, cmyk [0], 0.1);
        assertEquals(5.88, cmyk [1], 0.1);
        assertEquals(0, cmyk [2], 0.1);
        assertEquals(0, cmyk [3], 0.1);
    }

    @Test
    public void testRGBtoHSB(){
        Color color = new Color(31, 240, 255);
        float[] hsb = new float[3];
        Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), hsb);

        assertEquals(184.01, (hsb[0] * 360), 0.1);
        assertEquals(87.84, (hsb[1] * 100), 0.1);
        assertEquals(100, (hsb[2] * 100), 0.1);

    }

    @Test
    public void testDecode(){
        Color color = Color.decode("0x1FF0FF");
        assertEquals(31, color.getRed());
        assertEquals(240, color.getGreen());
        assertEquals(255, color.getBlue());

    }



}

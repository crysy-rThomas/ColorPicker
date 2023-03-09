package com.projet.colorpicker;

/**
 * Classe métier
 */
public class ChooseColor {
    /**
     * Crée une couleur RVB à partir des trois composantes
     * @param red la valeur de rouge
     * @param green la valeur de vert
     * @param blue la valeur de bleu
     * @return un entier 32 bits RRGGBB
     */

    public int createRGB(int red, int green, int blue)
    {
        return 0xFF000000+(red<<16)|(green<<8)|(blue);
    }

    /**
     *
     * @param rgb une couleur rgb 32 bits
     * @return la composante rouge
     */
    public byte red(int rgb)
    {
        return (byte)((rgb&0xFF0000)>>16);
    }

    /**
     *
     * @param rgb une couleur rgb 32 bits
     * @return la composante verte
     */
    public byte green(int rgb)
    {
        return (byte)((rgb&0x00FF00)>>8);
    }

    /**
     *
     * @param rgb une couleur rgb 32 bits
     * @return la composante bleue
     */
    public byte blue(int rgb)
    {
        return (byte)(rgb&0x0000FF);
    }

    /**
     *
     * @param rgb une couleur rgb 32 bits
     * @return la représentation en HTML #RRGGBB
     */
    public String codeHTML(int rgb)
    {
        return String.format("#%02X%02X%02X",red(rgb),green(rgb),blue(rgb));
    }

    public int redHexaToInt(String Hexa){


        return 00;
    }
}


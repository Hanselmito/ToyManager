package com.github.hanselmito.toymanager.Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class ImageToByte {

    /**
     * Convierte una imagen en un arreglo de bytes.
     *
     * @param imagePath Ruta de la imagen a convertir.
     * @return Arreglo de bytes que representa la imagen, o null si ocurre un error.
     */
    public static byte[] imageToBytes(String imagePath) {
        File imageFile = new File(imagePath);
        if (!imageFile.exists()) {
            System.err.println("La imagen no existe: " + imagePath);
            return null;
        }

        try {
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            if (bufferedImage == null) {
                System.err.println("no as puesto una imagen: " + imagePath);
                return null;
            }

            ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", byteOutStream);
            return byteOutStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

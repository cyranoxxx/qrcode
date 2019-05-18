package qrcodeReader;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.List;


public class qrCodeReader {
	
	private static String decodeQRCode(File qrCodeimage) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(qrCodeimage);
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        try {
            Result result = new MultiFormatReader().decode(bitmap);
            return result.getText();
        } catch (NotFoundException e) {
            System.out.println("There is no QR code in the image");
            return null;
        }
    }

    public static void main(String[] args) {
        try {
            File file = new File("c:\\karekod.jpg");
            String decodedText = decodeQRCode(file);
            String cekNo=decodedText.substring(7, 17);
            String bankaCode=decodedText.substring(18, 22);
            String bankaSube=decodedText.substring(23, 28);
            String cekVKN=decodedText.substring(46, 57);
            if(decodedText == null) {
                System.out.println("No QR Code found in the image");
            } else {
                System.out.println("Decoded text = " + decodedText);
                System.out.println("Çek No = " + cekNo);
                System.out.println("VKN No = " + cekVKN);
                System.out.println("Banka Code = " + bankaCode);
                System.out.println("Banka Þube = " + bankaSube);
            }
        } catch (IOException e) {
            System.out.println("Could not decode QR Code, IOException :: " + e.getMessage());
        }
    }
    
    

}

package com.crm.organizecrm.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.commons.imaging.ImageFormats;
import org.apache.commons.imaging.Imaging;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class QRCodeUtil {

    public static void generateQRCodeWithLogo(String qrCodeText, String filePath, byte[] logoBytes) throws WriterException, IOException {
        int size = 400;
        String fileType = "png";
        File qrFile = new File(filePath);

        Map<EncodeHintType, Object> hintMap = new HashMap<>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, size, size, hintMap);
        BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(byteMatrix);

        BufferedImage logoImage = Imaging.getBufferedImage(logoBytes);

        int deltaHeight = qrImage.getHeight() - logoImage.getHeight();
        int deltaWidth = qrImage.getWidth() - logoImage.getWidth();

        BufferedImage combined = new BufferedImage(qrImage.getHeight(), qrImage.getWidth(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) combined.getGraphics();

        g.drawImage(qrImage, 0, 0, null);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        g.drawImage(logoImage, Math.round(deltaWidth / 2), Math.round(deltaHeight / 2), null);

        Imaging.writeImage(combined, qrFile, ImageFormats.PNG);
    }
}

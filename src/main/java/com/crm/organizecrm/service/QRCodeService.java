package com.crm.organizecrm.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.commons.imaging.ImageFormats;
import org.apache.commons.imaging.Imaging;
import org.springframework.stereotype.Service;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class QRCodeService {

    public String generateQRCodeWithLogoBase64(String qrCodeText,String companyName, int width, int height, byte[] logoBytes) throws WriterException, IOException {
        Map<EncodeHintType, ErrorCorrectionLevel> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, width, height, hints);

        BufferedImage logo = Imaging.getBufferedImage(new ByteArrayInputStream(logoBytes));

        BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

        int logoWidth = qrImage.getWidth() / 5;
        int logoHeight = qrImage.getHeight() / 5;
        int x = (qrImage.getWidth() - logoWidth) / 2;
        int y = (qrImage.getHeight() - logoHeight) / 2;

        Graphics2D g = qrImage.createGraphics();
        g.drawImage(logo, x, y, logoWidth, logoHeight, null);
        g.dispose();

        BufferedImage qrCodeWithText = getQRCodeWithText(qrImage, companyName);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Imaging.writeImage(qrCodeWithText, outputStream, ImageFormats.PNG);
        byte[] qrCodeBytes = outputStream.toByteArray();

        return Base64.getEncoder().encodeToString(qrCodeBytes);
    }

    public BufferedImage getQRCodeWithText(BufferedImage qrImage, String text) throws IOException {
        if (!text.isEmpty()) {
            int totalTextLineToAdd = text.length();
            int newWidth = qrImage.getWidth();
            int newHeight = qrImage.getHeight() + 10 * totalTextLineToAdd;
            BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics graphics = newImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, newImage.getWidth(), newImage.getHeight());
            graphics.drawImage(qrImage, 0, 0, qrImage.getWidth(), qrImage.getHeight(), null);
            graphics.setFont(new Font("Arial Black", Font.BOLD, 12));
            Color textColor = Color.BLACK;
            graphics.setColor(textColor);
            FontMetrics fontMetrics = graphics.getFontMetrics();
            int startingYPosition = qrImage.getHeight() + 5;
            graphics.drawString(text, (newImage.getWidth() / 2) - (fontMetrics.stringWidth(text) / 2), startingYPosition);
            qrImage = newImage;
        }
        return qrImage;
    }
}

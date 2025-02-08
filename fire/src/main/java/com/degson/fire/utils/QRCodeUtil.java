package com.degson.fire.utils;

import com.degson.common.utils.StringUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.List;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 二维码工具
 * @Author:debug (SteadyJack)
 * @Link: weixin-> debug0868  qq-> 1948831260
 * @Date: 2020/11/16 22:38
 **/
@Component
public class QRCodeUtil {
//    private static final Logger log= (Logger) LoggerFactory.getLogger(QRCodeUtil.class);

    //CODE_WIDTH：二维码宽度，单位像素
    private static final int CODE_WIDTH = 400;
    //CODE_HEIGHT：二维码高度，单位像素
    private static final int CODE_HEIGHT = 400;
    //FRONT_COLOR：二维码前景色，0x000000 表示黑色
    private static final int FRONT_COLOR = 0x000000;
    //BACKGROUND_COLOR：二维码背景色，0xFFFFFF 表示白色
    //演示用 16 进制表示，和前端页面 CSS 的取色是一样的，注意前后景颜色应该对比明显，如常见的黑白
    private static final int BACKGROUND_COLOR = 0xFFFFFF;
    // 中间图片的路径
    private static final String imagePath = "resources/images/**.png";

    public static void createCodeToFile(String content, File codeImgFileSaveDir, String fileName) {
        try {
            if (StringUtils.isBlank(content) || StringUtils.isBlank(fileName)) {
                return;
            }
            content = content.trim();
            if (codeImgFileSaveDir==null || codeImgFileSaveDir.isFile()) {
                //二维码图片存在目录为空，默认放在桌面...
                codeImgFileSaveDir = FileSystemView.getFileSystemView().getHomeDirectory();
            }
            if (!codeImgFileSaveDir.exists()) {
                //二维码图片存在目录不存在，开始创建...
                codeImgFileSaveDir.mkdirs();
            }

            //核心代码-生成二维码
            BufferedImage bufferedImage = getBufferedImage(content);

            File codeImgFile = new File(codeImgFileSaveDir, fileName);
            ImageIO.write(bufferedImage, "png", codeImgFile);

//            log.info("二维码图片生成成功：" + codeImgFile.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* 生成二维码并输出到输出流, 通常用于输出到网页上进行显示，输出到网页与输出到磁盘上的文件中，区别在于最后一句 ImageIO.write
     * write(RenderedImage im,String formatName,File output)：写到文件中
     * write(RenderedImage im,String formatName,OutputStream output)：输出到输出流中
     * @param content  ：二维码内容
     * @param outputStream ：输出流，比如 HttpServletResponse 的 getOutputStream   */
    public static void createCodeToOutputStream(String content, OutputStream outputStream) {
        try {
            if (StringUtils.isBlank(content)) {
                return;
            }
            content = content.trim();
            //核心代码-生成二维码
            BufferedImage bufferedImage = getBufferedImage(content);

            //区别就是这一句，输出到输出流中，如果第三个参数是 File，则输出到文件中
            ImageIO.write(bufferedImage, "png", outputStream);

//            log.info("二维码图片生成到输出流成功...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //这里是发到前端一个base64的字符串，
//前端    this.base64Data = "data:img/jpg;base64," + get_to_url.data.fxORCode 这样接受即可
    public static String createCodeToOutputStream(String content) throws WriterException{
        String encode = null;
        try {
            if (StringUtils.isBlank(content)) {
                return null;
            }
            content = content.trim();
            //核心代码-生成二维码
            BufferedImage bufferedImage = getBufferedImage(content);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            //区别就是这一句，输出到输出流中，如果第三个参数是 File，则输出到文件中
            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
            BASE64Encoder base64Encoder = new BASE64Encoder();
            encode = base64Encoder.encode(byteArrayOutputStream.toByteArray());
//            log.info("二维码图片生成到输出流成功...");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(encode);
        return encode;
    }

    public static List<String> generateStreamList(List<String> texts) throws WriterException, IOException {
//        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        List<String> imagePaths = new ArrayList<>();

        String encode = null;
        for (String text : texts) {
            if (StringUtils.isBlank(text)) {
                return null;
            }
            text = text.trim();
            //核心代码-生成二维码
            BufferedImage bufferedImage = getBufferedImage(text);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            //区别就是这一句，输出到输出流中，如果第三个参数是 File，则输出到文件中
            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
            BASE64Encoder base64Encoder = new BASE64Encoder();
            encode = base64Encoder.encode(byteArrayOutputStream.toByteArray());
            imagePaths.add(encode);

//            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);
//            Path tempDir = Paths.get("qrcode-images");
//            File tempFile = Files.createTempFile(tempDir, "qrcode_", ".png").toFile();
//            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", tempFile.toPath());
//            imagePaths.add(tempFile.getAbsolutePath());
        }
        return imagePaths;
    }


    //核心代码-生成二维码
    public static BufferedImage getBufferedImage(String content) throws WriterException {

        //com.google.zxing.EncodeHintType：编码提示类型,枚举类型
        Map<EncodeHintType, Object> hints = new HashMap();

        //EncodeHintType.CHARACTER_SET：设置字符编码类型
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        //EncodeHintType.ERROR_CORRECTION：设置误差校正
        //ErrorCorrectionLevel：误差校正等级，L = ~7% correction、M = ~15% correction、Q = ~25% correction、H = ~30% correction
        //不设置时，默认为 L 等级，等级不一样，生成的图案不同，但扫描的结果是一样的
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);

        //EncodeHintType.MARGIN：设置二维码边距，单位像素，值越小，二维码距离四周越近
        hints.put(EncodeHintType.MARGIN, 1);

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, CODE_WIDTH, CODE_HEIGHT, hints);
        BufferedImage bufferedImage = new BufferedImage(CODE_WIDTH, CODE_HEIGHT, BufferedImage.TYPE_INT_BGR);
        for (int x = 0; x < CODE_WIDTH; x++) {
            for (int y = 0; y < CODE_HEIGHT; y++) {
                bufferedImage.setRGB(x, y, bitMatrix.get(x, y) ? FRONT_COLOR : BACKGROUND_COLOR);
            }
        }
//QRImgCenter(bufferedImage);//如果需要添加中间小图标，这里放开即可
        return bufferedImage;
    }


    //创建中间图片
    private static BufferedImage QRImgCenter(BufferedImage bufferedImage) throws IOException {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attr.getRequest();
        // 得到一个Graphics2D对象，并将其设置为输出图像
        Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.drawImage(bufferedImage, 0, 0, null);
        // 设置中间图片
        BufferedImage watermarkImage = ImageIO.read(new File(request.getServletContext().getRealPath("/")  + imagePath));
        int watermarkWidth = watermarkImage.getWidth();
        int watermarkHeight = watermarkImage.getHeight();
        int x = (bufferedImage.getWidth() - watermarkWidth) / 2;
        int y = (bufferedImage.getHeight() - watermarkHeight) / 2;
        graphics2D.drawImage(watermarkImage, x, y, null);
        graphics2D.drawString("", x, y);
        return bufferedImage;
    }
}

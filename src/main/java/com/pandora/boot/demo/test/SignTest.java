package com.pandora.boot.demo.test;

import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class SignTest {
    private static final int CANVAS_SIZE = 300; // 画布宽

    private static final String FONT_FAMILY = "宋体"; // 字体

    private static final String STAR = "★";//五角星

    private static int STAR_SIZE = (int) (CANVAS_SIZE / 3.2); // 五角星大小

    /**
     * 生成电子签章，bufferedImage形式
     * @return
     */
    public static BufferedImage drawElectronicSignature() {
        //公司名称
        String companyName = "测试公司";
        //签章类型
        String typeName = "";
        //签章编码
        String codeName = "";

        int companyNameFontSize = 35;
        int typeNameFontSize =20;
        int codeNameFontSize = 20;

        BufferedImage bi = new BufferedImage(CANVAS_SIZE, CANVAS_SIZE, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2d = bi.createGraphics();
        g2d.setColor(Color.RED);//画笔颜色
        g2d.setStroke(new BasicStroke(5));//画笔线宽
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

        int circleRadius = CANVAS_SIZE / 2 - 8;
        //画圆
        drawCicle(g2d, circleRadius);
        //画五角星
        drawStar(g2d, circleRadius);
        //画标题
        drawTypeText(typeName, typeNameFontSize, g2d, circleRadius);
        //画顶部
        drwaCompanyText(companyName, companyNameFontSize, g2d, circleRadius);
        //画底部
        if(codeName!=null && !"".equals(codeName)){
            drwaCodeText(codeName, codeNameFontSize, g2d, circleRadius);
        }
        File file = new File("C:\\Users\\Administrator\\Desktop\\2.png");
        try {
            ImageIO.write(bi, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        g2d.dispose();
        return bi;
    }

    public static void main(String[] args) {
        drawElectronicSignature();
    }


    /**
     * 生成印章主方法
     * @param sealName 印章主名称 如“中科美络信息有限公司”
     * @param tipName 印章标题，如"合同章"，“财务章”
     * @param footName 底部编码
     * @param sealSize 主名称字体大小，默认35
     * @param tipSize 标题字体大小，默认20
     * @param footSize 底部编码字体大小，默认14
     * @return
     */
    public static BufferedImage initSeal(String sealName, String tipName, String footName, Integer sealSize, Integer tipSize, Integer footSize) {
        BufferedImage bi = new BufferedImage(CANVAS_SIZE, CANVAS_SIZE, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2d = bi.createGraphics();
        g2d.setColor(Color.RED);//画笔颜色
        g2d.setStroke(new BasicStroke(5));//画笔线宽
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int circleRadius = CANVAS_SIZE / 2 - 8;
        //画圆
        drawCicle(g2d, circleRadius);
        //画五角星
        drawStar(g2d, circleRadius);
        //画标题
        drawTypeText(tipName, tipSize, g2d, circleRadius);
        //画顶部
        drwaCompanyText(sealName, sealSize, g2d, circleRadius);
        //画底部
        if(footName!=null && !"".equals(footName)){
            drwaCodeText(footName,footSize,g2d,circleRadius);
        }
        g2d.dispose();
        return bi;
    }

    /**
     * 弧形绘制顶部名称
     *
     * @param g2d
     */
    private static void drwaCodeText(String footName, Integer footSize, Graphics2D g2d,double radius) {
        int CENTERX = CANVAS_SIZE / 2;
        int CENTERY = CANVAS_SIZE / 2;
        //根据输入字符串得到字符数组
        String[] messages = new String[footName.length()];
        for(int i=0;i<footName.length();i++){
            messages[i] = String.valueOf(footName.charAt(i));
        }
        int ilength = messages.length;//输入的字数
        Font f = new Font(FONT_FAMILY, Font.LAYOUT_LEFT_TO_RIGHT, footSize == null ? 16 : footSize);//设置字体属性,字体及大小

        FontRenderContext context = g2d.getFontRenderContext();
        Rectangle2D bounds = f.getStringBounds(footName, context);

        //字符宽度＝字符串长度/字符数
        double char_interval = (bounds.getWidth() / ilength);
        //上坡度
        double ascent = bounds.getY() - 7;
        int first = 0, second = 0;
        boolean odd = false;
        if (ilength % 2 == 1) {
            first = (ilength - 1) / 2;
            odd = true;
        } else {
            first = (ilength) / 2 - 1;
            second = (ilength) / 2;
            odd = false;
        }

        double radius2 = -radius + ascent;
        double x0 = CENTERX;
        double y0 = 2*radius;
        //旋转角度
        double a = 2 * Math.asin(char_interval / (2 * radius2));
        if (odd) {
            g2d.setFont(f);
            g2d.drawString(messages[first], (float) (x0 - char_interval / 2), (float) y0);

            //中心点的右边
            for (int i = first + 1; i < ilength; i++) {
                double aa = (i - first) * a;
                double ax = radius2 * Math.sin(aa);
                double ay = radius2 - radius2 * Math.cos(aa);
                AffineTransform transform = AffineTransform.getRotateInstance(aa);//,x0 + ax, y0 + ay);
                Font f2 = f.deriveFont(transform);
                g2d.setFont(f2);
                g2d.drawString(messages[i], (float) (x0 + ax - char_interval / 2 * Math.cos(aa)), (float) (y0 + ay - char_interval / 2 * Math.sin(aa)));
            }
            //中心点的左边
            for (int i = first - 1; i > -1; i--) {
                double aa = (first - i) * a;
                double ax = radius2 * Math.sin(aa);
                double ay = radius2 - radius2 * Math.cos(aa);
                AffineTransform transform = AffineTransform.getRotateInstance(-aa);//,x0 + ax, y0 + ay);
                Font f2 = f.deriveFont(transform);
                g2d.setFont(f2);
                g2d.drawString(messages[i], (float) (x0 - ax - char_interval / 2 * Math.cos(aa)), (float) (y0 + ay + char_interval / 2 * Math.sin(aa)));
            }

        } else {
            //中心点的右边
            for (int i = second; i < ilength; i++) {
                double aa = (i - second + 0.5) * a;
                double ax = radius2 * Math.sin(aa);
                double ay = radius2 - radius2 * Math.cos(aa);
                AffineTransform transform = AffineTransform.getRotateInstance(aa);//,x0 + ax, y0 + ay);
                Font f2 = f.deriveFont(transform);
                g2d.setFont(f2);
                g2d.drawString(messages[i], (float) (x0 + ax - char_interval / 2 * Math.cos(aa)), (float) (y0 + ay - char_interval / 2 * Math.sin(aa)));
            }

            //中心点的左边
            for (int i = first; i > -1; i--) {
                double aa = (first - i + 0.5) * a;
                double ax = radius2 * Math.sin(aa);
                double ay = radius2 - radius2 * Math.cos(aa);
                AffineTransform transform = AffineTransform.getRotateInstance(-aa);//,x0 + ax, y0 + ay);
                Font f2 = f.deriveFont(transform);
                g2d.setFont(f2);
                g2d.drawString(messages[i], (float) (x0 - ax - char_interval / 2 * Math.cos(aa)), (float) (y0 + ay + char_interval / 2 * Math.sin(aa)));
            }
        }
    }
    /**
     * 弧形绘制顶部名称
     *
     * @param sealName
     * @param g2d
     */
    private static void drwaCompanyText(String sealName, Integer sealSize, Graphics2D g2d,double radius) {
        int CENTERX = CANVAS_SIZE / 2;
        int CENTERY = CANVAS_SIZE / 2;
        //根据输入字符串得到字符数组
        String[] messages = new String[sealName.length()];
        for(int i=0;i<sealName.length();i++){
            messages[i] = String.valueOf(sealName.charAt(i));
        }
        int ilength = messages.length;//输入的字数
        Font f = new Font(FONT_FAMILY, Font.BOLD, sealSize == null ? 35 : sealSize);//设置字体属性,字体及大小

        FontRenderContext context = g2d.getFontRenderContext();
        Rectangle2D bounds = f.getStringBounds(sealName, context);

        //字符宽度＝字符串长度/字符数
        double char_interval = (bounds.getWidth() / ilength);
        //上坡度
        double ascent = -bounds.getY() + 7;
        int first = 0, second = 0;
        boolean odd = false;
        if (ilength % 2 == 1) {
            first = (ilength - 1) / 2;
            odd = true;
        } else {
            first = (ilength) / 2 - 1;
            second = (ilength) / 2;
            odd = false;
        }

        double radius2 = radius - ascent;
        double x0 = CENTERX;
        double y0 = CENTERY - radius + ascent;
        //旋转角度
        double a = 2 * Math.asin(char_interval / (2 * radius2));
        if (odd) {
            g2d.setFont(f);
            g2d.drawString(messages[first], (float) (x0 - char_interval / 2), (float) y0);

            //中心点的右边
            for (int i = first + 1; i < ilength; i++) {
                double aa = (i - first) * a;
                double ax = radius2 * Math.sin(aa);
                double ay = radius2 - radius2 * Math.cos(aa);
                AffineTransform transform = AffineTransform.getRotateInstance(aa);//,x0 + ax, y0 + ay);
                Font f2 = f.deriveFont(transform);
                g2d.setFont(f2);
                g2d.drawString(messages[i], (float) (x0 + ax - char_interval / 2 * Math.cos(aa)), (float) (y0 + ay - char_interval / 2 * Math.sin(aa)));
            }
            //中心点的左边
            for (int i = first - 1; i > -1; i--) {
                double aa = (first - i) * a;
                double ax = radius2 * Math.sin(aa);
                double ay = radius2 - radius2 * Math.cos(aa);
                AffineTransform transform = AffineTransform.getRotateInstance(-aa);//,x0 + ax, y0 + ay);
                Font f2 = f.deriveFont(transform);
                g2d.setFont(f2);
                g2d.drawString(messages[i], (float) (x0 - ax - char_interval / 2 * Math.cos(aa)), (float) (y0 + ay + char_interval / 2 * Math.sin(aa)));
            }

        } else {
            //中心点的右边
            for (int i = second; i < ilength; i++) {
                double aa = (i - second + 0.5) * a;
                double ax = radius2 * Math.sin(aa);
                double ay = radius2 - radius2 * Math.cos(aa);
                AffineTransform transform = AffineTransform.getRotateInstance(aa);//,x0 + ax, y0 + ay);
                Font f2 = f.deriveFont(transform);
                g2d.setFont(f2);
                g2d.drawString(messages[i], (float) (x0 + ax - char_interval / 2 * Math.cos(aa)), (float) (y0 + ay - char_interval / 2 * Math.sin(aa)));
            }

            //中心点的左边
            for (int i = first; i > -1; i--) {
                double aa = (first - i + 0.5) * a;
                double ax = radius2 * Math.sin(aa);
                double ay = radius2 - radius2 * Math.cos(aa);
                AffineTransform transform = AffineTransform.getRotateInstance(-aa);//,x0 + ax, y0 + ay);
                Font f2 = f.deriveFont(transform);
                g2d.setFont(f2);
                g2d.drawString(messages[i], (float) (x0 - ax - char_interval / 2 * Math.cos(aa)), (float) (y0 + ay + char_interval / 2 * Math.sin(aa)));
            }
        }
    }

    /**
     * 画标题
     *
     * @param g2d
     * @param circleRadius
     */
    private static void drawTypeText(String tipName, Integer tipSize, Graphics2D g2d, int circleRadius) {
        Font f;
        FontRenderContext context;
        Rectangle2D rectangle;
        f = new Font(FONT_FAMILY, Font.BOLD, tipSize==null?20:tipSize);
        context = g2d.getFontRenderContext();
        rectangle = f.getStringBounds(tipName, context);
        int a = 10 * CANVAS_SIZE / 350; // 底部文字距中心的距离，这是调出来的
        g2d.setFont(f);
        g2d.drawString(tipName, (float) (circleRadius + a - rectangle.getCenterX()), (float) (circleRadius * 1.5 + a - rectangle.getCenterY()));
    }

    /**
     * 画中心的 ★
     *
     * @param g2d
     * @param circleRadius
     */
    private static void drawStar(Graphics2D g2d, int circleRadius) {
        Font f = new Font(FONT_FAMILY, Font.BOLD, STAR_SIZE);
        FontRenderContext context = g2d.getFontRenderContext();
        Rectangle2D rectangle = f.getStringBounds(STAR, context);
        g2d.setFont(f);
        g2d.drawString(STAR, (float) (circleRadius + 10 - rectangle.getCenterX()), (float) (circleRadius + 10 - rectangle.getCenterY()));
    }

    /**
     * 画外圈圆
     *
     * @param g2d
     * @param circleRadius
     */
    private static void drawCicle(Graphics2D g2d, int circleRadius) {
        Ellipse2D circle = new Ellipse2D.Double();
        circle.setFrameFromCenter(CANVAS_SIZE / 2, CANVAS_SIZE / 2, CANVAS_SIZE - 5, CANVAS_SIZE - 5);
        g2d.draw(circle);
    }

    //将图片转成base64编码
    public static String convertBufferedImageToBase64(BufferedImage image){
        BASE64Encoder encoder = new sun.misc.BASE64Encoder();
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            byte[] bytes = baos.toByteArray();
            return encoder.encodeBuffer(bytes).trim();
        } catch (IOException e) {
        }
        return null;
    }

    /**
     * 获取电子签章base64图片
     * @param electronicSignatureDTO
     * @return
     */
    /*public static String getElectronicSignatureWithBase64Image(ElectronicSignatureDTO electronicSignatureDTO) {
        BufferedImage bufferedImage = drawElectronicSignature(electronicSignatureDTO);
        String base64Image = convertBufferedImageToBase64(bufferedImage);
        return base64Image;
    }*/
}

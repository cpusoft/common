package tk.cpusoft.common.util.qr;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;


/**
 * @desc 二维码工具类
 * @date 2015年10月10日-下午4:13:53
 */
public class QRCodeUtil {


    /**
     * @desc 生成二维码，有内嵌LOGO，到文件中
     * @date 2015年10月10日-下午4:14:54
     * @param content  内容
     * @param imgPath       logo图片路径
     * @param destPath      目标目录
     * @param destFileName  目标文件名(无后缀)
     * @param charset       编码("UTF-8")
     * @param formatName    目标文件后缀("png")
     * @param width         宽
     * @param height        高
     * @param logoWidth     logo的宽
     * @param logoHeight    log的长
     * @throws Exception void 
     */
    public static void encodeToFile(String content, String imgPath, String destPath,String destFileName,String charset,String formatName,
            int width,int height,int logoWidth,int logoHeight)
                    throws Exception {
        QRCodeUtil.encodeToFile(content, imgPath, destPath, false, destFileName, charset, formatName,
                width, height, logoWidth, logoHeight, 0);
    }

    /**
     * @desc 生成二维码，无内嵌logo,到文件中
     * @date 2015年10月10日-下午4:09:46
     * @param content 内容
     * @param destPath   目标目录
     * @param destFileName 目标文件名(不带后缀)
     * @param charset           编码(一般"UTF-8")
     * @param formatName    图片格式(一般"png")
     * @param width  二维码长
     * @param height    二维码宽
     * @throws Exception void 
     */
    public static void encodeToFile(String content, String destPath,String destFileName,String charset,String formatName,
            int width,int height) throws Exception {
        QRCodeUtil.encodeToFile(content, null, destPath, false,destFileName, charset, formatName,
                width, height, 0, 0, 0);
    }
 
    /**
     * @desc 生成文件到byte[]数组中，有内嵌logo
     * @date 2015年10月10日-下午4:16:40
     * @param content  待生成的内容
     * @param imgPath   logo路径文件名
     * @param charset   编码("UTF-8")
     * @param formatName  图片格式("png")
     * @param width     宽
     * @param height    长
     * @param logoWidth  logo宽
     * @param logoHeight  logo长
     * @return  byte[] 
     * @throws Exception 
     */
    public static byte[] encode2Byte(String content,String imgPath,  String charset,String formatName,
            int width,int height,int logoWidth,int logoHeight,int margin) throws Exception {
        return QRCodeUtil.encode2Byte(content, imgPath, false, charset, formatName,
                width, height, logoWidth, logoHeight, margin);
    }

    
    /**
     * @desc 生成文件到byte[]数组中，无logo
     * @date 2015年10月10日-下午3:56:50
     * @param content  待生成的内容
     * @param charset   编码("UTF-8")
     * @param formatName  图片格式("png")
     * @param width       宽
     * @param height      长
     * @return  byte[]
     * @throws Exception  
     */
    public static byte[] encode2Byte(String content, String charset,String formatName,
            int width,int height,int margin) throws Exception {
        return QRCodeUtil.encode2Byte(content, null, false, charset, formatName,
                width, height, 0, 0, margin);
    }



    /**
     * @desc 解析二维码
     * @date 2015年10月10日-下午4:19:01
     * @param file  待解析的二维码文件
     * @param charsetName  编码UTF-8
     * @return String 二维码内容
     * @throws Exception  
     */
    public static String decode(File file,String charsetName) throws Exception {
        BufferedImage image;
        image = ImageIO.read(file);
        if (image == null) {
            return null;
        }
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(
                image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result;
        Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();
        hints.put(DecodeHintType.CHARACTER_SET, charsetName);
        result = new MultiFormatReader().decode(bitmap, hints);
        String resultStr = result.getText();
        return resultStr;
    }





    /**
     * @desc 生成图片
     * @Title: createImage 
     * @Description: TODO
     * @param  content    内容
     * @param  imgPath    logo图片路径
     * @param  destPath   目标目录
     * @param  needCompress   logo是否需要压缩
     * @param  destFileName   目标文件名
     * @param  charset    编码
     * @param  formatName   二维码图片格式
     * @param  width        二维码宽度
     * @param  height       二维码高度
     * @param  logoWidth    logo宽度
     * @param  logoHeight   logo高度
     * @param  margin       边框大小
     * @param @throws Exception     
     * @return void    返回类型 
     * @throws
     */
    private static BufferedImage createImage(String content, String imgPath,
            boolean needCompress,String destFileName,String charset,String formatName,
            int width,int height,int logoWidth,int logoHeight,int margin) throws Exception {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        hints.put(EncodeHintType.CHARACTER_SET, charset);
        hints.put(EncodeHintType.MARGIN, margin);
        BitMatrix bitMatrixOld = new MultiFormatWriter().encode(content,
                BarcodeFormat.QR_CODE, width, height, hints);
        BitMatrix bitMatrix = deleteWhite( bitMatrixOld );
        int widthTmp = bitMatrix.getWidth();
        int heightTmp = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(widthTmp, heightTmp,
                BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < widthTmp; x++) {
            for (int y = 0; y < heightTmp; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000
                        : 0xFFFFFFFF);
            }
        }
        if (imgPath == null || "".equals(imgPath)) {
            return image;
        }
        // 插入图片
        QRCodeUtil.insertImage(image, imgPath, needCompress,width,height,logoWidth,logoHeight);
        return image;
    }
    private static BufferedImage createImage(String content,
            boolean needCompress,String charset,
            int width,int height,int logoWidth,int logoHeight,int margin) throws Exception {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        hints.put(EncodeHintType.CHARACTER_SET, charset);
        hints.put(EncodeHintType.MARGIN, margin);
        BitMatrix bitMatrixOld = new MultiFormatWriter().encode(content,
                BarcodeFormat.QR_CODE, width, height, hints);
        BitMatrix bitMatrix = deleteWhite( bitMatrixOld );
        int widthTmp = bitMatrix.getWidth();
        int heightTmp = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(widthTmp, heightTmp,
                BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < widthTmp; x++) {
            for (int y = 0; y < heightTmp; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000
                        : 0xFFFFFFFF);
            }
        }
        return image;
    }
    /**
     * 删除zxing的二维码的白边
     * @param matrix
     * @return
     */
    private static BitMatrix deleteWhite(BitMatrix matrix){
        int[] rec = matrix.getEnclosingRectangle();
        int resWidth = rec[2] + 1;
        int resHeight = rec[3] + 1;

        BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
        resMatrix.clear();
        for (int i = 0; i < resWidth; i++) {
            for (int j = 0; j < resHeight; j++) {
                if (matrix.get(i + rec[0], j + rec[1]))
                    resMatrix.set(i, j);
            }
        }
        return resMatrix;
    }
    /**
     * 插入LOGO
     * @Title: insertImage 
     * @Description: TODO
     * @param  source  二维码图片
     * @param  imgPath logo路径
     * @param  needCompress logo是否压缩
     * @param  width  二维码图片宽度
     * @param  height 二维码图片高度
     * @param  logoWidth  logo宽度
     * @param  logoHeight logo高度
     * @param @throws Exception    设定文件 
     * @return void    返回类型 
     * @throws
     */
    private static void insertImage(BufferedImage source, String imgPath,
            boolean needCompress,int width,int height,int logoWidth,int logoHeight) throws Exception {
        File file = new File(imgPath);
        if (!file.exists()) {
            System.err.println(""+imgPath+"   该文件不存在！");
            return;
        }
        Image src = ImageIO.read(new File(imgPath));
        int widthTmp = src.getWidth(null);
        int heightTmp = src.getHeight(null);
        if (needCompress) { // 压缩LOGO
            if (widthTmp > logoWidth) {
                widthTmp = logoWidth;
            }
            if (heightTmp > logoHeight) {
                heightTmp = logoHeight;
            }
            Image image = src.getScaledInstance(widthTmp, heightTmp,
                    Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(widthTmp, heightTmp,
                    BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图
            g.dispose();
            src = image;
        }
        // 插入LOGO
        Graphics2D graph = source.createGraphics();
        int x = (width - widthTmp) / 2;
        int y = (height - heightTmp) / 2;
        graph.drawImage(src, x, y, widthTmp, heightTmp, null);
        Shape shape = new RoundRectangle2D.Float(x, y, widthTmp, widthTmp, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }
    /**
     * 当文件夹不存在时，mkdirs会自动创建多层目录
     * @param destPath 存放目录
     */
    private static void mkdirs(String destPath) {
        File file =new File(destPath);    
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
    }

    /**
     * 生成二维码到文件中
     * @Title: encode 
     * @Description: TODO
     * @param  content    内容
     * @param  imgPath    logo图片路径
     * @param  destPath   目标目录
     * @param  needCompress   logo是否需要压缩
     * @param  destFileName   目标文件名
     * @param  charset    编码
     * @param  formatName   二维码图片格式
     * @param  width        二维码宽度
     * @param  height       二维码高度
     * @param  logoWidth    logo宽度
     * @param  logoHeight   logo高度
     * @param  margin       边框大小
     * @param @throws Exception     
     * @return void    返回类型 
     * @throws
     */
    private static void encodeToFile(String content, String imgPath, String destPath,
            boolean needCompress,String destFileName,String charset,String formatName,
            int width,int height,int logoWidth,int logoHeight,int margin) throws Exception {
        BufferedImage image = QRCodeUtil.createImage(content, imgPath,
                needCompress, destFileName, charset, formatName,
                width, height, logoWidth, logoHeight, margin);
        mkdirs(destPath);
        String file = destFileName+"."+formatName;
        ImageIO.write(image, formatName, new File(destPath+"/"+file));
    }

    /**
     * 生成二维码到byte[]数组中
     * @Title: encode 
     * @Description: TODO
     * @param  content    内容
     * @param  imgPath    logo图片路径
     * @param  destPath   目标目录
     * @param  needCompress   logo是否需要压缩
     * @param  destFileName   目标文件名
     * @param  charset    编码
     * @param  formatName   二维码图片格式
     * @param  width        二维码宽度
     * @param  height       二维码高度
     * @param  logoWidth    logo宽度
     * @param  logoHeight   logo高度
     * @param  margin       边框大小
     * @param @throws Exception     
     * @return void    返回类型 
     * @throws
     */
    private static byte[] encode2Byte(String content, String imgPath, 
            boolean needCompress,String charset,String formatName,
            int width,int height,int logoWidth,int logoHeight,int margin) throws Exception {
        BufferedImage image = QRCodeUtil.createImage(content, needCompress, charset, 
                width, height, logoWidth, logoHeight, margin);
        ByteArrayOutputStream out = new ByteArrayOutputStream();  
        ImageIO.write(image, formatName, out);  
        byte[] b = out.toByteArray();
        return b;
    }


    //		private static final int BLACK = 0xff000000;
    //
    //		private static final int PADDING_SIZE_MIN = 20; // 最小留白长度, 单位: px
    //
    //		public static Bitmap createQRCode(String str, int widthAndHeight) throws WriterException {
    //			BitMap b = new BitMap();
    //			Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
    //			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
    //			BitMatrix matrix = new MultiFormatWriter().encode(str,
    //					BarcodeFormat.QR_CODE, widthAndHeight, widthAndHeight, hints);
    //
    //			int width = matrix.getWidth();
    //			int height = matrix.getHeight();
    //			int[] pixels = new int[width * height];
    //
    //			boolean isFirstBlackPoint = false;
    //			int startX = 0;
    //			int startY = 0;
    //
    //			for (int y = 0; y < height; y++) {
    //				for (int x = 0; x < width; x++) {
    //					if (matrix.get(x, y)) {
    //						if (isFirstBlackPoint == false)
    //						{
    //							isFirstBlackPoint = true;
    //							startX = x;
    //							startY = y;
    //							Log.d("createQRCode", "x y = " + x + " " + y);
    //						}
    //						pixels[y * width + x] = BLACK;
    //					}
    //				}
    //			}
    //
    //			Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
    //			bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
    //
    //			// 剪切中间的二维码区域，减少padding区域
    //			if (startX <= PADDING_SIZE_MIN) return bitmap;
    //
    //			int x1 = startX - PADDING_SIZE_MIN;
    //			int y1 = startY - PADDING_SIZE_MIN;
    //			if (x1 < 0 || y1 < 0) return bitmap;
    //
    //			int w1 = width - x1 * 2;
    //			int h1 = height - y1 * 2;
    //
    //			Bitmap bitmapQR = Bitmap.createBitmap(bitmap, x1, y1, w1, h1);
    //
    //			return bitmapQR;
    //		}


}

package tk.cpusoft.common.util.test;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

import net.coobird.thumbnailator.Thumbnails;

import org.testng.annotations.Test;

import tk.cpusoft.common.util.compress.ImageCompressUtil;

public class ImageCompressUtilTest {

    @Test
    public void test() throws Exception{
        File f = new File("E:\\logs\\1.jpg");
        float size = f.length();
        float percent = 100*1000/size;
        byte[] b = ImageCompressUtil.compressOfQuality(f, percent);

        File newF = new File("E:\\logs\\1-a.jpg");

        newF.createNewFile();

        FileOutputStream  fos = new FileOutputStream(newF);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        bos.write(b);
        bos.close();
        fos.close();

    }

    @Test
    public void test2() throws IOException{
        BufferedImage bi =  ImageIO.read(new File("E:\\logs\\1.bmp")); 
        int width = bi.getWidth();
        int height = bi.getHeight();

        Thumbnails.of("E:\\logs\\1.bmp").size(1280, 1024).outputFormat("png")
        .toFile("E:\\logs\\1.png");
        ByteArrayOutputStream output = null;    
        Thumbnails.of("E:\\logs\\1.bmp").size(1280, 1024).outputFormat("png")
        .toOutputStream(output);
        ByteArrayInputStream swapStream = new ByteArrayInputStream(output.toByteArray());
    }

    @Test
    public void test3() throws IOException{
        File f = new File("E:\\logs\\1.doc");
        InputStream inputStream =  new FileInputStream(f);
        ImageInputStream iis = ImageIO.createImageInputStream(inputStream);
        Iterator iter = ImageIO.getImageReaders(iis);
        if (!iter.hasNext()) {
            System.out.println("不图片");
            return;
        }
        System.out.println("是图片");
    }

    @Test
    public void test4() throws Exception{

        File f = new File("E:\\logs\\1.bmp");
        InputStream inputStream =  new FileInputStream(f);
        InputStream i2 = ImageCompressUtil.transferPicFormat(inputStream, "png");


        File newF = new File("E:\\logs\\1-a.png");

        newF.createNewFile();
        FileOutputStream  fos = new FileOutputStream(newF);
        byte[] b = new byte[1024];
        while (i2.read(b) != -1) {
            fos.write(b);
        }
        fos.close();

    }
}

package tk.cpusoft.common.util.uuid;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

public class CheckCode {

  private int imageWidth = 130;
  private int imageHeight = 45;
  private String imageContent;
  private OutputStream out;

  public void create(String uuid) throws Exception {
    generateImageContent(uuid);
    creatImage();
  }

  /**
   * 通过文件创建图像 格式为jpeg类型
   */
  private void creatImage() throws Exception {
    Random random = new Random();
    // 在内存中创建图象

    BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
    // 获取图形上下文
    Graphics2D g = image.createGraphics();
    // 设定背景色
    g.setColor(getRandColor(200, 250));
    g.fillRect(0, 0, imageWidth, imageHeight);
    // 设定字体
    // g.setFont(new Font("Times New Roman", Font.PLAIN, 20));
    g.setFont(new Font("Arial", Font.PLAIN, 30));
    g.setColor(getRandColor(102, 150));
    g.drawString(imageContent, 5, 40);
    // 随机产生干扰线，使图象中的认证码不易被其它程序探测到
    g.setColor(getRandColor(170, 190));
    for (int i = 0; i < 10; i++) {
      int x = random.nextInt(imageWidth);
      int y = random.nextInt(imageHeight);
      int xl = random.nextInt(50);
      int yl = random.nextInt(50);
      g.drawLine(x, y, x + xl, y + yl);
    }
    g.dispose();

    image.flush();
    ImageIO.write(image, "JPEG", out);
  }

  /**
   * 获取随机数字 返回一个4位的验证码
   */
  private void generateImageContent(String uuid) {
    /*String content = "";
    Random random = new Random();
    while (content.length() < 4) {
      int rdGet = Math.abs(random.nextInt()) % 10 + 48; // 产生48到57的随机数(0-9的键位值)
      // rdGet=Math.abs(rd.nextInt())%26+97; //产生97到122的随机数(a-z的键位值)
      char ch = (char) rdGet;
      content += ch;
    }*/

    imageContent = uuid;
  }

  /**
   * 给定范围获得随机颜色
   */
  private Color getRandColor(int fc, int bc) {
    Random random = new Random();
    if (fc > 255)
      fc = 255;
    if (bc > 255)
      bc = 255;
    int r = fc + random.nextInt(bc - fc);
    int g = fc + random.nextInt(bc - fc);
    int b = fc + random.nextInt(bc - fc);

    return new Color(r, g, b);
  }

  public int getImageWidth() {
    return imageWidth;
  }

  public void setImageWidth(int imageWidth) {
    this.imageWidth = imageWidth;
  }

  public int getImageHeight() {
    return imageHeight;
  }

  public void setImageHeight(int imageHeight) {
    this.imageHeight = imageHeight;
  }

  public String getImageContent() {
    return imageContent;
  }

  public OutputStream getOut() {
    return out;
  }

  public void setOut(OutputStream out) {
    this.out = out;
  }

}

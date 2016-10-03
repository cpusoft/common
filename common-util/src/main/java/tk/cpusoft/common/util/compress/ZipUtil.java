/*
 * @(#)ZipUtil.java
 *
 * Copyright 2012 北龙中网（北京）科技有限责任公司. All rights reserved.
 */
package tk.cpusoft.common.util.compress;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * 提供ZIP文件的压缩与解压
 *
 * @author zhangdongfang
 * @version 2012-6-26 上午11:23:16
 */
public final class ZipUtil {
    
    private static Logger logger = LoggerFactory.getLogger(ZipUtil.class);

    /**
     * 缓冲
     */
    private static int BUFFER_SIZE = 2048*3;
    /**
     * 每次读缓冲的数
     */
    private static final int READ_BUFFER_SIZE = BUFFER_SIZE;

    private ZipUtil(){
    }
    /**
     * 
     * 方法说明。
     * @param bufferSize
     * @author zhangdongfang
     */
    @SuppressWarnings("static-access")
    public void setBufferSize(int bufferSize) {
        this.BUFFER_SIZE = bufferSize;
    }

    /**
     * 
     * 压缩zip文件
     * @param filename要压缩的文件
     * @param outputPath打包压缩后的文件路径信息
     * @throws Exception
     * @author zhangdongfang 
     */
    public static void zip(String filename, String outputPath) throws Exception {
        if (filename == null || outputPath == null) {
            return;
        }
        File file = new File(filename);
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outputPath));
        out.putNextEntry(new ZipEntry(file.getName()));
        FileInputStream reader = new FileInputStream(file);
        try {
            int m = -1;
            byte[] buffer = new byte[BUFFER_SIZE];
            while ((m = reader.read(buffer, 0, READ_BUFFER_SIZE)) != -1) {
                out.write(buffer, 0, m);
            }
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception se) {
                
            }
        }
    }

    /**
     * 
     * 压缩整个目录中的文件
     * @param dirName
     * @param outputPath
     * @throws Exception
     * @author zhangdongfang
     */
    public static void zipDir(String dirName, String outputPath) throws Exception {
        if (dirName == null || outputPath == null) {
            return;
        }

        File file = new File(dirName);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outputPath));
            for (int i = 0; i < files.length; i++) {
                out.putNextEntry(new ZipEntry(files[i].getName()));
                FileInputStream reader = new FileInputStream(files[i]);
                int m = -1;
                byte[] buffer = new byte[BUFFER_SIZE];
                while ((m = reader.read(buffer, 0, READ_BUFFER_SIZE)) != -1) {
                    out.write(buffer, 0, m);
                }
                reader.close();
            }
            out.close();
        } else {
            zip(dirName, outputPath);
        }
    }







    /**
     * @desc 压缩文件留到一个zip文件 
     * @date 2016年4月18日-下午4:15:40
     * @param zipFilesForm
     * @param outputStream void 
     * @throws Exception 
     */
    public static void zipFiles(ZipFilesForm zipFilesForm) throws Exception {
        String zipFile = zipFilesForm.getZipFilePathName();
        List<String> fileNameList = zipFilesForm.getFileNames();
        List<InputStream> inputStreamList = zipFilesForm.getInputStreams();
         
        if(StringUtils.isBlank(zipFile) || fileNameList==null || fileNameList.size()==0 
                || inputStreamList==null || inputStreamList.size()==0 || fileNameList.size()!=inputStreamList.size()){
            return;
        }

        File file = new File(zipFile);
        if(!file.exists()) {
            file.createNewFile();
        }           
        //创建文件输出流
        FileOutputStream fous = new FileOutputStream(file);   
        // ZipOutputStream 输出流打包,
        ZipOutputStream zipOut = new ZipOutputStream(fous);
        for (int i = 0; i < fileNameList.size(); i++) {
            BufferedInputStream bins = null;
            String fileName = fileNameList.get(i);
            InputStream inputStream = inputStreamList.get(i);
            try{   
                bins = new BufferedInputStream(inputStream, 8192);
                ZipEntry entry = new ZipEntry(fileName);
                zipOut.putNextEntry(entry);
                // 向压缩文件中输出数据   
                int nNumber;
                byte[] buffer = new byte[BUFFER_SIZE];
                while ((nNumber = bins.read(buffer)) != -1) {
                    zipOut.write(buffer, 0, nNumber);
                }
                zipOut.flush();
            }catch(Exception e){
                logger.error("zipFiles():error:"+e.getMessage(),e);
            }finally{
                if(bins!=null){
                    try{
                        bins.close();
                    }catch(Exception e){}
                }
                if(inputStream!=null){
                    try{
                        inputStream.close();
                    }catch(Exception e){}
                }
            }
        }
        zipOut.close();

    }



    /**
     * 
     * 解压缩
     * @param outputPath 解压缩的文件路径信息
     * @param sourceZip源压缩文件信息
     * @throws Exception
     * @author zhangdongfang
     */
    public static void unzip(String sourceZip, String outputPath) throws Exception {
        if (sourceZip == null || outputPath == null) {
            return;
        }

        ZipInputStream in = new ZipInputStream(new FileInputStream(sourceZip));
        ZipEntry zipEntry = null;
        while ((zipEntry = in.getNextEntry()) != null) {

            File dir = new File(outputPath);
            dir.mkdir();
            File fil = new File(dir, zipEntry.getName());
            FileOutputStream out = new FileOutputStream(fil);
            int m = -1;
            byte[] buffer = new byte[BUFFER_SIZE];
            while ((m = in.read(buffer, 0, READ_BUFFER_SIZE)) != -1) {
                out.write(buffer, 0, m);
            }
            out.close();
        }
        in.close();
    }



}

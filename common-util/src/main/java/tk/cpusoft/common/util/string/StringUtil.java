package tk.cpusoft.common.util.string;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import tk.cpusoft.common.util.date.DateUtils;

/**
 * @desc 字符串处理类
 * @date 2015年11月6日-下午2:42:53
 */
public class StringUtil {


    /**
     * @desc 分隔后的数据 添加单引号,数据如：A,B,C 转换后成 'A','B','C',。
     * @date 2015年11月6日-下午2:43:47
     * @param obj
     * @return String 
     */
    public static String getOneSysbolFormItemValue(Object obj) {
        String ret = "";
        String otRet = "";
        if (obj == null) {
            ret = "";
        } else if (obj instanceof String) {
            ret = obj.toString();
            if (ret.indexOf(",") > -1) {
                String arr[] = ret.split(",");
                for (int i = 0; i < arr.length; i++) {
                    otRet += "'" + arr[i] + "',";
                }
                ret = otRet;
            }else if(ret.trim().length()!=0){
                ret = "'" + obj.toString() + "',";
            }
        } else if (obj instanceof String[]) {
            String arr[] = (String[]) obj;
            for (int i = 0; i < arr.length; i++) {
                ret += "'" + arr[i] + "',";
            }

        }
        if (ret.endsWith(",")) {
            ret = ret.substring(0, ret.length() - 1);
        }
        return ret;
    }
    /**
     * @desc 转换为String时加上非空判断 
     * @date 2015年11月26日-下午4:43:08
     * @param o
     * @return String 
     */
    public static String objectToString(Object o){
        if(o==null){
            return null;
        }
        return String.valueOf(o);
    }

    /**
     * @desc 防止出现null 
     * @date 2015年11月26日-下午4:42:57
     * @param str
     * @return String 
     */
    public static String nullToSpace(String str){
        if(str==null){
            return "";
        }
        return str;
    }


    private static final Set<String> picSuffixType = new HashSet<String>() ;
    private static final Set<String> compressSuffixType = new HashSet<String>() ;
    private static final Set<String> audioSuffixType = new HashSet<String>() ;
    private static final Set<String> wordSuffixType = new HashSet<String>() ;
    static {
        picSuffixType.add("jpg") ;
        picSuffixType.add("bmp") ;
        picSuffixType.add("png") ;
        
        compressSuffixType.add("zip");
        compressSuffixType.add("rar");
        compressSuffixType.add("tar");
        compressSuffixType.add("gz");
        
        audioSuffixType.add("amr");
        
        wordSuffixType.add("doc");
        wordSuffixType.add("docx");
     }
    /**
     * @desc 得到文件后缀 
     * @date 2016年4月14日-下午1:38:00
     * @param fileName
     * @return String 没有则为null
     */
    public static FileSuffixType getSuffix(String fileName){
        String suffix = getFileSuffix(fileName);
        if(picSuffixType.contains(suffix)){
            return FileSuffixType.PIC;
        }else if(compressSuffixType.contains(suffix)){
            return FileSuffixType.COMPRESS;
        }else if(audioSuffixType.contains(suffix)){
            return FileSuffixType.AUDIO;
        }else if(wordSuffixType.contains(suffix)){
            return FileSuffixType.WORD;
        }else{
            return FileSuffixType.OTHER;
        }
    }
    
    /**
     * @desc 返回文件后缀，如果没有，则为"" 
     * @date 2016年4月21日-上午9:23:08
     * @param fileName
     * @return String 
     */
    public static String getFileSuffix(String fileName){
        if(StringUtils.isBlank(fileName)){
            return "";
        }
        int lastIndexOfDot = fileName.lastIndexOf('.');
        int fileNameLength = fileName.length();
        if(lastIndexOfDot < 0 ){
            return "";
        }
        String suffix =  fileName.substring(lastIndexOfDot+1, fileNameLength);
        if(StringUtils.isBlank(suffix)){
            return "";
        }
        suffix = suffix.toLowerCase();
        return suffix;
    }
    
    
    /**
     * @desc 更新文件名 
     * @date 2016年4月25日-上午9:24:19
     * @param prefix
     * @param seqId
     * @param origFileName
     * @return String 
     */
    public static String changeFileName(String prefix, String seqId,String origFileName){
        //变更附件时需要改名
        String suffix = StringUtil.getFileSuffix(origFileName);
        String newFileName = prefix+"_"+DateUtils.formatDate(new Date())+"_"+seqId+"."+suffix;
        return newFileName;
    }
    /**
     * @desc 更换文件后缀 
     * @date 2016年4月25日-上午10:18:59
     * @param origFileName
     * @param newSuffix
     * @return String 
     */
    public static String changeSuffix(String origFileName, String newSuffix){
        //变更附件时需要改名
        if(StringUtils.isBlank(origFileName) || StringUtils.isBlank(newSuffix)){
            return origFileName;
        }
        int lastIndexOfDot = origFileName.lastIndexOf('.');
        int fileNameLength = origFileName.length();
        if(lastIndexOfDot < 0 ){
            return origFileName;
        }
        String newFileName =  origFileName.substring(0,lastIndexOfDot+1);
        if(StringUtils.isBlank(newFileName)){
            return origFileName;
        }
        return newFileName+newSuffix;
    }
}

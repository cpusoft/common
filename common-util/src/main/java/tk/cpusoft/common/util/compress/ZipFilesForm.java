/**
 * @desc  
 * @date 2016年4月18日
 */
package tk.cpusoft.common.util.compress;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import tk.cpusoft.common.base.BaseModel;

/**
 * @desc 
 * @date 2016年4月18日-下午3:08:47
 */
public class ZipFilesForm extends BaseModel{

    /**
     * @desc 
     * @date 2016年4月18日-下午3:08:59
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * @desc 压缩包中显示的文件名 
     * @date 2016年4月18日-下午3:11:43
     */
    private List<String> fileNames = new ArrayList<String>();
    /**
     * @desc 待压缩的文件流
     * @date 2016年4月18日-下午3:12:18
     */
    private List<InputStream> inputStreams = new ArrayList<InputStream>();
    /**
     * @desc 需要生成的zip文件目录 
     * @date 2016年4月18日-下午3:12:38
     */
    private String zipFilePathName;
    
    
    public void addFileName(String fileName){
        if(StringUtils.isNotBlank(fileName)){
            fileNames.add(fileName);
        }
    }

    public void addInputStream(InputStream inputStream){
        if(inputStream!=null){
            inputStreams.add(inputStream);
        }
    }

    public List<String> getFileNames() {
        return fileNames;
    }


    public void setFileNames(List<String> fileNames) {
        this.fileNames = fileNames;
    }


    public List<InputStream> getInputStreams() {
        return inputStreams;
    }


    public void setInputStreams(List<InputStream> inputStreams) {
        this.inputStreams = inputStreams;
    }


    public String getZipFilePathName() {
        return zipFilePathName;
    }


    public void setZipFilePathName(String zipFilePathName) {
        this.zipFilePathName = zipFilePathName;
    }
    
    
    

}

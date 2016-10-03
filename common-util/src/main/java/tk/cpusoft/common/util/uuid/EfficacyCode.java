package tk.cpusoft.common.util.uuid;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class EfficacyCode {
    
    public String getCode(){
	UUID uuid = UUID.randomUUID(); 
        String uuid_ = uuid.toString().replaceAll("-", "");
        
        int length = uuid_.length(); 
        
        List<String> codePool = new ArrayList<String>();
        java.util.Random r=new java.util.Random();
        for(int i=0;i<1000;i++){
            int index = r.nextInt(length);
            String code = null;
            if( ( index + 6 ) <= length ){
        	code = uuid_.substring(index, index+6);
            }else  {

        	code = uuid_.substring(length-index, length-index+6);
            }
            codePool.add(code);  
        }
        
        return codePool.get(new java.util.Random().nextInt(codePool.size()) );
	
    }


}

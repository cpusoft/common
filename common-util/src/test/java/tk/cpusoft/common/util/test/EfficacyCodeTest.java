package tk.cpusoft.common.util.test;

import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

import org.testng.annotations.Test;

import tk.cpusoft.common.util.uuid.EfficacyCode;

public class EfficacyCodeTest {
    
    
    @Test 
    public void testGetCode() { 
	Set<String> set = new HashSet();
       String code = null;
	for(int i=0;i<1000;i++){         
	    code = new  EfficacyCode().getCode();
            System.out.println("["+i+"]="+ code);
            set.add(code);
            
        }
	
	 System.out.println("["+ set.size() +"]");
    }

}

package tk.cpusoft.common.util.test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.annotations.Test;

import tk.cpusoft.common.util.json.JsonUtil;
import tk.cpusoft.common.util.log.LogInfo;
import tk.cpusoft.common.util.response.ReplyCode;

import com.google.gson.reflect.TypeToken;






public class JsonUtilTest {

    @Test
    public void jsonTest1() {
        String a = "ALL";
        String str = JsonUtil.Object2JsonString(a);
        System.out.println(str);

        Set<String> se = new HashSet<String>();
        se.add("Col1");
        se.add("Col2");
        se.add("Col3");
        str = JsonUtil.Object2JsonString(se);
        System.out.println(str);

        //        str = (String)JsonUtil.JsonString2SimpleObject(str, String.class);
        //        System.out.println(str);

        //        Student s = new Student();
        //        s.setId("111");
        //        s.setName("222:");
        //       // s.setDate(new Date());
        //        str = JsonUtil.Object2JsonString(s);
        //        System.out.println(str);
        //        
        //        Student ss = (Student)JsonUtil.JsonString2SimpleObject(str, Student.class);
        //        System.out.println(ss);
        //          FinProjectCredit ffub = new FinProjectCredit();
        //          String str = JsonUtil.Object2JsonString(ffub);
        //          System.out.println(str);

        //        Map map = new HashMap();
        //        map.put("education", "最高学历");
        //        map.put("university", "毕业院校");
        //        map.put("marital", "婚姻状况");
        //        String str = JsonUtil.Object2JsonString(map);
        //        System.out.println(str);
        //        
        //        map = new HashMap();
        //        map.put("companyIndustry", "公司行业");
        //        map.put("companyIndustry", "公司规模");
        //        map.put("position", "岗位职位");
        //        map.put("workAddress", "工作城市");
        //        map.put("workYear", "工作时间");
        //        
        //        str = JsonUtil.Object2JsonString(map);
        //        System.out.println(str);
        //        
        //        
        //        map = new HashMap();
        //        map.put("income", "收入");
        //        map.put("haveHouse", "Y");
        //        map.put("haveHouseLoan", "Y");
        //        map.put("car", "车产");
        //        map.put("haveCarLoan", "Y");
        //        map.put("haveCompany", "Y");
        //        str = JsonUtil.Object2JsonString(map);
        //        System.out.println(str);

        List list = new ArrayList();
        Map map = new HashMap();
        map.put("id", 1L);
        map.put("usrId", 7L);
        map.put("type", "CREDIT");
        map.put("auditTime", new Date());
        list.add(map);

        Map map2 = new HashMap();
        map2.put("id", 2L);
        map2.put("usrId", 7L);
        map2.put("type", "ID");
        map2.put("auditTime", new Date());
        list.add(map2);

        Map map3 = new HashMap();
        map3.put("id", 3L);
        map3.put("usrId", 7L);
        map3.put("type", "WORK");
        map3.put("auditTime", new Date());
        list.add(map3);


        Map map4 = new HashMap();
        map4.put("id", 3L);
        map4.put("usrId", 7L);
        map4.put("type", "INCOME");
        map4.put("auditTime", new Date());
        list.add(map4);

        Map map5 = new HashMap();
        map5.put("id", 3L);
        map5.put("usrId", 7L);
        map5.put("type", "HOUSE");
        map5.put("auditTime", new Date());
        list.add(map5);

        Map map6 = new HashMap();
        map6.put("id", 3L);
        map6.put("usrId", 7L);
        map6.put("type", "CAR");
        map6.put("auditTime", new Date());
        list.add(map6);

        Map map7 = new HashMap();
        map7.put("id", 3L);
        map7.put("usrId", 7L);
        map7.put("type", "MARITAL");
        map7.put("auditTime", new Date());
        list.add(map7);

        Map map8 = new HashMap();
        map8.put("id", 3L);
        map8.put("usrId", 7L);
        map8.put("type", "ADDRESS");
        map8.put("auditTime", new Date());
        list.add(map8);

        Map map9 = new HashMap();
        map9.put("id", 3L);
        map9.put("usrId", 7L);
        map9.put("type", "WEIBO");
        map9.put("auditTime", new Date());
        list.add(map9);

        str = JsonUtil.Object2JsonString(list);
        System.out.println(str);

        List<Map> l = (List<Map>)JsonUtil.JsonString2SimpleObject(str, List.class);
        System.out.println(l);
        for(Map s : l){
            str = (String)s.get("type");
            System.out.println(str);
        }
        ////        
        //        
        //        Student s2 = (Student)JsonUtil.JsonString2SimpleObject(str, Student.class);
        //        System.out.println(s2.getId()+"--"+s2.getName());
        //        
        //        Map m1 = (Map)JsonUtil.JsonString2SimpleObject(str, Map.class);
        //        System.out.println(m1.get("id"));
        //        
        //        
        //        Classes c = new Classes();
        //        
        //        List<Student> l = new ArrayList<Student>();
        //        l.add(s);
        //        l.add(s2);
        //        c.setStudents(l);
        //        c.setTeacher("teach");
        //        str = JsonUtil.Object2JsonString(c);
        //        System.out.println(str);
        //        Class className = Student.class;
        //        Class cs = className.getComponentType();
        //        
        //        Type cType=new TypeToken<Classes>(){}.getType();
        //        Classes c2 = (Classes)JsonUtil.JsonString2ComplexObject(str,cType);
        //        System.out.println(c2.getStudents().get(0).getId());
        //        
        //        
        //        str = JsonUtil.Object2JsonString(l);
        //        System.out.println(str);
        //        Type listType=new TypeToken<ArrayList<Student>>(){}.getType();
        //        List<Student> ll = (List<Student>)JsonUtil.JsonString2ComplexObject(str,listType);
        //        System.out.println(ll.get(0).getId());
        //        
        //        
        //        Map map = new HashMap();
        //        map.put("11", "ffff");
        //        map.put("222",333L);
        //        map.put("333",new Date());
        //        str = JsonUtil.Object2JsonString(map);
        //        System.out.println(str);
        //        
        //        Map m2 = (Map)JsonUtil.JsonString2SimpleObject(str, Map.class);
        //        System.out.println(m2.get("333"));
    }
    @Test
    public void jsonTest2() {
        List list = new ArrayList();
        Map map = new HashMap();
        map.put("loanType", "bankMortgage");
        list.add(map);

        Map map2 = new HashMap();
        map2.put("loanType", "bankNoMortgage");
        list.add(map2);

        Map map3 = new HashMap();
        map3.put("loanType", "smallLoanOrGuaranteeOrPawnshop");
        list.add(map2);
        String str = JsonUtil.Object2JsonString(list);
        System.out.println(str);
    }


    @Test
    public void mapTest(){
        Map map = new HashMap();
        map.put("age",33);
        map.put("householdLocation", "local" );
        map.put("haveHomePhone", "y" );
        map.put("residenceYear", "upper" );
        map.put("dependentRelatives", 2 );
        map.put("residentialType", "purchaseWithLoan" );
        map.put("registeredCapital", "middle");
        map.put("companyEstablishYear", 3 );
        map.put("companyPhoneType", "linearFixed" );
        map.put("haveBusinessAccount", "n" );
        map.put("employeePayrollType", "cashOrOnlineTransfer" );
        map.put("haveHousingFund", "n" );
        map.put("houseLoanType", "localNoLoan" );
        map.put("permanentlyIncreaseBankCredit", "n" );
        map.put("permanentlyDecreaseBankCredit", "n" );
        map.put("lifeInsurance", "n" );
        //     当三种贷款类型分别是银行抵押，小贷公司，银行无抵押时，map按如下方式写：
        map.put("loanType0","bankMortgage" );
        map.put("loanType1","smallLoanOrGuaranteeOrPawnshop");
        map.put("loanType2","bankNoMortgage");

        //   如果某种类型没有，不设即可。

        map.put(" actualBusinessAddress ", "have");
        map.put("blacklist", "self");
        String rr = JsonUtil.Object2JsonString(map);
        System.out.println(rr);

    }

    @Test
    public void testReg(){
        Map m = new HashMap();
        m.put("regId", "tdkx");
        m.put("regName", "测试注册商");
        m.put("status", "Y");
        List l = new ArrayList<>();
        l.add(m);
        l.add(m);

        Map m2 = new HashMap();
        m2.put("regList",l);
        String rr = JsonUtil.Object2JsonString(m2);
        System.out.println(rr);
    }


    
    @Test
    public void testLog(){
        LogInfo li = new LogInfo();
        li.setLogCode(ReplyCode.ERR_ALARM_ORACLE);
        li.setAccessReturnState("access");
        String rr = JsonUtil.Object2JsonString(li);
        System.out.println(rr);
    }
    

    @Test
    public void jsonListMap(){
       String listMap =  "[{\"time\":\"2016-04-27T15:35:32+08:00\",\"cnt\":8},"+
        " {\"time\":\"2016-04-27T15:35:37+08:00\",\"cnt\":8},"+
        " {\"time\":\"2016-04-27T15:35:42+08:00\",\"cnt\":8},"+
        " {\"time\":\"2016-04-27T15:35:47+08:00\",\"cnt\":8},"+
        " {\"time\":\"2016-04-27T15:35:52+08:00\",\"cnt\":4},"+
        " {\"time\":\"2016-04-27T15:35:57+08:00\",\"cnt\":4},"+
        " {\"time\":\"2016-04-27T15:36:02+08:00\",\"cnt\":8},"+
        " {\"time\":\"2016-04-27T15:36:07+08:00\",\"cnt\":8},"+
        " {\"time\":\"2016-04-27T15:36:12+08:00\",\"cnt\":8},"+
        " {\"time\":\"2016-04-27T15:36:17+08:00\",\"cnt\":8},"+
        " {\"time\":\"2016-04-27T15:36:22+08:00\",\"cnt\":6},"+
        " {\"time\":\"2016-04-27T15:36:27+08:00\",\"cnt\":8}]";
       Type listType = new TypeToken<ArrayList<Map>>(){}.getType();
       List<Map> lm = (List<Map>)JsonUtil.JsonString2ComplexObject(listMap, listType);
       System.out.println(lm);
    }
}

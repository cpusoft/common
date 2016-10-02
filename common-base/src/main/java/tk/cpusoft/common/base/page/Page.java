package tk.cpusoft.common.base.page;

import tk.cpusoft.common.base.BaseModel;

/**
 * 类描述：分页。需要分页的属性继承此Page即可
 * 创建时间：2014年5月19日-下午1:30:17
 * 
 */
public class Page  extends BaseModel{
    /**
     * @desc 
     * @date 2015年6月10日-下午2:48:36
     */
    private static final long serialVersionUID = 1L;
    /**
     * 属性描述：默认分页大小
     * 创建时间：2014年5月22日-上午8:34:25
     * 
     */
    public static final int DEFAULT_PAGE_SIZE = 15;
    /**
     * @desc 当前页码，从0页开始
     * @date 2015年4月15日-上午9:30:49
     */
    protected long currentPage=0;

    protected long pageSize = DEFAULT_PAGE_SIZE;

  
    
    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }
    
    /**
     * @desc 当前开始的数量(用于mysql) 
     * @date 2016年6月8日-上午9:49:10
     * @return long 
     */
    public long getCurrentNum(){
        return pageSize*currentPage;
    }
    
    

}

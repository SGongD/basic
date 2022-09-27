package com.basicit.framework.datasource;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * Custom paging object, extending navigatepageNums
 *
 * @author Crackers
 * @date 2022-03-15 16:19
 */
public class PageInfo<T> extends Page<T> {

    /*
    https://github.com/pagehelper/Mybatis-PageHelper/blob/master/src/main/java/com/github/pagehelper/Page.java

    this.startRow = this.pageNum > 0 ? (this.pageNum - 1) * this.pageSize : 0;
    this.endRow = this.startRow + this.pageSize * (this.pageNum > 0 ? 1 : 0);
    */

    public long getPrePage() {
        if (this.getCurrent() > 1) {
            // Add 1 at the end
            return this.getCurrent() - 1;
        }
        return 1;
    }

    public long getNextPage() {
        if (this.getCurrent() < this.getPages()) {
            // Add 1 at the end
            return this.getCurrent() + 1;
        }
        return this.getPages();
    }

    public long getStartRow() {
        if (this.getCurrent() > 0) {
            // Add 1 at the end
            return (this.getCurrent() - 1) * this.getSize() + 1;
        }
        return 0;
    }

    public long getEndRow() {
        long startRow = getStartRow();
        long endRow = startRow + this.getSize() * (this.getCurrent() > 0 ? 1 : 0);
        if (endRow > this.getTotal()) {
            endRow = this.getTotal();
        } else {
            endRow--;
        }
        return endRow;
    }

    public int[] getNavigatepageNums() {
        //current page number
        long current = this.getCurrent();
        //How many pages in total
        long totalPage = this.getPages();
        //The number of pages to display
        int navSize = 8;

        //Calculate how many to the left of current
        int before = navSize / 2;

        //The starting page number, preventing a negative start
        int start = current - before < 1 ? 1 : (int) (current - before);
        //end page number
        int end = start + navSize - 1;
        //If the ending page number is greater than or equal to the total page number
        if (end >= totalPage) {
            //The total page number is the ending page number
            end = (int) totalPage;
            //Because the ending page number changes, the starting page number also needs to be changed
            start = end - navSize + 1;
            //If the total page number is less than the number of displayed pages, the starting page number may be a negative number, change it to 1
            if (start < 1) {
                start = 1;
            }
        }

        int[] navs = new int[totalPage < navSize ? (int) totalPage : navSize];
        for (int i = start, j = 0; i <= end; i++, j++) {
            navs[j] = i;
        }
        return navs;
    }

    public PageInfo(long current, long size) {
        super(current, size);
    }
}

package jp.co.toshiba.ppok.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author Administrator
 */
public class Pagination<T> extends Page<T> {

    /**
     * 總頁數
     */
    protected int totalPages;

    /**
     * 導航條頁碼數量
     */
    protected int naviPages;

    /**
     * 分頁導航條頁數集合
     */
    protected int[] naviPageNum;

    /**
     * 唯一構造器
     *
     * @param currentPage 當前頁
     * @param pageSize    頁面大小
     * @param naviPages   導航條頁碼數量
     */
    public Pagination(int currentPage, int pageSize, int naviPages) {
        super.current = currentPage;
        super.size = pageSize;
        this.naviPages = naviPages;
        calcByNaviPages(naviPages);
    }

    /**
     * 根據導航條頁碼數量進行基本計算
     *
     * @param naviPages 導航條頁碼數量
     */
    private void calcByNaviPages(int naviPages) {
        // 設置導航條頁碼數量；
        setNaviPages(naviPages);
        // 設置總頁數；
        int pages = (int) super.getPages();
        setTotalPages(pages);
        //计算导航页
        calcNaviPageNum();
    }

    /**
     * 计算导航页
     */
    private void calcNaviPageNum() {
        int currentPage = (int) current;
        //当总页数小于或等于导航页码数时
        if (totalPages <= naviPages) {
            naviPageNum = new int[totalPages];
            for (int i = 0; i < totalPages; i++) {
                naviPageNum[i] = i + 1;
            }
        } else {
            //当总页数大于导航页码数时
            naviPageNum = new int[naviPages];
            int startNum = currentPage - naviPages / 2;
            int endNum = currentPage + naviPages / 2;
            if (startNum < 1) {
                startNum = 1;
                //(最前pageSize页
                for (int i = 0; i < naviPages; i++) {
                    naviPageNum[i] = startNum++;
                }
            } else if (endNum > totalPages) {
                endNum = totalPages;
                //最后pageSize页
                for (int i = naviPages - 1; i >= 0; i--) {
                    naviPageNum[i] = endNum--;
                }
            } else {
                //所有中间页
                for (int i = 0; i < naviPages; i++) {
                    naviPageNum[i] = startNum++;
                }
            }
        }
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getNaviPages() {
        return naviPages;
    }

    public void setNaviPages(int naviPages) {
        this.naviPages = naviPages;
    }

    public int[] getNaviPageNum() {
        return naviPageNum;
    }

    public void setNaviPageNum(int[] naviPageNum) {
        this.naviPageNum = naviPageNum;
    }
}

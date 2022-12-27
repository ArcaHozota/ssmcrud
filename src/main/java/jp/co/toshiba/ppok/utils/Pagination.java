package jp.co.toshiba.ppok.utils;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.domain.PageImpl;


/**
 * 簡單分頁實現類
 *
 * @author Administrator
 * @date 2022-12-14
 */
@Getter
@Setter
public class Pagination<T> extends PageImpl<T> {

    private static final long serialVersionUID = -632683959739832581L;

    /**
     * 當前頁
     */
    protected int current;

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
     * 是否有前一頁面
     */
    private boolean hasPrevious = false;
    /**
     * 是否有後一頁面
     */
    private boolean hasNext = false;

    /**
     * 唯一構造器
     */
    public Pagination() {
        super();
        this.current = super.getNumber();
    }

    /**
     * 根據導航條頁碼數量進行基本計算
     *
     * @param naviPages 導航條頁碼數量
     */
    public void calcByNaviPages(final int naviPages) {
        // 設置導航條頁碼數量；
        this.setNaviPages(naviPages);
        // 计算导航页
        this.calcNaviPageNum();
        // 判定页面边界
        this.discernPageBoundary();
    }

    /**
     * 计算导航页
     */
    private void calcNaviPageNum() {
        final int currentPage = (int) this.current;
        // 当总页数小于或等于导航页码数时
        if (this.totalPages <= this.naviPages) {
            this.naviPageNum = new int[this.totalPages];
            for (int i = 0; i < this.totalPages; i++) {
                this.naviPageNum[i] = i + 1;
            }
        } else {
            // 当总页数大于导航页码数时
            this.naviPageNum = new int[this.naviPages];
            int startNum = currentPage - this.naviPages / 2;
            int endNum = currentPage + this.naviPages / 2;
            if (startNum < 1) {
                startNum = 1;
                // (最前pageSize页
                for (int i = 0; i < this.naviPages; i++) {
                    this.naviPageNum[i] = startNum++;
                }
            } else if (endNum > this.totalPages) {
                endNum = this.totalPages;
                // 最后pageSize页
                for (int i = this.naviPages - 1; i >= 0; i--) {
                    this.naviPageNum[i] = endNum--;
                }
            } else {
                // 所有中间页
                for (int i = 0; i < this.naviPages; i++) {
                    this.naviPageNum[i] = startNum++;
                }
            }
        }
    }

    /**
     * 判定页面边界
     */
    private void discernPageBoundary() {
        this.hasPrevious = this.current > 1;
        final long totalPgs = this.totalPages;
        this.hasNext = this.current < totalPgs;
    }
}

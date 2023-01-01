package jp.co.toshiba.ppok.utils;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Basic {@code Page} implementation.
 *
 * @param <T> the type of which the page consists.
 * @author Oliver Gierke
 * @author Mark Paluch
 */
public class PaginationImpl<T> extends PageImpl<T> {

	private Integer current;

	private Integer totalPg;

	private Long totalRecords;

	private Boolean hasPrevious;

	private Boolean hasNext;

	protected int naviPages;

	protected int[] navigationPga;

	public PaginationImpl(List<T> content, Pageable pageable, long total) {
		super(content, pageable, total);
	}

	public PaginationImpl(List<T> content) {
		super(content);
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
		this.calcnavigationPga();
		// 判定页面边界
		this.discernPageBoundary();
	}

	/**
	 * 计算导航页
	 */
	private void calcnavigationPga() {
		final int currentPage = (int) this.current;
		// 当总页数小于或等于导航页码数时
		if (this.totalPg <= this.naviPages) {
			this.navigationPga = new int[this.totalPg];
			for (int i = 0; i < this.totalPg; i++) {
				this.navigationPga[i] = i + 1;
			}
		} else {
			// 当总页数大于导航页码数时
			this.navigationPga = new int[this.naviPages];
			int startNum = currentPage - this.naviPages / 2;
			int endNum = currentPage + this.naviPages / 2;
			if (startNum < 1) {
				startNum = 1;
				// (最前pageSize页
				for (int i = 0; i < this.naviPages; i++) {
					this.navigationPga[i] = startNum++;
				}
			} else if (endNum > this.totalPg) {
				endNum = this.totalPg;
				// 最后pageSize页
				for (int i = this.naviPages - 1; i >= 0; i--) {
					this.navigationPga[i] = endNum--;
				}
			} else {
				// 所有中间页
				for (int i = 0; i < this.naviPages; i++) {
					this.navigationPga[i] = startNum++;
				}
			}
		}
	}

	/**
	 * 判定页面边界
	 */
	private void discernPageBoundary() {
		this.hasPrevious = this.current > 1;
		final long totalPgs = this.totalPg;
		this.hasNext = this.current < totalPgs;
	}

	public Integer getCurrent() {
		return current;
	}

	public void setCurrent(Integer current) {
		this.current = current;
	}

	public Integer getTotalPg() {
		return totalPg;
	}

	public void setTotalPg(Integer totalPg) {
		this.totalPg = totalPg;
	}

	public Long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Long totalRecords) {
		this.totalRecords = totalRecords;
	}

	public Boolean getHasPrevious() {
		return hasPrevious;
	}

	public void setHasPrevious(Boolean hasPrevious) {
		this.hasPrevious = hasPrevious;
	}

	public Boolean getHasNext() {
		return hasNext;
	}

	public void setHasNext(Boolean hasNext) {
		this.hasNext = hasNext;
	}

	public int getNaviPages() {
		return naviPages;
	}

	public void setNaviPages(int naviPages) {
		this.naviPages = naviPages;
	}

	public int[] getNavigationPga() {
		return navigationPga;
	}

	public void setNavigationPga(int[] navigationPga) {
		this.navigationPga = navigationPga;
	}
}

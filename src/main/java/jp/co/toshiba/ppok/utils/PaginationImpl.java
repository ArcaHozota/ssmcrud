package jp.co.toshiba.ppok.utils;

import java.util.List;

import jp.co.toshiba.ppok.service.Pagination;
import lombok.Getter;
import lombok.Setter;

/**
 * Basic {@code Page} implementation.
 *
 * @param <T> the type of which the page consists.
 * @author Oliver Gierke
 * @author Mark Paluch
 */
@Getter
@Setter
public class PaginationImpl<T> implements Pagination<T> {

	private static final long serialVersionUID = -5664717729756413101L;

	private Integer current;

	private Integer totalPg;

	private Long totalRecords;

	private Boolean hasPrevious;

	private Boolean hasNext;

	protected int naviPages;

	protected int[] navigationPga;

	public PaginationImpl(final List<T> content) {
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
		this.calcNavigationPgs();
		// 判定页面边界
		this.discernPageBoundary();
	}

	/**
	 * 计算导航页
	 */
	private void calcNavigationPgs() {
		final int currentPage = this.current;
		// 当总页数小于或等于导航页码数时
		if (this.totalPg <= this.naviPages) {
			this.navigationPga = new int[this.totalPg];
			for (int i = 0; i < this.totalPg; i++) {
				this.navigationPga[i] = i + 1;
			}
			return;
		}
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

	/**
	 * 判定页面边界
	 */
	private void discernPageBoundary() {
		this.hasPrevious = this.current > 1;
		final long totalPgs = this.totalPg;
		this.hasNext = this.current < totalPgs;
	}
}

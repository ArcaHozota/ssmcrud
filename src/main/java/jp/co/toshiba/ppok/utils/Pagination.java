package jp.co.toshiba.ppok.utils;

import java.util.Arrays;
import java.util.List;

/**
 * 數據分頁
 *
 * @author Administrator
 * @param <T>
 * @since 3.08
 */
public final class Pagination<T> {

	/**
	 * 每頁數據
	 */
	private List<T> records;

	/**
	 * 當前頁碼
	 */
	private int pageNum;

	/**
	 * 每頁大小
	 */
	private int pageSize;

	/**
	 * 總頁數
	 */
	private int totalPages;

	/**
	 * 總記錄數
	 */
	private int totalRecords;

	/**
	 * 是否有前一頁
	 */
	private boolean hasPreviousPage = false;

	/**
	 * 是否有後一頁
	 */
	private boolean hasNextPage = false;

	/**
	 * 前一頁
	 */
	private int previousPage;

	/**
	 * 後一頁
	 */
	private int nextPage;

	/**
	 * 導航條頁碼數量
	 */
	private int navigatePages;

	/**
	 * 導航條第一頁
	 */
	private int naviFirstPage;

	/**
	 * 導航條最後一頁
	 */
	private int naviLastPage;

	/**
	 * 所有導航頁號碼
	 */
	private int[] navigatePageNums;

	/**
	 * 生成Pagination對象
	 *
	 * @param records      結果集
	 * @param totalRecords 總記錄數
	 * @param pageNum      當前頁
	 */
	public static <T> Pagination<T> of(final List<T> records, final int totalRecords, final int pageNum) {
		return new Pagination<>(records, totalRecords, pageNum, 17, 5);
	}

	/**
	 * 生成Pagination對象
	 *
	 * @param records      結果集
	 * @param totalRecords 總記錄數
	 * @param pageNum      當前頁
	 * @param pageSize     每頁大小
	 */
	public static <T> Pagination<T> of(final List<T> records, final int totalRecords, final int pageNum,
			final int pageSize) {
		return new Pagination<>(records, totalRecords, pageNum, pageSize, 5);
	}

	/**
	 * 生成Pagination對象
	 *
	 * @param records       結果集
	 * @param totalRecords  總記錄數
	 * @param pageNum       當前頁
	 * @param pageSize      每頁大小
	 * @param navigatePages 導航條頁碼數
	 */
	public static <T> Pagination<T> of(final List<T> records, final int totalRecords, final int pageNum,
			final int pageSize, final int navigatePages) {
		return new Pagination<>(records, totalRecords, pageNum, pageSize, navigatePages);
	}

	/**
	 * 封裝Pagination對象
	 *
	 * @param records       結果集
	 * @param totalRecords  總記錄數
	 * @param pageNum       當前頁
	 * @param pageSize      每頁大小
	 * @param navigatePages 導航條頁碼數
	 */
	private Pagination(final List<T> records, final int totalRecords, final int pageNum, final int pageSize,
			final int navigatePages) {
		if (records != null && !records.isEmpty()) {
			this.pageNum = pageNum;
			this.records = records;
			this.pageSize = records.size();
			this.totalRecords = totalRecords;
			final int ape = this.totalRecords / pageSize;
			this.totalPages = this.totalRecords % pageSize == 0 ? ape : ape + 1;
		} else if (records == null || records.isEmpty()) {
			this.pageNum = 1;
			this.records = null;
			this.pageSize = 0;
			this.totalRecords = 0;
			this.totalPages = 1;
		} else {
			throw new PaginationException("數據集合類型錯誤！");
		}
		this.calcByNavigatePages(navigatePages);
	}

	/**
	 * 根據導航條頁數進行計算
	 *
	 * @param navigatePages 導航條頁碼數量
	 */
	private void calcByNavigatePages(final int navigatePages) {
		// 設置導航條頁碼數量
		this.setNavigatePages(navigatePages);
		// 計算導航頁
		this.calcNavigatepageNums();
		// 計算前後頁，第一頁以及最後頁
		this.calcPage();
		// 判定頁面邊界
		this.discernPageBoundary();
	}

	/**
	 * 計算導航頁
	 */
	private void calcNavigatepageNums() {
		// 當總頁數小於或等於導航條頁碼數時
		if (this.totalPages <= this.navigatePages) {
			this.navigatePageNums = new int[this.totalPages];
			for (int i = 0; i < this.totalPages; i++) {
				this.navigatePageNums[i] = i + 1;
			}
		} else {
			// 當總頁數大於導航條頁碼數時
			this.navigatePageNums = new int[this.navigatePages];
			int startNum = this.pageNum - this.navigatePages / 2;
			int endNum = this.pageNum + this.navigatePages / 2;
			if (startNum < 1) {
				startNum = 1;
				// 第一導航條頁
				for (int i = 0; i < this.navigatePages; i++) {
					this.navigatePageNums[i] = startNum++;
				}
			} else if (endNum > this.totalPages) {
				endNum = this.totalPages;
				// 最後導航條頁
				for (int i = this.navigatePages - 1; i >= 0; i--) {
					this.navigatePageNums[i] = endNum--;
				}
			} else {
				// 所有中間頁
				for (int i = 0; i < this.navigatePages; i++) {
					this.navigatePageNums[i] = startNum++;
				}
			}
		}
	}

	/**
	 * 計算前後頁，第一頁以及最後頁
	 */
	private void calcPage() {
		if (this.navigatePageNums != null && this.navigatePageNums.length > 0) {
			this.naviFirstPage = this.navigatePageNums[0];
			this.naviLastPage = this.navigatePageNums[this.navigatePageNums.length - 1];
			if (this.pageNum > 1) {
				this.previousPage = this.pageNum - 1;
			}
			if (this.pageNum < this.totalPages) {
				this.nextPage = this.pageNum + 1;
			}
		}
	}

	/**
	 * 判定頁面邊界
	 */
	private void discernPageBoundary() {
		this.hasPreviousPage = this.pageNum > 1;
		this.hasNextPage = this.pageNum < this.totalPages;
	}

	/**
	 * 是否包含内容
	 */
	public boolean hasContent() {
		return !this.records.isEmpty();
	}

	/**
	 * getter for records
	 *
	 * @return records
	 */
	public List<T> getRecords() {
		return this.records;
	}

	/**
	 * setter of records
	 *
	 * @param records セットする records
	 */
	public void setRecords(final List<T> records) {
		this.records = records;
	}

	/**
	 * getter for pageNum
	 *
	 * @return pageNum
	 */
	public int getPageNum() {
		return this.pageNum;
	}

	/**
	 * setter of pageNum
	 *
	 * @param pageNum セットする pageNum
	 */
	public void setPageNum(final int pageNum) {
		this.pageNum = pageNum;
	}

	/**
	 * getter for pageSize
	 *
	 * @return pageSize
	 */
	public int getPageSize() {
		return this.pageSize;
	}

	/**
	 * setter of pageSize
	 *
	 * @param pageSize セットする pageSize
	 */
	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * getter for totalPages
	 *
	 * @return totalPages
	 */
	public int getTotalPages() {
		return this.totalPages;
	}

	/**
	 * setter of totalPages
	 *
	 * @param totalPages セットする totalPages
	 */
	public void setTotalPages(final int totalPages) {
		this.totalPages = totalPages;
	}

	/**
	 * getter for totalRecords
	 *
	 * @return totalRecords
	 */
	public int getTotalRecords() {
		return this.totalRecords;
	}

	/**
	 * setter of totalRecords
	 *
	 * @param totalRecords セットする totalRecords
	 */
	public void setTotalRecords(final int totalRecords) {
		this.totalRecords = totalRecords;
	}

	/**
	 * getter for hasPreviousPage
	 *
	 * @return hasPreviousPage
	 */
	public boolean isHasPreviousPage() {
		return this.hasPreviousPage;
	}

	/**
	 * setter of hasPreviousPage
	 *
	 * @param hasPreviousPage セットする hasPreviousPage
	 */
	public void setHasPreviousPage(final boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}

	/**
	 * getter for hasNextPage
	 *
	 * @return hasNextPage
	 */
	public boolean isHasNextPage() {
		return this.hasNextPage;
	}

	/**
	 * setter of hasNextPage
	 *
	 * @param hasNextPage セットする hasNextPage
	 */
	public void setHasNextPage(final boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	/**
	 * getter for previousPage
	 *
	 * @return previousPage
	 */
	public int getPreviousPage() {
		return this.previousPage;
	}

	/**
	 * setter of previousPage
	 *
	 * @param previousPage セットする previousPage
	 */
	public void setPreviousPage(final int previousPage) {
		this.previousPage = previousPage;
	}

	/**
	 * getter for nextPage
	 *
	 * @return nextPage
	 */
	public int getNextPage() {
		return this.nextPage;
	}

	/**
	 * setter of nextPage
	 *
	 * @param nextPage セットする nextPage
	 */
	public void setNextPage(final int nextPage) {
		this.nextPage = nextPage;
	}

	/**
	 * getter for navigatePages
	 *
	 * @return navigatePages
	 */
	public int getNavigatePages() {
		return this.navigatePages;
	}

	/**
	 * setter of navigatePages
	 *
	 * @param navigatePages セットする navigatePages
	 */
	public void setNavigatePages(final int navigatePages) {
		this.navigatePages = navigatePages;
	}

	/**
	 * getter for naviFirstPage
	 *
	 * @return naviFirstPage
	 */
	public int getNaviFirstPage() {
		return this.naviFirstPage;
	}

	/**
	 * setter of naviFirstPage
	 *
	 * @param naviFirstPage セットする naviFirstPage
	 */
	public void setNaviFirstPage(final int naviFirstPage) {
		this.naviFirstPage = naviFirstPage;
	}

	/**
	 * getter for naviLastPage
	 *
	 * @return naviLastPage
	 */
	public int getNaviLastPage() {
		return this.naviLastPage;
	}

	/**
	 * setter of naviLastPage
	 *
	 * @param naviLastPage セットする naviLastPage
	 */
	public void setNaviLastPage(final int naviLastPage) {
		this.naviLastPage = naviLastPage;
	}

	/**
	 * getter for navigatePageNums
	 *
	 * @return navigatePageNums
	 */
	public int[] getNavigatePageNums() {
		return this.navigatePageNums;
	}

	/**
	 * setter of navigatePageNums
	 *
	 * @param navigatePageNums セットする navigatePageNums
	 */
	public void setNavigatePageNums(final int[] navigatePageNums) {
		this.navigatePageNums = navigatePageNums;
	}

	/**
	 * toString
	 */
	@Override
	public String toString() {
		return "Pagination [records=" + this.records + ", pageNum=" + this.pageNum + ", pageSize=" + this.pageSize
				+ ", totalPages=" + this.totalPages + ", totalRecords=" + this.totalRecords + ", hasPrePage="
				+ this.hasPreviousPage + ", hasNextPage=" + this.hasNextPage + ", previousPage=" + this.previousPage
				+ ", nextPage=" + this.nextPage + ", navigatePages=" + this.navigatePages + ", naviFirstPage="
				+ this.naviFirstPage + ", naviLastPage=" + this.naviLastPage + ", navigatePageNums="
				+ Arrays.toString(this.navigatePageNums) + "]";
	}

	static class PaginationException extends RuntimeException {

		private static final long serialVersionUID = 4906724781325178457L;

		public PaginationException(final String message) {
			super(message);
		}

	}
}

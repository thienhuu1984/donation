package funix.huutt.prj.entity;

import java.util.ArrayList;
import java.util.List;

public class ModelApplication {
	
	private String currentPage;
	private String previousPage;
	private List<String> pagesOfSite;
	
	private int page;
	private int rowPerPage;
	private int totalPages;
	private boolean request;
	private List<Integer> pageList;
	
	public ModelApplication() {
		
		pageList = new ArrayList<>();
		
		pagesOfSite = new ArrayList<>();
		pagesOfSite.add("donation");
		pagesOfSite.add("user");
		pagesOfSite.add("role");
	}

	public List<Integer> getPageList() {
		return pageList;
	}

	public void setPageList(List<Integer> pageList) {
		this.pageList = pageList;
	}

	public List<String> getPagesOfSite() {
		return pagesOfSite;
	}

	public void setPagesOfSite(List<String> pagesOfSite) {
		this.pagesOfSite = pagesOfSite;
	}

	public int getTotalPages() {
		
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		
		for(int i = 1; i <= totalPages; i++) {
			pageList.add(i);
		}
			
		
		this.totalPages = totalPages;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getPreviousPage() {
		return previousPage;
	}

	public void setPreviousPage(String previousPage) {
		this.previousPage = previousPage;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRowPerPage() {
		return rowPerPage;
	}

	public void setRowPerPage(int rowPerPage) {
		this.rowPerPage = rowPerPage;
	}

	public boolean isRequest() {
		return request;
	}

	public void setRequest(boolean request) {
		this.request = request;
	}

}

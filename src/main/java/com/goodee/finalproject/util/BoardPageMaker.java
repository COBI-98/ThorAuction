package com.goodee.finalproject.util;


public class BoardPageMaker {

	//멤버변수
	/*
	 * 파라미터를 담을 변수
	 * jsp에서 꺼내서 사용할 변수
	 * mapper에서 꺼내서 사용할 변수
	 * perPage : 한페이지에 출력할 글의 개수
	 * perBlock : 한페이지에 출력할 번호의 개수
	 */

	private Long page;
	private Long startNum;
	private Long lastNum;
	private Long startRow;
	private Long lastRow;
	private Long perPage;
	private Long perBlock;

	//이전 블럭의 유무
	private boolean pre = false;
	//다음 블럭의 유무
	private boolean next = false;


	//검색 컬럼의 종류
	private String kind;
	//검색어
	private String search; 
	
	private String auctionSort;


	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	
	public String getAuctionSort() {
		
		if(this.auctionSort == null) {
			this.auctionSort = "1";
		}		
		return auctionSort;
	}
	public void setAuctionSort(String auctionSort) {
		this.auctionSort = auctionSort;
	}
	
	public String getSearch() {
		if(this.search == null)
			this.search = "";

		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}

	public boolean isPre() {
		return pre;
	}
	public void setPre(boolean pre) {
		this.pre = pre;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}


	public BoardPageMaker() {
		this.perPage=10L;
		this.perBlock=5L;
	}
	public Long getPage() {
		if(this.page ==null || this.page<1) {
			this.page =1L;
		}


		return page;
	}
	public void setPage(Long page) {
		this.page = page;
	}
	public Long getStartNum() {
		return startNum;
	}
	public void setStartNum(Long startNum) {
		this.startNum = startNum;
	}
	public Long getLastNum() {
		return lastNum;
	}
	public void setLastNum(Long lastNum) {
		this.lastNum = lastNum;
	}
	public Long getStartRow() {
		return startRow;
	}
	public void setStartRow(Long startRow) {
		this.startRow = startRow;
	}
	public Long getLastRow() {
		return lastRow;
	}
	public void setLastRow(Long lastRow) {
		this.lastRow = lastRow;
	}

	public Long getPerPage() {
		if(this.perPage ==null) {
			this.page =10L;
		}

		return perPage;
	}
	public void setPerPage(Long perPage) {
		this.perPage = perPage;
	}
	public Long getPerBlock() {
		if(this.perBlock ==null) {
			this.perBlock =5L;
		}
		return perBlock;
	}
	public void setPerBlock(Long perBlock) {
		this.perBlock = perBlock;
	}
	
	// 1. mapper에서 사용할 값
	public void getRowNum() throws Exception{
		this.startRow = (this.getPage()-1)*this.getPerPage();
		this.lastRow = this.getPerPage();
	}

	// 2. jsp에서 사용할 값
	public void getNum(Long totalCount) throws Exception{


		Long totalPage = totalCount/this.getPerPage();
		if(totalCount%this.getPerPage() !=0) {
			totalPage++;
		}

		if(this.getPage()>totalPage) {
			this.setPage(totalPage);
		}

		Long totalBlock = totalPage/this.getPerBlock();
		if(totalPage%this.getPerBlock() != 0 ) {
			totalBlock++;
		}

		Long curBlock = this.getPage()/this.getPerBlock();
		if(this.getPage()%this.getPerBlock() != 0) {
			curBlock++;
		}

		this.startNum = (curBlock-1)*this.getPerBlock()+1;
		this.lastNum = curBlock*this.getPerBlock();

		//6. curBlock이 마지막Block(totalBlock과 같을 때)

		if(curBlock==totalBlock) {
			this.lastNum=totalPage;
		}

		// 7. curBlock 이전, 다음 블럭의 유무
		if(curBlock>1) {
			pre=true;
		}

		if(curBlock<totalBlock) {
			next=true;
		}
	}
}

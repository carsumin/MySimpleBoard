package com.board.model;

public class PageMakerDTO {

	/* 시작 페이지 */
	private int startPage;
	
	/* 끝 페이지 */
	private int endPage;
	
	/* 이전 페이지, 다음 페이지 존재 유무 */
	private boolean prev, next;
	
	/* 전체 게시물 수 */
	private int total;
	
	/* 현재 페이지, 페이지당 게시물 표시수 정보 */
	private Criteria cri;
	
	/* 생성자 */
	/* 현재 페이지에 대한 정보인 Criteria와 게시물의 총개수인 total을 파라미터로 부여한 생성자 */
	/* 전달받은 파라미터를 활용하여 계산 과정을 거친후 pageMakerDTO의 변수에 대한 값을 초기화함 */
	public PageMakerDTO(Criteria cri, int total) {
		
		this.cri = cri;
		this.total = total;
		
		/* 마지막 페이지 */
		/* 현재 페이지가 7인 경우 endPage값이 10, 현재 페이지가 23인 경우 endPage 30이 됨 */
		/* int로 다시 형변환하는 이유는 Math.ceil()메소드의 반환타입이 double이기 때문 */
		this.endPage = (int)(Math.ceil(cri.getPageNum()/10.0))*10;
		
		/* 시작 페이지 */
		/* 화면에 표시될 페이지 번호들은 10개이기 때문에 끝번호 - 9를 하면 첫번째 번호 */
		this.startPage = this.endPage - 9;
		
		/* 전체 마지막 페이지 */
		int realEnd = (int)(Math.ceil(total * 1.0/cri.getAmount()));
		
		/* 전체 마지막 페이지(realend)가 화면에 보이는 마지막페이지(endPage)보다 작은 경우, 보이는 페이지(endPage)값 조정 */
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		
		/* 시작 페이지(startPage)값이 1보다 큰 경우 true */
		this.prev = this.startPage > 1;
		
		/* 마지막 페이지(endPage)값이 1보다 큰 경우 true */
		this.next = this.endPage < realEnd;	
	
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Criteria getCri() {
		return cri;
	}

	public void setCri(Criteria cri) {
		this.cri = cri;
	}

	@Override
	public String toString() {
		return "PageMakerDTO [startPage=" + startPage + ", endPage=" + endPage + ", prev=" + prev + ", next=" + next
				+ ", total=" + total + ", cri=" + cri + "]";
	}
	
	
}

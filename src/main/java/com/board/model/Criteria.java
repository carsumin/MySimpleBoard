package com.board.model;

import java.util.Arrays;

public class Criteria {

	/* 현재 페이지 */
	private int pageNum;
	
	/* 한 페이지당 보여질 게시물 개수  */
	private int amount;
	
	/* 스킵할 게시물 수 ( (pageNum - 1) * amount ) */
	/* 쿼리문의 limit에서 사용할 스킵 개수를 미리 자바 단계에서 처리 후 넘겨주기 위함  */
	private int skip;
	
	/* 검색 키워드 */
	private String keyword;
	
	/* 검색 타입 */
	private String type;
	
	/* 검색 타입 배열 변환 */
	private String[] typeArr;
	
	/* 기본 생성자 -> 기본 세팅 : pageNum = 1, amount = 10 */
	/* 파라미터없이 Criteria 클래스를 호출하였을 때 기본 세팅  */
	public Criteria() {
		this(1,10);
		this.skip = 0;
	}
	
	/* 생성자 => 원하는 pageNum, 원하는 amount */
	/* 파라미터와 함께 Criteria 호출하였을 때 각각의 값이 pageNum과 amount에 저장되도록 생성자 작성  */
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
		this.skip = (pageNum-1)*amount;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		
		this.skip = (pageNum-1)*this.amount;
		
		this.pageNum = pageNum;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		
		this.skip = (this.pageNum-1)*amount;
		
		this.amount = amount;
	}
	
	public int getSkip() {
		return skip;
	}
	
	public void setSkip(int skip) {
		this.skip = skip;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getType() {
		return type;
	}

	/* type변수에 데이터가 들어왔을 때 자동으로 배열형식으로 변환하여 typeArr변수에 저장 */
	/* 배열로 변환하기 위해서 String 타입의 데이터를 String 배열 타입 데이터로 변환해주는 split() 메서드를 사용 */
	public void setType(String type) {
		this.type = type;
		this.typeArr = type.split("");
	}

	public String[] getTypeArr() {
		return typeArr;
	}

	public void setTypeArr(String[] typeArr) {
		this.typeArr = typeArr;
	}

	@Override
	public String toString() {
		return "Criteria [pageNum=" + pageNum + ", amount=" + amount + ", skip=" + skip + ", keyword=" + keyword
				+ ", type=" + type + ", typeArr=" + Arrays.toString(typeArr) + "]";
	}
	
	
}

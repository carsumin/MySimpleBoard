package com.board.mapper;

import java.util.List;

import com.board.model.BoardVO;
import com.board.model.Criteria;

public interface BoardMapper {
	
	/* 게시판 등록 */
    public void enroll(BoardVO board);
    
    /* 게시판 목록 */
    /* 목록 페이지의 경우 대부분 2개 이상의 행에 있는 정보를 반환 받아야하기 때문에 List 타입으로 반환받음 */
    public List<BoardVO> getList();
    
    /* 게시판 목록(페이징 적용) */
    /* pageNum, amount의 정보를 DB에 전달하기 위해서 Criteria 클래스를 파라미터로 부여함 */
    public List<BoardVO> getListPaging(Criteria cri);
    
    /* 게시판 조회  */
    /* 하나의 게시판 정보를 얻기 위해서 게시판 번호를 알아야 하기 때문에 게시판 정보 데이터를 전달받을 수 있도록 int형 변수를 파라미터로 추가 */
    public BoardVO getPage(int bno);
    
    /* 게시판 수정  */
    /* bno, title, content, writer에 대한 데이터를 필요로하기 때문에 해당 변수들이 정의되어 있는 BoardVO를 파라미터로 추가 */
    /* 수정을 수행하는 메소드의 경우 반환 타입이 필요없지만 int형으로 설정할경우 수정이 성공시 1반환 실패시 0반환함 */
    public int modify(BoardVO board);
    
    /* 게시판 삭제 */
    /* 삭제 쿼리가 성공하면 1반환 실패하면 0반환 */
    public int delete(int bno);
    
    /* 게시판 총 개수 */
    public int getTotal(Criteria cri);
    
}

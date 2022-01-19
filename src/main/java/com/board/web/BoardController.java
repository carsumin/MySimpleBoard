package com.board.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.model.BoardVO;
import com.board.model.Criteria;
import com.board.model.PageMakerDTO;
import com.board.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService bservice;
	
	/* 게시판 목록 페이지 접속 */
	/*
	@GetMapping("/list")
    // => @RequestMapping(value="list", method=RequestMethod.GET)
    public void boardListGET(Model model) { //view에 데이터를 전송하기 위해 Model 파라미터를 추가
        
        log.info("게시판 목록 페이지 진입");
        
        //addAttribute 메소드를 호출하여 list라는 속성명에 BoardService 클래스의 getList()메소드를 반환한 값을 속성 값으로 저장
        model.addAttribute("list", bservice.getList());
        
    }
    */
	
	/* 게시판 목록 페이지 접속(페이징 적용) */
	/* Criteria 클래스를 파라미터로 추가 , getListPaging()메소드를 사용 */
	@GetMapping("/list")
	public void boardListGET(Model model, Criteria cri) {
		
		log.info("boardListGET");
		
		model.addAttribute("list", bservice.getListPaging(cri));
		
		int total = bservice.getTotal(cri);
		
		/* PageMakerDTO 클래스의 데이터를 뷰로 보내기 위해 addAttribute()메소드를 활용하여 pageMaker속성에 저장 */
		PageMakerDTO pageMake = new PageMakerDTO(cri, total);
		
		model.addAttribute("pageMaker", pageMake);
	}
    
	/* 게시판 등록 페이지 접속 */
    @GetMapping("/enroll")
    // => @RequestMapping(value="enroll", method=RequestMethod.GET)
    public void boardEnrollGET() {
        
        log.info("게시판 등록 페이지 진입");
        
    }
    
    /* 게시판 등록 */
    @PostMapping("/enroll")
    public String boardEnrollPOST(BoardVO board, RedirectAttributes rttr) {
    	
    	log.info("BoardVO : " + board);
    	
    	bservice.enroll(board);
    	
    	rttr.addFlashAttribute("result", "enroll success"); //일회성으로만 데이터 전달하기 위해 addFlashAttribute()
    	
    	return "redirect:/board/list";
    }
    
    /* 게시판 조회 */
    /* view로부터 게시판 번호를 전달받기 위해 int형 변수를 파라미터로 추가 */
    /* 게시판 조회 페이지에 쿼리 실행 후 전달받는 BoardVO 객체 데이터를 전달하기 위해 Model을 파라미터로 추가 */
    /* pageNum과 amount는 Criteria클래스에 정의되어 있는 변수들이기 때문에 파라미터로 부여 */
    @GetMapping("/get")
    public void boardGetPageGET(int bno, Model model, Criteria cri) {
    	
    	/* addAttribute 메소드 호출하여 pageInfo 속성명에 BoardService 인터페이스의 getPage() 메소드 호출하여 반환받은 BoardVO 객체를 속성값으로 저장 */
    	model.addAttribute("pageInfo", bservice.getPage(bno));
    	
    	/* addAttribute 메소드 호출하여 cri속성명에 뷰로부터 전달받은 Criteria인스턴스를 저장 */
    	model.addAttribute("cri", cri);
    	
    }
    
    /* 수정 페이지 이동  */
    @GetMapping("/modify")
    public void boardModifyGET(int bno, Model model, Criteria cri) {
    	
    	model.addAttribute("pageInfo", bservice.getPage(bno));
    	
    	/* 전달받은 데이터를 다시 수정화면(modify.jsp)에 전송하도록 cri에 Criteria인스턴스 저장 */
    	model.addAttribute("cri", cri);
    }
    
    /* 페이지 수정  */
    /* 수정될 내용의 데이터를 가져오기 위해 BoardVO 클래스를 파라미터로 부여함 */
    @PostMapping("/modify")
    public String boardModifyPOST(BoardVO board, RedirectAttributes rttr) {
    	
    	bservice.modify(board);
    	
    	//수정완료 경고창 띄우기 위한 메소드 호출
    	rttr.addFlashAttribute("result", "modify success");
    	
    	//리다이렉트 방식으로 리스트 페이지 이동
    	return "redirect:/board/list";
    }
    
    /* 페이지 삭제 */
    /* 삭제쿼리 게시판 번호 정보 필요하기 때문에 int형 변수를 파라미터로 부여함 */
    /* 수정 기능 후 리다이렉트 방식으로 리스트 페이지 이동시 데이터를 같이 전송하기 위해서 RedirectAttributes 객체를 파라미터로 부여함 */
    @PostMapping("/delete")
    public String boardDeletePOST(int bno, RedirectAttributes rttr) {
    	
    	bservice.delete(bno);
    	
    	rttr.addFlashAttribute("result","delete success");
    	
    	return "redirect:/board/list";
    }
    
    
    
    
    
}

package com.kh.board.service;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

import com.kh.attachment.vo.AttachmentVo;
import com.kh.board.repository.BoardDao;
import com.kh.board.vo.BoardVo;
import com.kh.category.vo.CategoryVo;
import com.kh.common.PageVo;

import static com.kh.common.JDBCTemplate.*;

public class BoardService {

	private final BoardDao dao = new BoardDao();

	/*
	 * 게시글 총 개수
	 */

	public int getCount() {

		Connection conn = null;
		int result = 0;

		conn = getConnetion();

		try {
			// dao호출
			result = dao.getCount(conn); // select 쿼리 날린 거 , 숫자 데이터 받음 -> commit / rollback 필요 없음

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn);
		}

		return result;
	}

	/*
	 * 게시글 리스트 조회 (현재 페이지에 보여질)
	 */

	public List<BoardVo> selectList(PageVo pageVo) {

		// 데이터 검사

		Connection conn = null;
		List<BoardVo> voList = null;

		conn = getConnetion();
		// dao 호출
		voList = dao.selectList(conn, pageVo);

		close(conn);

		return voList;
	}

	/*
	 * 카테고리 정보(리스트) 조회 
	 */
	public List<CategoryVo> selectCategoryList() {
		Connection conn = getConnetion();
		List<CategoryVo> list = dao.selectCategoryList(conn);
		close(conn);
		return list;
	}

	/*
	 * 원본 파일명을 우리 사이트에 맞는, 중복되지 않을 이름으로 변경
	 */
	public String createChangeName(String originName) {
		//확장자 가져오기
		int dotIdx = originName.lastIndexOf(".");
		String ext = originName.substring(dotIdx);
		
		//파일 이름 만들기
		long now = System.currentTimeMillis();
		String random = UUID.randomUUID().toString();
		random = random.substring(0,8);
		
		String changeName = "KH_" + now + "_" + random + ext; 
		
		return changeName;
		
		
	}

	/*
	 * 게시글 작성
	 */
	public int insertBoard(BoardVo bvo, AttachmentVo avo) {
		
		//1. 데이터 검사
		//제목이나 내용이 비어있으면 insert 안하게
		if(bvo.getTitle().length() < 1) {
			//실패처리. 다음단계 진행 x
		}
		
		if(bvo.getContent().length() < 1) {
			//실패처리. 다음단계 진행 x
		}
		
		Connection conn = getConnetion();
		
		//2. dao 호출 -> board 테이블에 insert하는 것은 attachment 테이블에 insert 하는 것과 같은 트랜잭션이다. 둘 다 동시에 이루어져야 게시글 작성 ok.
		int result1 = dao.insertBoard(conn, bvo);
		
		int result2 = 1; 
		if(avo != null) {
			//avo가 null이 아니다 == 클라이언트가 게시글만 쓴 게 아니라 파일 첨부도 했다. -> 이때만 db에 파일 첨부한 거 insert 해준다.
			result2 = dao.insertAttachment(conn, avo);
		}
		
		//3. 트랜잭션 처리
		if(result1 * result2 == 1) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		
		//4. 결과 리턴
		return result1 * result2;
		
	}
}

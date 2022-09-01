package com.kh.board.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.kh.attachment.vo.AttachmentVo;
import com.kh.board.service.BoardService;
import com.kh.board.vo.BoardVo;
import com.kh.category.vo.CategoryVo;
import com.kh.member.vo.MemberVo;

@MultipartConfig(
//		fileSizeThreshold = 1024 * 1024,
//		location = "/swy/temp"
		maxFileSize = 1024 * 1024 * 50,
		maxRequestSize = 1024 * 1024 * 50 * 5
		)
@WebServlet(urlPatterns = "/board/insert")
public class BoardInsertController extends HttpServlet {

	/*
	 * 일반게시글 작성 화면 보여주기
	 */

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 카테고리 정보 조회해서, jsp 까지 전달해주기
		List<CategoryVo> list = new BoardService().selectCategoryList();
		req.setAttribute("list", list);

		// 다음타자 선택
		req.getRequestDispatcher("/views/board/boardEnrollForm.jsp").forward(req, resp);
	}


	/*
	 *  게시글 작성 (파일 첨부)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//=====1.인코딩 --> 필터에서 해줌
//		req.setCharacterEncoding("UTF-8");
		
		//=====2.데이터 꺼내기
		String title = (String)req.getParameter("title");
		String category = (String)req.getParameter("category");
		String content = (String)req.getParameter("content");
		Part f = req.getPart("f");
		
		//클라이언트에게서 받은 no는 신뢰할 수 없으므로 세션에 있는 로그인된 멤버에게서 no를 받아온다.
		MemberVo m = (MemberVo)req.getSession().getAttribute("loginMember");
		
		
		//=====3.데이터 뭉치기 (Board)
		BoardVo bvo = new BoardVo();
		bvo.setCategory(category);
		bvo.setTitle(title);
		bvo.setContent(content);
		bvo.setWriter(String.valueOf(m.getNo()));
		
		
		
		
//		System.out.println("파일 첨부 안 했을 때 f ::: " + f.getSubmittedFileName().length()); --> 제출된 파일이 없으면 길이는 0이다
		
		
		
		
		AttachmentVo avo = null;
		String savePath = "";
		if(f.getSubmittedFileName().length() > 0) {
			//제출된 파일이 있는 경우에만 밑의 모든 파일에 대한 과정을 수행함
			//=====파일 업로드
			String originName = f.getSubmittedFileName(); //원본 파일명 얻기
			//원래 originName의 확장자 가져와서, 우리 사이트의 기획에 맞게 중복되지 않을 이름으로 changeName -> service 메소드에 비지니스 로직 작성함
			String changeName = new BoardService().createChangeName(originName);
			
			//=====인풋 스트림 준비
			InputStream is = f.getInputStream(); 
			BufferedInputStream bis =  new BufferedInputStream(is);
			
			//=====아웃풋 스트림 준비 (서버에 저장하기 위함)-> filePath라는 서버 경로에 저장함 / getServletContext.getRealPath("/")는 서블릿이 들어있는 바구니의 실제 경로 출력 
			String realPath = req.getServletContext().getRealPath("/resources/upload");
			savePath = realPath + File.separator + changeName;
//			FileOutputStream os = new FileOutputStream(realPath + "/" + fileName);
			FileOutputStream os = new FileOutputStream(savePath);
			BufferedOutputStream bos = new BufferedOutputStream(os);
			
			
			
			//1. 실제로 파일 업로드 하기 (근데 좀 느림, -1 줄어들지 않음)
			//data의 값이 -1이 아닐 때만 파일을 읽는 과정을 반복한다. 더 이상 읽을 데이터가 없을 때까지. -1일 때는 더 이상 읽어들일 데이터가 없는 상태
//			int data = 0;
//			while(data != -1) {
//			//hello.png의 0100110100001001 int형 데이터를, inputStream으로 read 하고, outputStream으로 write 하는 과정을 반복
//				data = is.read();
//				os.write(data);
//			}
			
			//2. 데이터 읽어오고 검사하고 집어넣는 거라 빨라짐 , -1도 줄어들음 (여전히 느리긴 함)
//			int data = 0;
//			while((data = is.read()) != -1) {
//			//hello.png의 0100110100001001 int형 데이터를, inputStream으로 read 하고, outputStream으로 write 하는 과정을 반복
//				os.write(data);
//			}
			
			//3. 원래는 stream을 통해 한 글자씩만 담아왔지만, 이제는 buf라는 배열을 가지고 1024개의 숫자를 담아올 수 있으니 더 빨라짐
			//1024개의 숫자를 담아줄 수 있는 buf라는 바구니 준비
			byte[] buf = new byte[1024];
			int size = 0;
			
//			while(size != 0) {
//				size = bis.read(buf);
//				bos.write(buf);
//			}
			
			//더 이상 읽어올 데이터가 없으면 -1 리턴한다.
			while( (size = bis.read(buf)) != -1) {
				bos.write(buf, 0, size);
			}
			
			bos.flush();
			
			bis.close();
			bos.close();
			
			//Attachment 테이블 관련 데이터는 위에 준비 되어있는 상태 (원본파일명, 변경파일명, 경로)
			//준비된 데이터를 이용하여, 객체로 뭉치기
			avo = new AttachmentVo();
			avo.setOriginName(originName);
			avo.setChangeName(changeName);
			avo.setFilePath(realPath);
		}
		
	
		
		
		//=====4.Board 테이블 채우기 (insert)
		int result = new BoardService().insertBoard(bvo, avo);
		
		//=====5.결과에 따라 화면 선택
		if(result == 1) {
			//성공 -> 일반게시판 목록 1페이지 보여주기 + 알람
		} else {
			//실패 -> 에러페이지
			//실패는 db에 insert를 실패한 것임, 만약 첨부파일이 있는데 insert 실패한 거라면 ? -> 이미 파일 업로드는 이루어진 상태이므로 파일 삭제하기
			
			if(avo != null) {
				//첨부파일 있음 -> 업로드 이미 되어있을 수 있음 -> 삭제 해줘야함
				new File(savePath).delete();
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
//		System.out.println(title);
//		System.out.println(category);
//		System.out.println(content);
//		System.out.println(f);
//		
//		System.out.println("================ f 정보 ================");
//		
//		System.out.println(f.getContentType());
//		System.out.println(f.getHeader("content-disposition"));
//		System.out.println(f.getName());
//		System.out.println(f.getSize());
//		
//		//사용자가 제출한 파일명
//		System.out.println(f.getSubmittedFileName());
//		System.out.println(f.getInputStream());
//		System.out.println(f.getHeaderNames());
	}
}

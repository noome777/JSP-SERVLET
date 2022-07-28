package com.kh.user.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.common.JDBCTemplate;
import com.kh.user.dto.UserDto;

@WebServlet(urlPatterns = "/user/login" )
public class UserLoginServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		try {
			//1. 데이터 꺼내기
			String userId = req.getParameter("userId");
			String userPwd = req.getParameter("userPwd");
			
			//2. 서비스 로직 (아이디 비번 맞는지)
			
			
			
			//3. DB 조회하기 (아이디 비번 전달받아서 조회)
			
			//(1) 커넥션 얻기 위해-> 드라이버 준비, URL, ID, PWD)-> 커넥션 얻기
			Connection conn = JDBCTemplate.getConnection();
		
			//(2) SQL 준비
			String sql = "SELECT NO, ID, NICK, ENROLL_DATE FROM MEMBER WHERE ID = ? AND PWD = ? AND QUIT_YN = 'N'";
			
			//(3) SQL 담을 객체 준비
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			//(4) SQL 완성시키기
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			//(5) SQL 실행 및 결과 저장
			ResultSet rs = pstmt.executeQuery();
			
			//(6) 결과에 따른 로직 처리
			
			UserDto dto = null;
			
			//boolean selectResult = rs.next(); //rs.next의 리턴 타입은 boolean임.
			//if(selectResult == true){
			//if(selectResult){ 왼쪽 == 오른쪽이므로 rs.next 바로 넣어도됨.
			
			if(rs.next()) {//커서가 다음 행을 가리키도록 만든다. next가 있으면(true)이면 첫번째 행을 가져온다. 없으면 안 가져온다.(결과집합(resultset)에서 데이터를 꺼내오려면, 커서가 가리키는 행에서 원하는 칼럼을 지정하여 특정 값을 꺼낼 수 있다.)
				int no = rs.getInt("NO");
				String id = rs.getString("ID");
				String nick = rs.getString("NICK");
				Timestamp enrollDate = rs.getTimestamp("ENROLL_DATE");
				
				//조회 결과(no, id, nick, enrollDate)가 있을 때, 그 데이터들을 담아줄 객체 생성. 이 데이터들을 한번에 dto라는 곳에 모두 담아줄 객체가 필요하기 때문에 만드는 것이다.
				dto = new UserDto();
				dto.setNo(no);
				dto.setId(id);
				dto.setNick(nick);
				dto.setEnrollDate(enrollDate);
				
				System.out.println("no : " + no);
				System.out.println("id : " + id);
				System.out.println("nick : " + nick);
				System.out.println("no : " + no);
			}
			
			
			
			//뒷정리(커밋 || 롤백, 자원반납)
			
			
			//4. 결과에 따라, 화면 보여주기 (forward로 jsp 다음타자에게 넘기기)
			if(dto != null) {
				//로그인 성공
				//dto의 기본값이 null인데, if문을 통과하면 null이 아닌 어떤 값이 들어가 있을 거니까 , dto가 null이 아니면 로그인이 성공하는 것이다.
				
				System.out.println(dto.getNo() + " 번 회원 로그인 성공");
				
				//req.setAttribute("loginUser", dto);										
				//req.getRequestDispatcher("/index.jsp").forward(req, resp);// ->실제 경로는 webContent가 생략돼서 /webContent/indes.jsp임, 새로고침시에 클라이언트에서 서버 쪽으로 최초의 보냈던 데이터를 또 제출(새로 고침 시에도 url이 안 바뀐다)하는 것이기 때문에 양식 다시 제출 확인 창 뜸
																			//--------request와 session의 차이---------
																			//req.setAttribute("loginUser", dto);라고 하면 request에 담아 둔 데이터는 sendredirect 에서는 최초에 보냈던 요청이 아닌 새로운 요청이기 때문에 데이터를 담아두는 게 의미가 없다.-> req.setAttribute("loginUser", dto);로 데이터 담아두는 것은 무조건 포워딩 해줄 때만 의미가 있음.
																			//따라서 이 경우 데이터를 들고 가지 않기 때문에, session을 통해 tomcat에 데이터를 정해진 시간 동안 저장해 둘 수 있도록 밑의 session.setAttribute("loginUser", dto);을 사용해준다. 이렇게 해주면, 양식 다시 제출 확인 창 안 뜬다
				
				HttpSession session = req.getSession();//여기서는 클래스 파일이기 때문에 session을 직접 만들어 주어야 하고, index.jsp에서는 jasper가 session을 내장 객체로 만들어주고 사용하기 때문에 session을 만들어 줄 필요 없이 session을 그대로 가져다가 쓰면 된다.
				session.setAttribute("loginUser", dto); 
				
				
				resp.sendRedirect("/webPrj14/index.jsp"); //여기서는 redirect는 클라이언트가 톰켓에 다시 한번 요청을 보내는 것이므로 프로젝트 명까지 적어 줘야 한다. 최초의 요청이 아닌 다른 요청을(새로 고침 시에 url이 바뀐다) 보내는 것이므로 양식을 다시 제출하는 게 아니기 때문에 양식 다시 제출 확인 창 안 뜬다.
				
			}else {
				//로그인 실패
				System.out.println(userId +" 계정으로 로그인 시도했으나 실패");
				req.getRequestDispatcher("/views/user/loginFail.jsp").forward(req, resp);
			}
				
				
				
				
			} catch (Exception e) {
				System.out.println("로그인 실패 !"); // 처음 데이터를 가져오는 과정에서 화면 넘겨주는 끝까지 과정까지 중에서 하나라도 문제가 발생하면 그냥 로그인 실패될 수 있도록 처리해준다.
				e.printStackTrace();
			}
			
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
}

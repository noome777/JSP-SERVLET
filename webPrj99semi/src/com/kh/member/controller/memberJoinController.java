package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.servicce.MemberService;
import com.kh.member.vo.MemberVo;

@WebServlet(urlPatterns = "/member/join")
public class memberJoinController extends HttpServlet {
	
	
	//화면 보여주는건(로그인화면, 회원가입화면) get 방식 요청이니까 doGet으로 , 
	//데이터를 받아서 데이터를 처리하는 경우(아이디, 비번 요청 등)에는 post 방식으로 처리할 테니까 doPost로 처리하는 게 best !!
	
	
	/*
	 * 회원가입 화면 보여주기
	 * 회원가입 버튼을 누르면 http://127.0.0.1:8888/semi/member/join 로 요청을 보내지만 실제로는 서블릿이 jsp에 보낸 요청을 가지고 /views/member/joinForm.jsp 여기에 데이터 넘겨줌
	//버튼태그 눌렀을 때 location.href로 location을 바꾸는 것으로, url자체를 바꿔서 요청 보내는 거라서 doGet으로 해야된다.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//회원가입 버튼을 누르면 http://127.0.0.1:8888  /semi/member/join 로 요청을 보내지만 실제로는 클라이언트의 요청을 가지고 서블릿 내부에서 url을 처리하여 jsp에 /views/member/joinForm.jsp 로 데이터 넘겨줌
		//즉, 사용자가 직접 jsp 파일 경로를 요청해서 보여줄 수 없게 하고 (내 폴더 노출되니까, 보안상 문제) 서블릿 주소를 통해서 보여주게 하려고 함  
		// <button onclick="location.href='/semi/member/join'">회원가입</button> 클라이언트에서   -->  (urlPatterns = "/member/join") 서블릿으로 --> but, 실제 경로는 밑의 포워딩한 경로임!!!
		
		//다른 애한테 떠넘기기
		req.getRequestDispatcher("/views/member/joinForm.jsp").forward(req, resp);
		
		
	}
	
	//회원가입 처리
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//controller 
		// -> 1. 브라우저에서 넘어온 데이터 꺼내기  2. 객체에 담기 (단순한 문자열 데이터들을 자바 데이터로 바꿔준다) // 3. 화면선택
		
		
		req.setCharacterEncoding("UTF-8");
		
		String memberId = req.getParameter("memberId");
		String memberPwd = req.getParameter("memberPwd");
		String memberPwd2 = req.getParameter("memberPwd2");
		String memberName = req.getParameter("memberName");
		String memberPhone = req.getParameter("memberPhone");
		String memberEmail = req.getParameter("memberEmail");
		String memberAddr = req.getParameter("memberAddr");
		String [] interest = req.getParameterValues("interest");
		
		//변수가 너무 많으므로 객체로 관리하기 -> memberVo
		
		//1. setter를 이용 -> 사람입장에서 편리
//		MemberVo vo = new MemberVo();		
//		vo.setId(memberId);
//		vo.setPwd(memberPwd);
//		vo.setName(memberName);
//		vo.setPhone(memberPhone);
//		vo.setEmail(memberEmail);
//		vo.setAddr(memberAddr);
//		vo.setInterest(String.join(",", interest));
//		System.out.println(vo);
		
		//2. 생성자를 이용 (객체가 태어날 때부터 데이터를 들고 객체를 만들면서 태어나니까 무결성, 안전성으로 인해 좋은 것임)-> 컴퓨터 입장에서 좋음
		
		//취미 선택 없을 경우 방어코드 ->null일 경우 string.join 해주면 안 되니까
		String hobbys = "";
		if(interest != null) {
			hobbys = String.join(",", interest);
		}
		
		MemberVo vo = new MemberVo(memberId, memberPwd, memberPwd2, memberName, memberPhone, memberEmail, memberAddr, hobbys);
		
		//객체 이용해서 회원가입 진행 --> 이 작업이 insert와 같음
		int result = new MemberService().join(vo);
		
		//insert 결과를 가지고 , 화면 선택
		if(result == 1) {
			//회원가입 성공 + 메세지 담기 -> 성공화면 선택, 다음타자에게 요청 떠넘기기 -> redirect로 해준다 -> 이래야 새로고침 시에 문제 안 생기니까 (why? url이 바뀌니까)
			//회원가입을 할 때 아이디 패스워스 담아서 요청 보냈는데 새로고침 하면 url이 안 바뀌고 포워딩하면 똑같은 데이터를 다시 나한테 보내니까 그걸 방지하기 위한 것임
			resp.sendRedirect("/semi");
		}else {
			//회원가입 실패 + 실패한 메세지-> 실패화면
			System.out.println("[ERROR-CODE:" + result +  "] 회원가입 실패 !");
			resp.sendRedirect("/semi/views/error/errorPage.jsp");
		}
		
		
//		
		
		
		
	}

}

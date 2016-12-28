package imgbbs;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import imgbbs.BbsDAO;
import imgbbs.BbsDTO;

public class BbsTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
				BbsDAO dao = new BbsDAO();
				delete(dao);
				//create(dao);
				//List(dao);
				//read(dao);
				//total(dao);
				//checkRefno(dao);
				
	}

	private static void delete(BbsDAO dao) {
		if(dao.delete(7)){
			p("삭제성공");
		}else{
			p("삭제실패");
		}
			
		}

		


	private static void checkRefno(BbsDAO dao) {
			int bbsno = 13;
			if(dao.checkRefno(bbsno)){
				p("�θ���Դϴ�.");
				
			}else{
				p("�θ���� �ƴմϴ�");
			}
	}

	private static void total(BbsDAO dao) {
		String col = "wname";
		String word = "�մ���";
		int total = dao.total(col, word);
		p("total:"+total);
		
	}

	private static void read(BbsDAO dao) {
				BbsDTO dto = dao.read(1);
				p(dto);
	}

	private static void List(BbsDAO dao) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		String col = "name";
		String word = "";
		int sno = 1;
		int eno = 5;
		map.put("col", col);
		map.put("word", word);
		map.put("sno", sno);
		map.put("eno", eno);
		
		List<BbsDTO>list = dao.list(map );
		Iterator<BbsDTO> iter = list.iterator();
		while(iter.hasNext()){
			BbsDTO dto = iter.next();
			p(dto);
		}
		
	}

	private static void p(BbsDTO dto) {
		// TODO Auto-generated method stub
		p("���--------------------");
		p("��ȣ:"+dto.getNo());
		p("����:"+dto.getName());
		p("����:"+dto.getTitle());
		p("��ȸ��:"+dto.getViewcnt());
		p("��¥:"+dto.getWdate());
		p("��ü----------------------:");
		p("����:"+dto.getContent());
		p("gepno:"+dto.getGrpno());
			p("indent:"+dto.getIndent());
			p("ansnum:"+dto.getAnsnum());

	}

	public static void create(BbsDAO dao) {
		
		BbsDTO dto = new BbsDTO();
		dto.setName("��浿");
		dto.setTitle("����� ����");
		dto.setContent("�»�� ����");
		dto.setPasswd("1234");
		dto.setFilename("test.txt");
		
		if(dao.create(dto)){
			p("����");
		}else{
			p("����");
		}
	}

	private static void p(String string) {
		System.out.println(string);
		
	}
	
}

package imgbbs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import utility.DBClose;
import utility.DBOpen;



public class BbsDAO {
	/**
	 * 부모굴인지 확인
	 * 부모글이면 삭제 못함
	 * @param bbsno 삭제하려고하는 글번호
	 * @return true 부모글번호, false 부모굴번호 아님
	 */
	public boolean checkRefno(int no){
		boolean flag = false;
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(refno) from imgbbs ");
		sql.append(" where refno = ? ");
		try {
			pstmt= con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			if(rs.next()){
				int cnt = rs.getInt(1);
				if(cnt>0)flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBClose.close(con, pstmt, rs);
		}
		
		
		return flag;
	}
	
	public int total(String col , String word){
		int total  = 0;
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) ");
		sql.append(" from imgbbs ");
		if(word.trim().length()>0)
		sql.append(" where "+col+ " like '%'||?||'%' ");
		
			try {
				pstmt = con.prepareStatement(sql.toString());
				if(word.trim().length()>0)
					pstmt.setString(1, word);

				rs=pstmt.executeQuery();
				if(rs.next()){
					total = rs.getInt(1);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DBClose.close(con, pstmt, rs);
			}
		return total;
		
	}
	
	public void upAnsnum(Map map){
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		int grpno = (Integer)map.get("grpno");
		int ansnum = (Integer)map.get("ansnum");
		sql.append(" update imgbbs ");
		sql.append(" set ansnum = ansnum +1 ");
		sql.append(" where grpno = ? ");
		sql.append(" and ansnum > ? ");
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, grpno);
			pstmt.setInt(2, ansnum);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBClose.close(con, pstmt);
		}
		
		
	}
	public boolean createReply(BbsDTO dto){
		boolean flag = false;
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO imgbbs(no, name, title, content, passwd, wdate, grpno, indent, ansnum,refno) ");
		sql.append(" VALUES((SELECT NVL(MAX(no), 0) + 1 as no FROM imgbbs), ");
		sql.append(" ?, ?, ?, ?, sysdate, ");
		sql.append(" ?, ?, ?, ?) ");
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getPasswd());
			pstmt.setInt(5, dto.getGrpno());//�쁾遺�紐⑥� �룞�씪!!!!!!!!!!
			pstmt.setInt(6, dto.getIndent()+1);//�쁾遺�紐⑥쓽 indent+1!!!!
			pstmt.setInt(7, dto.getAnsnum()+1);//�쁾遺�紐⑥쓽 ansnum+1!!!!
			pstmt.setInt(8, dto.getNo());//글번호 등록
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) flag=true;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBClose.close(con, pstmt);
		}
		
		return flag;
	}

	
	
	public BbsDTO readReply(int no){
		BbsDTO dto = null;
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT no, title, grpno, indent,ansnum ");
		sql.append(" FROM imgbbs WHERE no = ? ");
		
		try {
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			
			rs= pstmt.executeQuery();
			
			if(rs.next()){
				dto = new BbsDTO();
				dto.setNo(rs.getInt("no"));
				dto.setTitle(rs.getString("title"));
				dto.setGrpno(rs.getInt("grpno"));
				dto.setIndent(rs.getInt("indent"));
				dto.setAnsnum(rs.getInt("ansnum"));
		}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBClose.close(con, pstmt, rs);
		}
		 
		return dto;
	}
	
	public boolean delete(int no){
		boolean flag = false;
		Connection con = DBOpen.open();
		PreparedStatement pstmt= null;
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from imgbbs ");
		sql.append(" where no = ? ");
		
		try {
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1 , no);
			
			int cnt = pstmt.executeUpdate();
			if(cnt>0)flag=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBClose.close(con, pstmt);
		}
		return flag;
	}
	
	public boolean update(BbsDTO dto){
		boolean flag = false;
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE imgbbs  ");
		sql.append(" SET wname=?, ");
		sql.append(" title=?, ");
		sql.append(" content=?  ");
		sql.append(" WHERE no = ? ");
		try {
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setInt(4, dto.getNo());
			
			int cnt=pstmt.executeUpdate();
			if(cnt>0)flag=true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBClose.close(con, pstmt);
		}
	return flag;
	}
	
	
	public boolean passCheck(Map map){
		boolean flag = false;
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int no = (Integer)map.get("no");
		String passwd = (String)map.get("passwd");
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT COUNT(no) as cnt ");
		sql.append(" FROM imgbbs ");
		sql.append(" WHERE no=? AND passwd=? ");
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1,no);
			pstmt.setString(2,passwd);
			
			rs=pstmt.executeQuery();
			rs.next();
			int cnt = rs.getInt("cnt");
			
			if(cnt>0) flag=true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBClose.close(con, pstmt, rs);
		}
		
		return flag;
	}
	
	public BbsDTO read(int bbsno){
		BbsDTO dto = null;
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT bbsno, wname, title, viewcnt, wdate, content, ");
		sql.append(" filename, filesize ");
		sql.append(" FROM bbs WHERE bbsno = ? ");
		
		try {
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, bbsno);
			
			rs= pstmt.executeQuery();
			
			if(rs.next()){
				dto = new BbsDTO();
				dto.setNo(rs.getInt("no"));
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setWdate(rs.getString("wdate"));
				dto.setViewcnt(rs.getInt("viewcnt"));
				dto.setFilename(rs.getString("filename"));
		}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBClose.close(con, pstmt, rs);
		}
		 
		return dto;
	}
	
	
	public void upViewcnt(int no){
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		StringBuffer sql= new StringBuffer();
		sql.append(" update imgbbs ");
		sql.append(" set viewcnt = viewcnt + 1 ");
		sql.append(" where no = ? ");
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			
			pstmt.executeUpdate();
			 
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBClose.close(con, pstmt);
		}
		
		
	}
	
	public List<BbsDTO> list(Map map){
		List<BbsDTO> list = new ArrayList<BbsDTO>();
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String col= (String)map.get("col");
		String word= (String)map.get("word");
		int sno =(Integer)map.get("sno");
		int eno =(Integer)map.get("eno");
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT no, name, title, viewcnt, wdate, ");
		sql.append(" indent, filename, r ");
		sql.append(" FROM( ");
		sql.append(" 	SELECT no, name, title, viewcnt, wdate, ");
		sql.append(" 	indent, filename,rownum r  ");
		sql.append(" 	FROM(    ");
		sql.append(" 		SELECT no, name, title, viewcnt, wdate, ");
		sql.append(" 		indent, filename ");
		sql.append(" 		FROM bbs  ");
		if(word.trim().length()>0)
		sql.append(" 		where  "+col+"  like  '%'||?||'%' ");
		sql.append(" 		ORDER BY grpno DESC, ansnum ");
		sql.append(" 	) ");
		sql.append(" )WHERE r>=? and r<=? ");
		try {
			pstmt = con.prepareStatement(sql.toString());
			int i = 0;
			if(word.trim().length()>0)
				pstmt.setString(++i, word);
			
			pstmt.setInt(++i, sno);
			pstmt.setInt(++i, eno);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				BbsDTO dto = new BbsDTO();
				dto.setNo(rs.getInt("bbsno"));
				dto.setName(rs.getString("wname"));
				dto.setTitle(rs.getString("title"));
				dto.setViewcnt(rs.getInt("viewcnt"));
				dto.setWdate(rs.getString("wdate"));
				dto.setIndent(rs.getInt("indent"));
				dto.setFilename(rs.getString("filename"));

				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBClose.close(con, pstmt, rs);
		}
		
		
		return list;
	}
	
	public boolean create(BbsDTO dto){
		boolean flag = false;
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO bbs(bbsno, wname, title, content, passwd, wdate, grpno, filname,filesize) ");
		sql.append(" VALUES((SELECT NVL(MAX(bbsno), 0) + 1 as bbsno FROM bbs), ");
		sql.append(" ?, ?, ?, ?, sysdate, ");
		sql.append(" (SELECT NVL(MAX(grpno), 0) + 1 as grpno FROM bbs), ");
		sql.append(" ?,?) ");

		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getPasswd());
			pstmt.setString(5, dto.getFilename());
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) flag=true;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBClose.close(con, pstmt);
		}
		
		return flag;
	}
	
}

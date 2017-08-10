package data;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import data.PVO;

public class DAO {
	DBConnect dbconnect = null;
	String sql = "";

	private static DAO instance = new DAO();
    
    public static DAO getDao() {
        return instance;
    }

    public DAO() {
    	dbconnect = new DBConnect();
    }

	public String pasing(String data) {
		try {
			data = new String(data.getBytes("8859_1"), "euc-kr");
		} catch (Exception e) {
		}

		return data;
	}
	public void deleteProduct(int pNum) {
		Connection con = dbconnect.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			sql = "DELETE FROM `shop`.`product` WHERE `pNum`=?;";
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, pNum);
            pstmt.executeUpdate();
                    
		} catch (Exception e) {
			System.out.println("오류!");
		} finally {
			System.out.println("성공!");
			DBClose.close(con, pstmt);
		}
	}

	
	public void modifyProduct(PVO vo) {
		Connection con = dbconnect.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			sql = "UPDATE `shop`.`product` SET pQuan=?, pPrice=? WHERE pNum=?;";
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, vo.getpQuan());
			pstmt.setInt(2, vo.getpPrice());
			pstmt.setInt(3, vo.getpNum());
            pstmt.executeUpdate();
           
                    
		} catch (Exception e) {
			System.out.println("오류!");
		} finally {
			System.out.println("성공!");
			DBClose.close(con, pstmt);
		}
	}

	public void insertProduct(PVO vo) {
		Connection con = dbconnect.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			sql = "insert into `shop`.`product` ( `pProduct`, `pQuan`, `pPrice`) VALUES ( ?, ?, ? );";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, vo.getpProduct());
			pstmt.setInt(2, vo.getpQuan());
			pstmt.setInt(3, vo.getpPrice());
            pstmt.executeUpdate();           
                    
		} catch (Exception e) {
			System.out.println("오류!");
		} finally {
			System.out.println("성공!");
			DBClose.close(con, pstmt);
		}
	}
	public void insertOrder(OVO vo) {
		Connection con = dbconnect.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			sql = "INSERT INTO `shop`.`order` ( `oName`, `oId`, `oPhone`, `oAdd`, `oProduct`, `oQuan`, `oDate`, `oPrice`) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?);";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pasing(vo.getoName()));
			pstmt.setString(2, vo.getoId());
			pstmt.setString(3, vo.getoPhone());
			pstmt.setString(4, pasing(vo.getoAdd()));
			pstmt.setString(5, pasing(vo.getoProduct()));
			pstmt.setInt(6, vo.getoQuan());
			pstmt.setTimestamp(7, vo.getoDate());
			pstmt.setInt(8, vo.getoPrice());
            pstmt.executeUpdate();
           
            sql= "SELECT max(oNum) from `order`;";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
            	vo.setoNum(rs.getInt(1));
            }
            
		} catch (Exception e) {
			System.out.println(vo.getoId());
		} finally {
			System.out.println(vo.getoDate());
			DBClose.close(con, pstmt);
		}
	}


	public PVO getProduct(int pNum) {
		Connection con = dbconnect.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PVO pvo = new PVO();
		try {
			sql = "SELECT * from product where pNum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pNum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				pvo.setpNum(rs.getInt(1));
				pvo.setpProduct(rs.getString(2));
				pvo.setpQuan(rs.getInt(3));
				pvo.setpPrice(rs.getInt(4));
				System.out.println("끝!");				
			}
		} catch (Exception e) {
		} finally {
			DBClose.close(con, pstmt, rs);
		}
		return pvo;
	}
	
	public OVO getOrder(int oNum) {
		Connection con = dbconnect.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OVO ovo = new OVO();
		try {
			sql = "SELECT * from `order` where oNum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, oNum);
			
			System.out.println("시작"+oNum);
			rs = pstmt.executeQuery();
			System.out.println("전송");
			while (rs.next()) {				
				ovo.setoNum(rs.getInt(1));
				ovo.setoName(rs.getString(2));
				ovo.setoId(rs.getString(3));
				ovo.setoPhone(rs.getString(4));
				ovo.setoAdd(rs.getString(5));
				ovo.setoProduct(rs.getString(6));
				ovo.setoQuan(rs.getInt(7));
				ovo.setoDate(rs.getTimestamp(8));
				ovo.setoPrice(rs.getInt(9));
				System.out.println("완료");
			}
		} catch (Exception e) {
		} finally {
			DBClose.close(con, pstmt, rs);
		}
		return ovo;
	}
	public ArrayList<PVO> getProductList(String uid) {
		Connection con = dbconnect.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<PVO> alist = new ArrayList<PVO>();
		System.out.println("시작");
		try {
			sql = "SELECT `dname`,`dcount`,`dprice` from `pretend` where did=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, uid);
			rs = pstmt.executeQuery();
			System.out.println("조회");
			while (rs.next()) {
				PVO pvo = new PVO();
				pvo.setpProduct(rs.getString(1));
				pvo.setpQuan(rs.getInt(2));
				pvo.setpPrice(rs.getInt(3));
				alist.add(pvo);
			}
		} catch (Exception e) {
		} finally {
			DBClose.close(con, pstmt, rs);
		}
		return alist;
	}
	
	//관리자
	public ArrayList<OVO> getOrderList() {
		Connection con = dbconnect.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<OVO> alist = new ArrayList<OVO>();
		System.out.println("시작");
		try {
			sql = "SELECT * from `order`";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			System.out.println("조회");
			while (rs.next()) {
				OVO ovo = new OVO();
				ovo.setoNum(rs.getInt(1));
				ovo.setoName(rs.getString(2));
				ovo.setoId(rs.getString(3));
				ovo.setoPhone(rs.getString(4));
				ovo.setoAdd(rs.getString(5));
				ovo.setoProduct(rs.getString(6));
				ovo.setoQuan(rs.getInt(7));
				ovo.setoDate(rs.getTimestamp(8));
				ovo.setoPrice(rs.getInt(9));
				alist.add(ovo);
			}
		} catch (Exception e) {
		} finally {
			DBClose.close(con, pstmt, rs);
		}
		return alist;
	}
	//상품목록
	public ArrayList<PVO> getGoodList(){
		String SQL = "SELECT * FROM product";// WHERE pNum < ? ORDER BY bbsID DESC LIMIT 10
		Connection con = dbconnect.getConnection();
		ResultSet rs = null;
		ArrayList<PVO> list = new ArrayList<PVO>();
		try {
			PreparedStatement pstmt = con.prepareStatement(SQL);
		//	pstmt.setInt(1, getNest() - (pageNumber - 1) * 10);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				PVO pvo = new PVO();
				pvo.setpNum(rs.getInt(1));
				pvo.setpProduct(rs.getString(2));
				pvo.setpQuan(rs.getInt(3));
				pvo.setpPrice(rs.getInt(4));;
				list.add(pvo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list; 
	}
/*	public OVO getView(int idx) {
		Connection con = dbconnect.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OVO vo = null;
		try {
			sql = "SELECT username, title, memo, time, hit, password, ref, indent, step FROM board1 WHERE num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo = new VO();
				vo.setName(rs.getString(1));
				vo.setTitle(rs.getString(2));
				vo.setMemo(rs.getString(3));
				vo.setTime(rs.getString(4));
				vo.setHit(rs.getInt(5) + 1);
				vo.setPassword(rs.getString(6));
				vo.setRef(rs.getInt(7));
				vo.setIndent(rs.getInt(8));
				vo.setStep(rs.getInt(9));
			}
		} catch (Exception e) {
		} finally {
			DBClose.close(con, pstmt, rs);
		}
		return vo;
	}*/

	
/*	
*/
}

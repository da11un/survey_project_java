package project_survey;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SurveyDao {
	private JdbcTemplate jdbcTemplate;

	public SurveyDao() {
		jdbcTemplate = JdbcTemplate.getInstance();
	}

	// selectAll
	public List<SurveyVo> selectAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from \"SURVEY\" order by \"NUMBER\" ASC";
		List<SurveyVo> ls = new ArrayList<>();

		try {
			conn = jdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				SurveyVo vo = new SurveyVo(rs.getInt(1), rs.getString(2), rs.getInt(3));
				ls.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return (ls.size() == 0) ? null : ls;
	}

	// counter
	public int counter(int selnum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "update \"SURVEY\" set \"VOTE\"=\"VOTE\"+1 where \"NUMBER\"=?";

		try {
			conn = jdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, selnum);
			rs = pstmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return 0;
	}

	// sumVote
	public int sumVote() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select sum(\"VOTE\") from \"SURVEY\"";

		try {
			conn = jdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return 0;
	}

	// modifyList
	public boolean modifyList(String input, String list) {
		boolean ret = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update \"SURVEY\" set \"LIST\"=? where \"LIST\"=?";

		try {
			conn = jdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setObject(1, input);
			pstmt.setObject(2, list);
			pstmt.executeUpdate();
			ret = true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ret;
	}

	// list 전체 목록
	public ArrayList<SurveyVo> allList() {
		ArrayList<SurveyVo> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select \"LIST\" from \"SURVEY\"";

		try {
			conn = jdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				SurveyVo vo = new SurveyVo(rs.getString(1));
				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return (list.size() == 0) ? null : list;
	}

	// insertList
	public void insertList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String insertSql[] = new String[] { 
				"insert into \"SURVEY\" values(\"SEQ_SURVEY\".nextval, '액션', 0)",
				"insert into \"SURVEY\" values(\"SEQ_SURVEY\".nextval, '멜로', 0)",
				"insert into \"SURVEY\" values(\"SEQ_SURVEY\".nextval, '코미디', 0)",
				"insert into \"SURVEY\" values(\"SEQ_SURVEY\".nextval, '공포', 0)",
				"insert into \"SURVEY\" values(\"SEQ_SURVEY\".nextval, '판타지', 0)", 
				};
		try {
			conn = jdbcTemplate.getConnection();
			for (int i = 0; i < insertSql.length; i++) {
				pstmt = conn.prepareStatement(insertSql[i]);
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	// 초기화 init
	public void init() {
		Connection conn = null;
		PreparedStatement pstmt = null;

		String[] init = new String[] { "truncate table \"SURVEY\"", "drop sequence \"SEQ_SURVEY\"",
				"create sequence \"SEQ_SURVEY\" start with 1 increment by 1 nocache" };

		try {
			conn = jdbcTemplate.getConnection();
			for (int i = 0; i < init.length; i++) {
				pstmt = conn.prepareStatement(init[i]);
				pstmt.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			insertList();
		}
	}

}

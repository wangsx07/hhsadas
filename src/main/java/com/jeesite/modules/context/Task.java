package com.jeesite.modules.context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Task implements Runnable {
	private Map<String, Long> m = null;

	public Task(Map m) {
		this.m = m;
	}

	@Override
	public void run() {
		// updateStatus("111111");
		while (true) {
			long currenttime = System.currentTimeMillis();
			Set<Entry<String, Long>> set = m.entrySet();
			for (Entry<String, Long> entry : set) {
				if (currenttime - entry.getValue() > 2 * 60 * 1000) {
					updateStatus(entry.getKey());
					m.remove(entry.getKey());
				}
			}
			try {
				Thread.sleep(1000);// sleep 1s
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// 更新表中在线状态
	private void updateStatus(String key) {
		String sql = Constant.sql;
		Connection conn = null;
		PreparedStatement stat = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(Constant.mysql_url, "root", "root");
			stat = conn.prepareStatement(sql);
			stat.setString(1, key);
			stat.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stat.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
package org.ng12306.ngsql;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class NgSQLClient {
	public static void main(String[] args) throws Exception {
		Socket socket = new Socket("localhost", 12306);
//		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//		DataInputStream din = new DataInputStream(socket.getInputStream());
//		BufferedReader in = new BufferedReader(new InputStreamReader(din));
		InputStream in = socket.getInputStream();
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		byte[] buf = new byte[1024];
		//select id from t1
		//"select id,member_id from t1 limit 1
//		String str = bf.readLine();
//		out.println(str);
//		out.flush();
		while (true) {
//			if("exit".equals(str)){
//				break;
//			}
			int len = in.read(buf);
			if(len != -1) {
				System.out.print(new String(buf, "utf-8"));
			}
		}
//		socket.close();
	}

}

package com.netmanage.code;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.androidnettest.code.IDealResult;

public class NetManage {

	ExecutorService executors;

	private static NetManage manage;

	public static NetManage GetInstance() {
		if (manage == null) {
			manage = new NetManage();
		}
		return manage;
	}

	public NetManage() {
		// TODO Auto-generated constructor stub
		executors = Executors.newCachedThreadPool();
	}

	public void SendInfo(final String urlString, final IDealResult dealResult) {

		executors.execute(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				URL url;
				try {
					url = new URL(urlString);
					URLConnection connection = url.openConnection();
					InputStream inputStream = connection.getInputStream();

					int i = 0;
					byte[] bs = new byte[1024];
					StringBuffer stringBuffer = new StringBuffer();
					while ((i = inputStream.read(bs)) != -1) {

						String string = new String(bs, 0, i);
						stringBuffer.append(string);
					}

					String resultString = stringBuffer.toString();

					dealResult.DealWithResultInfo(resultString);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
	}

}

package com.ysobj.mims.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;

public class ProcessOutputReceiver extends Thread {
	private StringWriter buffer = new StringWriter();
	private Reader in;

	public ProcessOutputReceiver(Process process) {
		in = new BufferedReader(new InputStreamReader(
				process.getInputStream()));
	}

	@Override
	public void run() {
		int c;
		try {
			while ((c = in.read()) != -1) {
				buffer.write((char) c);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public String toString() {
		return buffer.toString();
	}
}

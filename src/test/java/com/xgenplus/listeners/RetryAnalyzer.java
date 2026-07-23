package com.xgenplus.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

	private int count = 0;
	private static final int maxRetry = 2; // recommended = 1

	@Override
	public boolean retry(ITestResult result) {

		if (count < maxRetry) {
			count++;

			System.out.println("🔁 Retrying Test: " + result.getName() + " | Attempt: " + count);

			result.setAttribute("retry", true);
			return true;
		}

		return false;
	}
}

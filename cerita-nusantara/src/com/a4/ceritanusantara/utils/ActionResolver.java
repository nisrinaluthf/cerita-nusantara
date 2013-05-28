package com.a4.ceritanusantara.utils;

public interface ActionResolver {
	public void showToast(CharSequence toastMessage, int toastDuration);
	public void showAlertBox(String alertBoxTitle, String alertBoxMessage, 
			String alertBoxButtonText);
	public void openUri(String uri);
	public void showMyList();
}

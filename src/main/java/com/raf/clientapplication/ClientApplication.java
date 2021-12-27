package com.raf.clientapplication;

import java.awt.*;

import javax.swing.*;

import com.raf.clientapplication.view.LoginView;
import com.raf.clientapplication.view.MoviesView;

public class ClientApplication extends JFrame {

	private String token;
	private LoginView loginView;
	private MoviesView moviesView;

	private ClientApplication() throws IllegalAccessException, NoSuchMethodException {
		this.setTitle("Client Application");
		this.setSize(1200, 1200);
		this.setLayout(new BorderLayout());

		loginView = new LoginView();
		this.add(loginView, BorderLayout.NORTH);

		moviesView = new MoviesView();
		this.add(moviesView, BorderLayout.CENTER);

		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private static class InstanceHolder {
		private static ClientApplication instance;

		static {
			try {
				instance = new ClientApplication();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LoginView getLoginView() {
		return loginView;
	}

	public void setLoginView(LoginView loginView) {
		this.loginView = loginView;
	}

	public MoviesView getMoviesView() {
		return moviesView;
	}

	public void setMoviesView(MoviesView moviesView) {
		this.moviesView = moviesView;
	}

	public static ClientApplication getInstance() {
		return InstanceHolder.instance;
	}
}

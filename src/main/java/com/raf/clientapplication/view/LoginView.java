package com.raf.clientapplication.view;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raf.clientapplication.ClientApplication;
import com.raf.clientapplication.restclient.UserServiceRestClient;

public class LoginView extends JPanel {

	private JPanel inputPanel;

	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JTextField usernameInput;
	private JPasswordField passwordInput;

	private JButton loginButton;

	private UserServiceRestClient userServiceRestClient = new UserServiceRestClient();

	private ObjectMapper objectMapper = new ObjectMapper();

	public LoginView() {

		super();
		this.setSize(400, 400);

		this.setLayout(new BorderLayout());

		initInputPanel();

		loginButton = new JButton("Login");
		this.add(loginButton, BorderLayout.SOUTH);
		loginButton.addActionListener((event) -> {

			try {
				String token = userServiceRestClient
					.login(usernameInput.getText(), String.valueOf(passwordInput.getPassword()));
				this.setVisible(false);
				ClientApplication.getInstance().setToken(token);
				System.out.println(token);
				ClientApplication.getInstance().getMoviesView().init();

			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	private void initInputPanel() {

		inputPanel = new JPanel();

		usernameLabel = new JLabel("Username: ");
		passwordLabel = new JLabel("Password: ");

		usernameInput = new JTextField(20);
		passwordInput = new JPasswordField(20);

		inputPanel.add(usernameLabel);
		inputPanel.add(usernameInput);

		inputPanel.add(passwordLabel);
		inputPanel.add(passwordInput);

		this.add(inputPanel, BorderLayout.CENTER);
	}
}

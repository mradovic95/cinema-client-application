package com.raf.clientapplication.view;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;

import com.raf.clientapplication.model.MovieTableModel;
import com.raf.clientapplication.restclient.MovieServiceRestClient;
import com.raf.clientapplication.restclient.dto.MovieListDto;

public class MoviesView extends JPanel {

	private MovieTableModel movieTableModel;
	private JTable movieTable;
	private MovieServiceRestClient movieServiceRestClient;
	private JButton jButton;

	public MoviesView() throws IllegalAccessException, NoSuchMethodException {
		super();
		this.setSize(400, 400);

		movieTableModel = new MovieTableModel();
		movieServiceRestClient = new MovieServiceRestClient();
		movieTable = new JTable(movieTableModel);
		this.setLayout(new BorderLayout());
		JScrollPane scrollPane = new JScrollPane(movieTable);
		this.add(scrollPane, BorderLayout.NORTH);

		jButton = new JButton("Create Order");
		this.add(jButton, BorderLayout.CENTER);

		jButton.addActionListener((event) -> {
			System.out.println(movieTableModel.getMovieListDto().getContent().get(movieTable.getSelectedRow()).getId());
		});

		setVisible(false);
	}

	public void init() throws IOException {
		this.setVisible(true);

		MovieListDto movieListDto = movieServiceRestClient.getMovies();
		movieListDto.getContent().forEach(movieDto -> {
			System.out.println(movieDto);
			movieTableModel.addRow(new Object[]{movieDto.getTitle(), movieDto.getDescription(), movieDto.getId()});
		});

	}

	public MovieTableModel getMovieTableModel() {
		return movieTableModel;
	}

	public JTable getMovieTable() {
		return movieTable;
	}
}

package com.raf.clientapplication.model;

import javax.swing.table.DefaultTableModel;

import com.raf.clientapplication.restclient.dto.MovieDto;
import com.raf.clientapplication.restclient.dto.MovieListDto;

public class MovieTableModel extends DefaultTableModel {


	public MovieTableModel() throws IllegalAccessException, NoSuchMethodException {
		super(new String[]{"Title", "Description"}, 0);
	}

	private MovieListDto movieListDto = new MovieListDto();

	@Override
	public void addRow(Object[] row) {
		super.addRow(row);
		MovieDto dto = new MovieDto();
		dto.setTitle(String.valueOf(row[0]));
		dto.setDescription(String.valueOf(row[1]));
		dto.setId(Long.valueOf(String.valueOf(row[2])));
		movieListDto.getContent().add(dto);
	}

	public MovieListDto getMovieListDto() {
		return movieListDto;
	}

	public void setMovieListDto(MovieListDto movieListDto) {
		this.movieListDto = movieListDto;
	}
}

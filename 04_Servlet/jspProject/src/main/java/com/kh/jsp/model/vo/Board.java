package com.kh.jsp.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Board {
	private int board_no;
	private int board_type;
	private int category_no;
	private String board_title;
	private String board_content;
	private int board_writer;
	private int count;
	private Date create_date;
	private String status;
	private String categoryName;
    private String writerId;
    
	public static Board insertCreateBoard(
										int category_no,
										String board_title,
										String board_content,
										int board_writer) {
		Board b = new Board();
		b.setCategory_no(category_no);
		b.setBoard_title(board_title);
		b.setBoard_content(board_content);
		b.setBoard_writer(board_writer);
		b.setBoard_type(1);
		return b;
	}
	
}

package jpa_JooLib.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "jpa_comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_jpa_comment_commentNo")
    @SequenceGenerator(name = "seq_jpa_comment_commentNo", sequenceName = "seq_jpa_comment_commentNo", allocationSize = 1)
    private int commentNo;
    
    private int boardNo;
    private String userId;
    private String content;
    private LocalDateTime regTime;

    @PrePersist
    public void prePersist() {
        this.regTime = LocalDateTime.now();
    }
    
    public String getFormattedRegTime() {
    	if (this.regTime != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm:ss");
            return this.regTime.format(formatter);
        } else {
            return " "; // or any other default value
        }
    }

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getRegTime() {
		return regTime;
	}

	public void setRegTime(LocalDateTime regTime) {
		this.regTime = regTime;
	}

}

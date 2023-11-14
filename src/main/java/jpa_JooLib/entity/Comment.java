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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_jpa_comment_commentNO")
    @SequenceGenerator(name = "seq_jpa_comment_commentNO", sequenceName = "seq_jpa_comment_commentNO", allocationSize = 1)
    @Column(name = "commentno")
    private Integer id;
    
    @Column(name = "boardno")
    private Integer boardId;

    @Column(name = "userid")
    private String userId;

    @Column(name = "content")
    private String content;

    @Column(name = "regtime")
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
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBoardId() {
		return boardId;
	}

	public void setBoardId(Integer boardId) {
		this.boardId = boardId;
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

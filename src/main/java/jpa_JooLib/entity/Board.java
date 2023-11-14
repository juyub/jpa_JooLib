package jpa_JooLib.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "jpa_board")
public class Board {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_jpa_board_boardno")
    @SequenceGenerator(name = "seq_jpa_board_boardno", sequenceName = "seq_jpa_board_boardno", allocationSize = 1)
    @Column(name = "boardno")
    private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentno")
    private Board parent;
	
	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Board> children = new ArrayList<>();

    @Column(name = "board_level")
    private Integer level = 0;
    
    @Column(name = "userid")
    private String userId;

    @Column(name = "title")
    private String title;

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
            return "N/A"; // or any other default value
        }
    }
    
    @Column(name = "hit")
    private Integer hit = 0;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Board getParent() {
		return parent;
	}

	public void setParent(Board parent) {
		this.parent = parent;
	}

	public List<Board> getChildren() {
		return children;
	}

	public void setChildren(List<Board> children) {
		this.children = children;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public Integer getHit() {
		return hit;
	}

	public void setHit(Integer hit) {
		this.hit = hit;
	}
    
}

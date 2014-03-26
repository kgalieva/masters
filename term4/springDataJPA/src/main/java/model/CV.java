package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
public class CV {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Category> category;

    @ManyToOne(cascade = CascadeType.ALL)
    private User owner;

    private String title;

    private String text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "CV{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CV cv = (CV) o;

        if (!id.equals(cv.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

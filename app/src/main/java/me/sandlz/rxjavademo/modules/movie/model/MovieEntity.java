package me.sandlz.rxjavademo.modules.movie.model;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.util.List;

/**
 * Created by liuzhu on 16/7/31.
 * Description : 电影信息bean
 * Usage :
 */
@Table(name = "MovieEntity")
public class MovieEntity {
    @Column(name = "id",isId = true,autoGen = false)
    private int id;
    @Column(name = "alt")
    private String alt;
    @Column(name = "year")
    private int year;
    @Column(name = "title")
    private String title;
    private String original_title;
    private List<String> genres;
    private List<Cast> casts;
    private List<Cast> directors;
    private AvatarEntity images;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<Cast> getCasts() {
        return casts;
    }

    public void setCasts(List<Cast> casts) {
        this.casts = casts;
    }

    public List<Cast> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Cast> directors) {
        this.directors = directors;
    }

    public AvatarEntity getImages() {
        return images;
    }

    public void setImages(AvatarEntity images) {
        this.images = images;
    }

    public class Cast{
        private String id;
        private String name;
        private String alt;
        private AvatarEntity avatars;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public AvatarEntity getAvatars() {
            return avatars;
        }

        public void setAvatars(AvatarEntity avatars) {
            this.avatars = avatars;
        }

        @Override
        public String toString() {
            return "cast.id=" + id + " cast.name=" + name + " | ";
        }
    }

}


package com.tlh.model;

import java.util.List;

/**
 * Created by tlh on 2016/4/1.
 * 愚人节快乐哦
 */
public class LessonForStudentModel extends BaseModel{

    /**
     * result : success
     * msg : 获取成功
     * data : [{"lessonId":"","term":"","name":"","grade":"","teacher":{"teacherId":"","name":"","college":"","location":""}}]
     */
	
    /**
     * lessonId :
     * term :
     * name :
     * grade :
     * teacher : {"teacherId":"","name":"","college":"","location":""}
     */
    private List<LessonForStudentEntity> data;

    public List<LessonForStudentEntity> getData() {
        return data;
    }

    public void setData(List<LessonForStudentEntity> data) {
        this.data = data;
    }

    public static class LessonForStudentEntity {
        private String id;
        private String term;
        private String name;
        private String grade;
        /**
         * teacherId :
         * name :
         * college :
         * location :
         */

        private Teacher teacher;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTerm() {
            return term;
        }

        public void setTerm(String term) {
            this.term = term;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public Teacher getTeacher() {
            return teacher;
        }

        public void setTeacher(Teacher teacher) {
            this.teacher = teacher;
        }
    }
}

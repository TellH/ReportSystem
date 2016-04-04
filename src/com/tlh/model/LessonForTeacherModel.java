package com.tlh.model;

import java.util.List;

/**
 * Created by tlh on 2016/4/1.
 */
public class LessonForTeacherModel extends BaseModel{

    /**
     * result : success
     * msg : 获取成功
     * data : [{"lessonId":"","term":"","name":"","grade":"","students":[{"studentId":"","name":"","location":"","college":"","major":"","sex":"","enterTime":""}]}]
     */
    /**
     * lessonId :
     * term :
     * name :
     * grade :
     * students : [{"studentId":"","name":"","location":"","college":"","major":"","sex":"","enterTime":""}]
     */

    private List<LessonForTeacherEntity> data;

    public List<LessonForTeacherEntity> getData() {
        return data;
    }

    public void setData(List<LessonForTeacherEntity> data) {
        this.data = data;
    }

    public static class LessonForTeacherEntity {
        private String id;
        private String term;
        private String name;
        private String grade;
        /**
         * studentId :
         * name :
         * location :
         * college :
         * major :
         * sex :
         * enterTime :
         */

        private List<Student> students;

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

        public List<Student> getStudents() {
            return students;
        }

        public void setStudents(List<Student> students) {
            this.students = students;
        }
    }
}

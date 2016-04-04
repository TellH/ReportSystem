package com.tlh.model;

import java.util.List;

import org.apache.struts2.json.annotations.JSON;

/**
 * Created by tlh on 2016/4/1.
 */
public class ReportForStudentModel extends BaseModel{

    /**
     * result : success
     * msg : 获取成功
     * data : [{"reportId":"","name":"","location":"","teacher":{"teacherId":"","name":"","college":"","location":""},"lesson":{"lessonId":"","term":"","grade":""},"date_from":"","date_to":"","major":"","college":"","content":"","note":"","templateUrl":"","status":"","score":"","comment":"","advice":"","docUrl":""},{"reportId":"","name":"","location":"","teacher":{"teacherId":"","name":"","college":"","location":""},"lesson":{"lessonId":"","term":"","grade":""},"date_from":"","date_to":"","major":"","college":"","content":"","note":"","templateUrl":"","status":"","score":"","comment":"","advice":"","docUrl":""}]
     */
    /**
     * reportId :
     * name :
     * location :
     * teacher : {"teacherId":"","name":"","college":"","location":""}
     * lesson : {"lessonId":"","term":"","grade":""}
     * date_from :
     * date_to :
     * major :
     * college :
     * content :
     * note :
     * templateUrl :
     * status :
     * score :
     * comment :
     * advice :
     * docUrl :
     */

    private List<ReportForStudentEntity> data;

    public List<ReportForStudentEntity> getData() {
        return data;
    }

    public void setData(List<ReportForStudentEntity> data) {
        this.data = data;
    }

    public static class ReportForStudentEntity {
        private String id;
        private String name;
        private String location;
        /**
         * teacherId :
         * name :
         * college :
         * location :
         */

        private transient String teacherId;
        @JSON(serialize=false)
        public String getTeacherId() {
			return teacherId;
		}

		public void setTeacherId(String teacherId) {
			this.teacherId = teacherId;
		}

		private Teacher teacher;
        /**
         * lessonId :
         * term :
         * grade :
         */

		
		private String lessonId;
        private Lesson lesson;
        private String date_from;
        private String date_to;
        @JSON(serialize=false)
        public String getLessonId() {
			return lessonId;
		}

		public void setLessonId(String lessonId) {
			this.lessonId = lessonId;
		}

		private String major;
        private String college;
        private String content;
        private String note;
        private String templateUrl;
        private int status;
        private String score;
        private String comment;
        private String advice;
        private String docUrl;

        @JSON(name="reportId")
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

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public Teacher getTeacher() {
            return teacher;
        }

        public void setTeacher(Teacher teacher) {
            this.teacher = teacher;
        }

        public Lesson getLesson() {
            return lesson;
        }

        public void setLesson(Lesson lesson) {
            this.lesson = lesson;
        }

        public String getDate_from() {
            return date_from;
        }

        public void setDate_from(String date_from) {
            this.date_from = date_from;
        }

        public String getDate_to() {
            return date_to;
        }

        public void setDate_to(String date_to) {
            this.date_to = date_to;
        }

        public String getMajor() {
            return major;
        }

        public void setMajor(String major) {
            this.major = major;
        }

        public String getCollege() {
            return college;
        }

        public void setCollege(String college) {
            this.college = college;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getTemplateUrl() {
            return templateUrl;
        }

        public void setTemplateUrl(String templateUrl) {
            this.templateUrl = templateUrl;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getAdvice() {
            return advice;
        }

        public void setAdvice(String advice) {
            this.advice = advice;
        }

        public String getDocUrl() {
            return docUrl;
        }

        public void setDocUrl(String docUrl) {
            this.docUrl = docUrl;
        }

    }
}

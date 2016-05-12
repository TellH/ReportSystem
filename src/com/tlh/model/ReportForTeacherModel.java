package com.tlh.model;

import java.util.List;

import org.apache.struts2.json.annotations.JSON;

/**
 * Created by tlh on 2016/4/1.
 */
public class ReportForTeacherModel extends BaseModel{

    /**
     * result : success
     * msg : 获取成功
     * data : [{"reportId":"","name":"","location":"","lessons":{"lessonId":"","term":"","grade":"","name":""},"date_from":"","date_to":"","major":"","college":"","content":"","note":"","templateUrl":"","students":[{"id":"","status":"","score":"","comment":"","advice":"","docUrl":"","name":"","college":"","major":"","sex":"","location":"","enterTime":""}]}]
     */
    /**
     * reportId :
     * name :
     * location :
     * lessons : {"lessonId":"","term":"","grade":"","name":""}
     * date_from :
     * date_to :
     * major :
     * college :
     * content :
     * note :
     * templateUrl :
     * students : [{"id":"","status":"","score":"","comment":"","advice":"","docUrl":"","name":"","college":"","major":"","sex":"","location":"","enterTime":""}]
     */
	private int totalPages=0;
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
    private List<ReportForTeacherEntity> data;

    public List<ReportForTeacherEntity> getData() {
        return data;
    }

    public void setData(List<ReportForTeacherEntity> data) {
        this.data = data;
    }

    public static class ReportForTeacherEntity {
        private String id;
        private String name;
        private String location;
        /**
         * lessonId :
         * term :
         * grade :
         * name :
         */

        private transient String lessonId;
        public String getLessonId() {
			return lessonId;
		}

		public void setLessonId(String lessonId) {
			this.lessonId = lessonId;
		}

		private Lesson lesson;
        private String date_from;
        private String date_to;
        private String major;
        private String college;
        private String content;
        private String note;
        private String templateUrl;
        /**
         * id :
         * status :
         * score :
         * comment :
         * advice :
         * docUrl :
         * name :
         * college :
         * major :
         * sex :
         * location :
         * enterTime :
         */

        private List<StudentWithReport> students;
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

        public List<StudentWithReport> getStudents() {
            return students;
        }

        public void setStudents(List<StudentWithReport> students) {
            this.students = students;
        }

    }
}

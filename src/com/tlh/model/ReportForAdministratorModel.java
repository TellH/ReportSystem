package com.tlh.model;

import java.util.List;

/**
 * Created by tlh on 2016/4/1.
 */
public class ReportForAdministratorModel extends BaseModel {

    /**
     * result : success
     * msg : 获取成功
     * data : [{"reportId":"","name":"","location":"","teacher":{"teacherId":"","name":"","location":"","college":""},"lesson":{"lessonId":"","term":"","grade":"","name":""},"date_from":"","date_to":"","major":"","college":"","content":"","note":"","templateUrl":""}]
     */
    /**
     * reportId :
     * name :
     * location :
     * teacher : {"teacherId":"","name":"","location":"","college":""}
     * lesson : {"lessonId":"","term":"","grade":"","name":""}
     * date_from :
     * date_to :
     * major :
     * college :
     * content :
     * note :
     * templateUrl :
     */

    private List<ReportForAdministratorEntity> data;

    public List<ReportForAdministratorEntity> getData() {
        return data;
    }

    public void setData(List<ReportForAdministratorEntity> data) {
        this.data = data;
    }

    public static class ReportForAdministratorEntity {
        private String reportId;
        private String name;
        private String location;
        /**
         * teacherId :
         * name :
         * location :
         * college :
         */

        private Teacher teacher;
        /**
         * lessonId :
         * term :
         * grade :
         * name :
         */

        private Lesson lesson;
        private String date_from;
        private String date_to;
        private String major;
        private String college;
        private String content;
        private String note;
        private String templateUrl;

        public String getReportId() {
            return reportId;
        }

        public void setReportId(String reportId) {
            this.reportId = reportId;
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
    }
}

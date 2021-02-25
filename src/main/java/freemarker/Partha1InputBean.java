package freemarker;

import java.util.Arrays;

public class Partha1InputBean {
 
        private int auditId; //1,
        private String objectId;// "prj5698",
        private String objectTitle;//"Project 5698",
        private String objectType;// "PROJECT",
        private String stage;// "Stage-1",
        private String commChnl;// "WEB",
        private String commLang;// "FRENCH",
        private String plndStrtDt;// "2021-02-20T14:12:18.038Z",
        private String plndEndDt;// "2021-02-20T15:12:18.038Z",
        private String actlEndDt;// "2021-02-20T15:12:18.038Z",
        private String status;// "COMPLETED",
        private String mjrNc;// "1",
        private String nextRevDt;// "2021-02-20T15:12:18.038Z",
        private String auditee;// "d516d648-646d-4522-abaa-5da6a8f94b64",
        private String auditeeName;// "Roger Moore",
        private String auditeeEmail;// "rm@mail.com",
        private String auditeeLocations;// "UK",
        private String auditeeTimezone;// "GMT",
        private String creBy;// "be5b8aef-9218-4391-b74b-568e8146476b",
        private String creAt;// "2021-02-20T13;43;12.143Z",
        private String updBy;// "be5b8aef-9218-4391-b74b-568e8146476b",
        private String updAt;// "2021-02-20T14:12:18.821Z",
        private FindingsText findingsText[];


        static class FindingsText{
            private String type;
            private String note;
            public String getType() {
                return type;
            }
            public void setType(String type) {
                this.type = type;
            }
            public String getNote() {
                return note;
            }
            public void setNote(String note) {
                this.note = note;
            }
            @Override
            public String toString() {
                return "FindingsText [type=" + type + ", note=" + note + "]";
            }
        }

        public int getAuditId() {
            return auditId;
        }

        public void setAuditId(int auditId) {
            this.auditId = auditId;
        }

        public String getObjectId() {
            return objectId;
        }

        public void setObjectId(String objectId) {
            this.objectId = objectId;
        }

        public String getObjectTitle() {
            return objectTitle;
        }

        public void setObjectTitle(String objectTitle) {
            this.objectTitle = objectTitle;
        }

        public String getObjectType() {
            return objectType;
        }

        public void setObjectType(String objectType) {
            this.objectType = objectType;
        }

        public String getStage() {
            return stage;
        }

        public void setStage(String stage) {
            this.stage = stage;
        }

        public String getCommChnl() {
            return commChnl;
        }

        public void setCommChnl(String commChnl) {
            this.commChnl = commChnl;
        }

        public String getCommLang() {
            return commLang;
        }

        public void setCommLang(String commLang) {
            this.commLang = commLang;
        }

        public String getPlndStrtDt() {
            return plndStrtDt;
        }

        public void setPlndStrtDt(String plndStrtDt) {
            this.plndStrtDt = plndStrtDt;
        }

        public String getPlndEndDt() {
            return plndEndDt;
        }

        public void setPlndEndDt(String plndEndDt) {
            this.plndEndDt = plndEndDt;
        }

        public String getActlEndDt() {
            return actlEndDt;
        }

        public void setActlEndDt(String actlEndDt) {
            this.actlEndDt = actlEndDt;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMjrNc() {
            return mjrNc;
        }

        public void setMjrNc(String mjrNc) {
            this.mjrNc = mjrNc;
        }

        public String getNextRevDt() {
            return nextRevDt;
        }

        public void setNextRevDt(String nextRevDt) {
            this.nextRevDt = nextRevDt;
        }

        public String getAuditee() {
            return auditee;
        }

        public void setAuditee(String auditee) {
            this.auditee = auditee;
        }

        public String getAuditeeName() {
            return auditeeName;
        }

        public void setAuditeeName(String auditeeName) {
            this.auditeeName = auditeeName;
        }

        public String getAuditeeEmail() {
            return auditeeEmail;
        }

        public void setAuditeeEmail(String auditeeEmail) {
            this.auditeeEmail = auditeeEmail;
        }

        public String getAuditeeLocations() {
            return auditeeLocations;
        }

        public void setAuditeeLocations(String auditeeLocations) {
            this.auditeeLocations = auditeeLocations;
        }

        public String getAuditeeTimezone() {
            return auditeeTimezone;
        }

        public void setAuditeeTimezone(String auditeeTimezone) {
            this.auditeeTimezone = auditeeTimezone;
        }

        public String getCreBy() {
            return creBy;
        }

        public void setCreBy(String creBy) {
            this.creBy = creBy;
        }

        public String getCreAt() {
            return creAt;
        }

        public void setCreAt(String creAt) {
            this.creAt = creAt;
        }

        public String getUpdBy() {
            return updBy;
        }

        public void setUpdBy(String updBy) {
            this.updBy = updBy;
        }

        public String getUpdAt() {
            return updAt;
        }

        public void setUpdAt(String updAt) {
            this.updAt = updAt;
        }

        public FindingsText[] getFindingsText() {
            return findingsText;
        }

        public void setFindingsText(FindingsText[] findingsText) {
            this.findingsText = findingsText;
        }
        public ColumnsPartha1InputBean convert() {
            ColumnsPartha1InputBean output = new ColumnsPartha1InputBean();
            output.setAuditId(""+auditId); //1,
            output.setObjectId(objectId);// "prj5698",
            output.setObjectTitle(objectTitle);//"Project 5698",
            output.setObjectType(objectType);// "PROJECT",
            output.setStage(stage);// "Stage-1",
            output.setCommChnl(commChnl);// "WEB",
            output.setCommLang(commLang);// "FRENCH",
            output.setPlndStrtDt(plndStrtDt);// "2021-02-20T14:12:18.038Z",
            output.setPlndEndDt(plndEndDt);// "2021-02-20T15:12:18.038Z",
            output.setActlEndDt(actlEndDt);// "2021-02-20T15:12:18.038Z",
            output.setStatus(status);// "COMPLETED",
            output.setMjrNc(mjrNc);// "1",
            output.setNextRevDt(nextRevDt);// "2021-02-20T15:12:18.038Z",
            output.setAuditee(auditee);// "d516d648-646d-4522-abaa-5da6a8f94b64",
            output.setAuditeeName(auditeeName);// "Roger Moore",
            output.setAuditeeEmail(auditeeEmail);// "rm@mail.com",
            output.setAuditeeLocations(auditeeLocations);// "UK",
            output.setAuditeeTimezone(auditeeTimezone);// "GMT",
            output.setCreBy(creBy);// "be5b8aef-9218-4391-b74b-568e8146476b",
            output.setCreAt(creAt);// "2021-02-20T13();43();12.143Z",
            output.setUpdBy(updBy);// "be5b8aef-9218-4391-b74b-568e8146476b",
            output.setUpdAt(updAt);// "2021-02-20T14:12:18.821Z",
            if (findingsText != null) {
            ColumnsPartha1InputBean.FindingsText[] tex = new ColumnsPartha1InputBean.FindingsText[findingsText.length];
            for(int i = 0; i <findingsText.length;i++) {
                tex[i] = new ColumnsPartha1InputBean.FindingsText();
                if(findingsText[i] != null) {
                    tex[i].setType(findingsText[i].getType());
                    tex[i].setNote(findingsText[i].getNote());
                }
            }
            output.setFindingsText(tex);
            }
            return output;
        }

        @Override
        public String toString() {
            return "JsonBean1 [auditId=" + auditId + ", objectId=" + objectId + ", objectTitle="
                    + objectTitle + ", objectType=" + objectType + ", stage=" + stage
                    + ", commChnl=" + commChnl + ", commLang=" + commLang + ", plndStrtDt="
                    + plndStrtDt + ", plndEndDt=" + plndEndDt + ", actlEndDt=" + actlEndDt
                    + ", status=" + status + ", mjrNc=" + mjrNc + ", nextRevDt=" + nextRevDt
                    + ", auditee=" + auditee + ", auditeeName=" + auditeeName + ", auditeeEmail="
                    + auditeeEmail + ", auditeeLocations=" + auditeeLocations + ", auditeeTimezone="
                    + auditeeTimezone + ", creBy=" + creBy + ", creAt=" + creAt + ", updBy=" + updBy
                    + ", updAt=" + updAt + ", findingsText=" + Arrays.toString(findingsText) + "]";
        }
    }


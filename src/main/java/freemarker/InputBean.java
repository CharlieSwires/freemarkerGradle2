package freemarker;

public class InputBean {

    private String fileCSV;
    private String headingsCSV;
    private String printedby;
    private String datetime;
    private String title;

    public InputBean() {
    }

    public String getFileCSV() {
        return fileCSV;
    }

    public void setFileCSV(String fileCSV) {
        this.fileCSV = fileCSV;
    }

    public String getHeadingsCSV() {
        return headingsCSV;
    }

    public void setHeadingsCSV(String headingsCSV) {
        this.headingsCSV = headingsCSV;
    }

    public String getPrintedby() {
        return printedby;
    }

    public void setPrintedby(String printedby) {
        this.printedby = printedby;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}

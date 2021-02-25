package freemarker;

public class InputBeanGeneral {

    private String inputFTL;
    private String replacementStringsCSV;
    
    public InputBeanGeneral() {
        
    }


    public String getInputFTL() {
        return inputFTL;
    }

    public void setInputFTL(String inputFTL) {
        this.inputFTL = inputFTL;
    }


    public String getReplacementStringsCSV() {
        return replacementStringsCSV;
    }


    public void setReplacementStringsCSV(String replacementStringsCSV) {
        this.replacementStringsCSV = replacementStringsCSV;
    }
}

public class Email 
{
    private String text;       
    private boolean isSpam;    

    public Email(String text, boolean isSpam) {
        this.text = text;
        this.isSpam = isSpam;
    }

    public String getText() {
        return text;             
     }

    public boolean isSpam() {
        return isSpam;         
    }
}
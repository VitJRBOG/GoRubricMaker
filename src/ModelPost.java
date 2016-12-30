class ModelPost {

    private int varIntCategoryNumber;
    private int varIntPostNumber;
    private String varStringNumber;
    private String varStringBody;
    private String[] arrayStringPhoto;
    private String varStringLinkAuthor;
    private String varStringNameAuthor;
    private String varStringAuthor;

    void setCategoryNumber(int varIntCategoryNumber) {
        this.varIntCategoryNumber = varIntCategoryNumber;
    }
    void setPostNumber(int varIntPostNumber) {
        this.varIntPostNumber = varIntPostNumber;
    }
    void setBody(String varStringBody) {
        this.varStringBody = varStringBody;
    }
    void setPhoto(String[] arrayStringPhoto) {
        this.arrayStringPhoto = arrayStringPhoto;
    }
    void setLinkAuthor(String varStringLinkAuthor) {
        varStringLinkAuthor = varStringLinkAuthor.replace("https://vk.com/", "");
        this.varStringLinkAuthor = varStringLinkAuthor;
    }
    void setNameAuthor(String varStringNameAuthor) {
        this.varStringNameAuthor = varStringNameAuthor;
    }


    int getCategoryNumber() {
        return varIntCategoryNumber;
    }
    int getPostNumber() {
        return varIntPostNumber;
    }
    String getNumber() {
        this.varStringNumber = this.varIntCategoryNumber + "." + this.varIntPostNumber + ") ";
        return varStringNumber;
    }
    String getBody() {
        return varStringBody;
    }
    String[] getPhoto() {
        return arrayStringPhoto;
    }
    String getAuthor() {
        this.varStringAuthor = "@" + varStringLinkAuthor + " (" + varStringNameAuthor + ")";
        return varStringAuthor;
    }

    ModelPost() {
        this.varIntCategoryNumber = 0;
        this.varIntPostNumber = 0;
        this.varStringNumber = varIntCategoryNumber + "." + varIntPostNumber;
        this.varStringBody = "null";
        this.arrayStringPhoto = null;
        this.varStringLinkAuthor = "null";
        this.varStringNameAuthor = "null";
        this.varStringAuthor = "null";
    }
}

package controllers.common;

/**
 * Error message for Api.
 */
public final class ErrorMessage {
    /**
     * Error message.
     */
    private String errMsg;
    /**
     * Custom message.
     */
    private String ctmMsg;


    public ErrorMessage() {
        this(null);
    }

    public ErrorMessage(String errMsg) {
        this(errMsg, null);
    }

    public ErrorMessage(String errMsg, String ctmMsg) {
        this.errMsg = errMsg;
        this.ctmMsg = ctmMsg;
    }

    public String getCtmMsg() {
        return ctmMsg;
    }

    public void setCtmMsg(String ctmMsg) {
        this.ctmMsg = ctmMsg;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}

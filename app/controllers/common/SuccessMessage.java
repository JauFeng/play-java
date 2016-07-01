package controllers.common;

/**
 * Success message for login Api.
 */
public final class SuccessMessage {
    /**
     * Token.
     */
    private String token;

    /**
     * Nick Name.
     */
    private String roleName;

    /**
     * User level.
     */
    private int level;

    /**
     * Experience.
     */
    private int exp;

    /**
     * Portrait.
     */
    private String portrait;

    /**
     * signature.
     */
    private String signature;

    /**
     * Gender.
     */
    private int sex;

    /**
     * Birthday.
     */
    private String birthday;

    /**
     * Address.
     */
    private String address;

    /**
     * Version.
     */
    // TODO: 2016/6/30  what is version?
    private float version;


    public SuccessMessage(String token,
                          String roleName,
                          int level,
                          int exp,
                          String portrait,
                          String signature,
                          int sex,
                          String birthday,
                          String address,
                          float version) {
        this.token = token;
        this.roleName = roleName;
        this.level = level;
        this.exp = exp;
        this.portrait = portrait;
        this.signature = signature;
        this.sex = sex;
        this.birthday = birthday;
        this.address = address;
        this.version = version;
    }

    public SuccessMessage(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getVersion() {
        return version;
    }

    public void setVersion(float version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "SuccessMessage{" +
                "token='" + token + '\'' +
                ", roleName='" + roleName + '\'' +
                ", level=" + level +
                ", exp=" + exp +
                ", portrait='" + portrait + '\'' +
                ", signature='" + signature + '\'' +
                ", sex=" + sex +
                ", birthday='" + birthday + '\'' +
                ", address='" + address + '\'' +
                ", version=" + version +
                '}';
    }
}

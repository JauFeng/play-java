package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "t_user_info")
public class UserInfo extends Model {

    public static final Finder<String, UserInfo> finder = new Finder<>(UserInfo.class);

    /**
     * 用户ID
     */
    @Id
    @Column(name = "user_id", unique = true, nullable = false)
    private long userId;
    /**
     * 用户昵称
     */
    @Constraints.Required
    @Column(name = "role_name", length = 50, nullable = false)
    private String roleName;
    /**
     * 设备id
     */
    @Column(name = "device_id", length = 128)
    private String deviceId;
    /**
     * 手机
     */
    @Column(name = "phone_number", length = 20)
    private String phoneNumber;
    /**
     * 邮箱
     */
    @Column(name = "mail_address", length = 128)
    private String mailAddress;
    /**
     * 密码
     */
    @Constraints.Required
    @Column(name = "password", length = 50)
    private String password;
    /**
     * 等级
     */
    @Column(name = "level", length = 11)
    private int level;
    /**
     * 经验值
     */
    @Column(name = "exp", length = 11)
    private int experience;
    /**
     * 签名
     */
    @Column(name = "signature", length = 256)
    private String signature;
    /**
     * 头像
     */
    @Column(name = "portrait", length = 128)
    private String portrait;
    /**
     * 性别
     */
    @Column(name = "sex", length = 11)
    private byte sex;
    /**
     * 出生日期
     */
    @Column(name = "birth_day")
    private Date birthDay;
    /**
     * 角色创建时间
     **/
    @Column(name = "role_create_time")
    private Date roleCreateTime;
    /**
     * 地址
     */
    @Column(name = "address", length = 256)
    private String address;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int exp) {
        this.experience = exp;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public byte getSex() {
        return sex;
    }

    public void setSex(byte sex) {
        this.sex = sex;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public Date getRoleCreateTime() {
        return roleCreateTime;
    }

    public void setRoleCreateTime(Date roleCreateTime) {
        this.roleCreateTime = roleCreateTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", roleName='" + roleName + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", mailAddress='" + mailAddress + '\'' +
                ", password='" + password + '\'' +
                ", level=" + level +
                ", experience=" + experience +
                ", signature='" + signature + '\'' +
                ", portrait='" + portrait + '\'' +
                ", sex=" + sex +
                ", birthDay=" + birthDay +
                ", roleCreateTime=" + roleCreateTime +
                ", address='" + address + '\'' +
                '}';
    }
}

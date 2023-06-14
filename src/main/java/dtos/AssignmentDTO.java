package dtos;

import java.time.LocalDateTime;
import java.util.List;

public class AssignmentDTO {

    private String id;
    private String familyName;
    private LocalDateTime createDate;
    private String contactInfo;
    private List<String> memberIds;



    public AssignmentDTO(String id, String familyName, LocalDateTime createDate, String contactInfo, List<String> memberIds) {
        this.id = id;
        this.familyName = familyName;
        this.createDate = createDate;
        this.contactInfo = contactInfo;
        this.memberIds = memberIds;
    }

    public AssignmentDTO(String familyName, LocalDateTime createDate, String contactInfo, List<String> memberIds) {
        this.familyName = familyName;
        this.createDate = createDate;
        this.contactInfo = contactInfo;
        this.memberIds = memberIds;
    }

    public AssignmentDTO(String familyName, LocalDateTime createDate, String contactInfo) {
        this.familyName = familyName;
        this.createDate = createDate;
        this.contactInfo = contactInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public List<String> getMemberIds() {
        return memberIds;
    }

    public void setMemberIds(List<String> memberIds) {
        this.memberIds = memberIds;
    }

    @Override
    public String toString() {
        return "AssignmentDTO{" +
                "id='" + id + '\'' +
                ", familyName='" + familyName + '\'' +
                ", createDate=" + createDate +
                ", contactInfo='" + contactInfo + '\'' +
                ", memberIds=" + memberIds +
                '}';
    }
}

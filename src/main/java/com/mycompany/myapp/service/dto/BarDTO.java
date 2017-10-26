package com.mycompany.myapp.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the Bar entity.
 */
public class BarDTO implements Serializable {

    private Long id;

    @Size(min = 2, max = 20)
    private String name;

    @Lob
    private byte[] photo;
    private String photoContentType;

    private String startDate;

    private String endDate;

    private Double coverCharge;

    private String adresse;

    private String type;

    private String specialEvent;

    private Integer from4To6;

    private Integer from6To8;

    private Integer from8To10;

    private Integer from10ToMid;

    private Integer fromMidTo2;

    private Integer from2To4;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getPhotoContentType() {
        return photoContentType;
    }

    public void setPhotoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Double getCoverCharge() {
        return coverCharge;
    }

    public void setCoverCharge(Double coverCharge) {
        this.coverCharge = coverCharge;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSpecialEvent() {
        return specialEvent;
    }

    public void setSpecialEvent(String specialEvent) {
        this.specialEvent = specialEvent;
    }

    public Integer getFrom4To6() {
        return from4To6;
    }

    public void setFrom4To6(Integer from4To6) {
        this.from4To6 = from4To6;
    }

    public Integer getFrom6To8() {
        return from6To8;
    }

    public void setFrom6To8(Integer from6To8) {
        this.from6To8 = from6To8;
    }

    public Integer getFrom8To10() {
        return from8To10;
    }

    public void setFrom8To10(Integer from8To10) {
        this.from8To10 = from8To10;
    }

    public Integer getFrom10ToMid() {
        return from10ToMid;
    }

    public void setFrom10ToMid(Integer from10ToMid) {
        this.from10ToMid = from10ToMid;
    }

    public Integer getFromMidTo2() {
        return fromMidTo2;
    }

    public void setFromMidTo2(Integer fromMidTo2) {
        this.fromMidTo2 = fromMidTo2;
    }

    public Integer getFrom2To4() {
        return from2To4;
    }

    public void setFrom2To4(Integer from2To4) {
        this.from2To4 = from2To4;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BarDTO barDTO = (BarDTO) o;
        if(barDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), barDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BarDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", photo='" + getPhoto() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", coverCharge='" + getCoverCharge() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", type='" + getType() + "'" +
            ", specialEvent='" + getSpecialEvent() + "'" +
            ", from4To6='" + getFrom4To6() + "'" +
            ", from6To8='" + getFrom6To8() + "'" +
            ", from8To10='" + getFrom8To10() + "'" +
            ", from10ToMid='" + getFrom10ToMid() + "'" +
            ", fromMidTo2='" + getFromMidTo2() + "'" +
            ", from2To4='" + getFrom2To4() + "'" +
            "}";
    }
}

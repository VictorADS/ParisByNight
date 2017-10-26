package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Bar.
 */
@Entity
@Table(name = "bar")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Bar implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(min = 2, max = 20)
    @Column(name = "name", length = 20)
    private String name;

    @Lob
    @Column(name = "photo")
    private byte[] photo;

    @Column(name = "photo_content_type")
    private String photoContentType;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    @Column(name = "cover_charge")
    private Double coverCharge;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "jhi_type")
    private String type;

    @Column(name = "special_event")
    private String specialEvent;

    @Column(name = "from_4_to_6")
    private Integer from4To6;

    @Column(name = "from_6_to_8")
    private Integer from6To8;

    @Column(name = "from_8_to_10")
    private Integer from8To10;

    @Column(name = "from_10_to_mid")
    private Integer from10ToMid;

    @Column(name = "from_mid_to_2")
    private Integer fromMidTo2;

    @Column(name = "from_2_to_4")
    private Integer from2To4;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Bar name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public Bar photo(byte[] photo) {
        this.photo = photo;
        return this;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getPhotoContentType() {
        return photoContentType;
    }

    public Bar photoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
        return this;
    }

    public void setPhotoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
    }

    public String getStartDate() {
        return startDate;
    }

    public Bar startDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public Bar endDate(String endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Double getCoverCharge() {
        return coverCharge;
    }

    public Bar coverCharge(Double coverCharge) {
        this.coverCharge = coverCharge;
        return this;
    }

    public void setCoverCharge(Double coverCharge) {
        this.coverCharge = coverCharge;
    }

    public String getAdresse() {
        return adresse;
    }

    public Bar adresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getType() {
        return type;
    }

    public Bar type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSpecialEvent() {
        return specialEvent;
    }

    public Bar specialEvent(String specialEvent) {
        this.specialEvent = specialEvent;
        return this;
    }

    public void setSpecialEvent(String specialEvent) {
        this.specialEvent = specialEvent;
    }

    public Integer getFrom4To6() {
        return from4To6;
    }

    public Bar from4To6(Integer from4To6) {
        this.from4To6 = from4To6;
        return this;
    }

    public void setFrom4To6(Integer from4To6) {
        this.from4To6 = from4To6;
    }

    public Integer getFrom6To8() {
        return from6To8;
    }

    public Bar from6To8(Integer from6To8) {
        this.from6To8 = from6To8;
        return this;
    }

    public void setFrom6To8(Integer from6To8) {
        this.from6To8 = from6To8;
    }

    public Integer getFrom8To10() {
        return from8To10;
    }

    public Bar from8To10(Integer from8To10) {
        this.from8To10 = from8To10;
        return this;
    }

    public void setFrom8To10(Integer from8To10) {
        this.from8To10 = from8To10;
    }

    public Integer getFrom10ToMid() {
        return from10ToMid;
    }

    public Bar from10ToMid(Integer from10ToMid) {
        this.from10ToMid = from10ToMid;
        return this;
    }

    public void setFrom10ToMid(Integer from10ToMid) {
        this.from10ToMid = from10ToMid;
    }

    public Integer getFromMidTo2() {
        return fromMidTo2;
    }

    public Bar fromMidTo2(Integer fromMidTo2) {
        this.fromMidTo2 = fromMidTo2;
        return this;
    }

    public void setFromMidTo2(Integer fromMidTo2) {
        this.fromMidTo2 = fromMidTo2;
    }

    public Integer getFrom2To4() {
        return from2To4;
    }

    public Bar from2To4(Integer from2To4) {
        this.from2To4 = from2To4;
        return this;
    }

    public void setFrom2To4(Integer from2To4) {
        this.from2To4 = from2To4;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Bar bar = (Bar) o;
        if (bar.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bar.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Bar{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", photo='" + getPhoto() + "'" +
            ", photoContentType='" + photoContentType + "'" +
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

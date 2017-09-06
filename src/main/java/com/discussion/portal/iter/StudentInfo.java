
package com.discussion.portal.iter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "mothersname",
    "cdistrict",
    "academicyear",
    "gender",
    "cstatename",
    "scellno",
    "admissionyear",
    "ccityname",
    "ppin",
    "programdesc",
    "pdistrict",
    "sectioncode",
    "enrollmentno",
    "parentoccupation",
    "branchdesc",
    "ptelephoneno",
    "dateofbirth",
    "semailid",
    "paddress1",
    "paddress2",
    "paddress3",
    "pemailid",
    "pcityname",
    "stelephoneno",
    "stynumber",
    "caddress3",
    "bloodgroup",
    "caddress1",
    "caddress2",
    "nationality",
    "maritalstatus",
    "pstatename",
    "fathersname",
    "accountno",
    "name",
    "pcellno",
    "bankname",
    "category",
    "cpin"
})
public class StudentInfo {

    @JsonProperty("mothersname")
    private String mothersname;
    @JsonProperty("cdistrict")
    private String cdistrict;
    @JsonProperty("academicyear")
    private String academicyear;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("cstatename")
    private String cstatename;
    @JsonProperty("scellno")
    private String scellno;
    @JsonProperty("admissionyear")
    private String admissionyear;
    @JsonProperty("ccityname")
    private String ccityname;
    @JsonProperty("ppin")
    private Integer ppin;
    @JsonProperty("programdesc")
    private String programdesc;
    @JsonProperty("pdistrict")
    private String pdistrict;
    @JsonProperty("sectioncode")
    private String sectioncode;
    @JsonProperty("enrollmentno")
    private String enrollmentno;
    @JsonProperty("parentoccupation")
    private Object parentoccupation;
    @JsonProperty("branchdesc")
    private String branchdesc;
    @JsonProperty("ptelephoneno")
    private Object ptelephoneno;
    @JsonProperty("dateofbirth")
    private String dateofbirth;
    @JsonProperty("semailid")
    private String semailid;
    @JsonProperty("paddress1")
    private String paddress1;
    @JsonProperty("paddress2")
    private Object paddress2;
    @JsonProperty("paddress3")
    private String paddress3;
    @JsonProperty("pemailid")
    private String pemailid;
    @JsonProperty("pcityname")
    private Object pcityname;
    @JsonProperty("stelephoneno")
    private Object stelephoneno;
    @JsonProperty("stynumber")
    private Integer stynumber;
    @JsonProperty("caddress3")
    private Object caddress3;
    @JsonProperty("bloodgroup")
    private String bloodgroup;
    @JsonProperty("caddress1")
    private String caddress1;
    @JsonProperty("caddress2")
    private String caddress2;
    @JsonProperty("nationality")
    private String nationality;
    @JsonProperty("maritalstatus")
    private Object maritalstatus;
    @JsonProperty("pstatename")
    private String pstatename;
    @JsonProperty("fathersname")
    private String fathersname;
    @JsonProperty("accountno")
    private Object accountno;
    @JsonProperty("name")
    private String name;
    @JsonProperty("pcellno")
    private String pcellno;
    @JsonProperty("bankname")
    private Object bankname;
    @JsonProperty("category")
    private String category;
    @JsonProperty("cpin")
    private Integer cpin;

    @JsonProperty("mothersname")
    public String getMothersname() {
        return mothersname;
    }

    @JsonProperty("mothersname")
    public void setMothersname(String mothersname) {
        this.mothersname = mothersname;
    }

    @JsonProperty("cdistrict")
    public String getCdistrict() {
        return cdistrict;
    }

    @JsonProperty("cdistrict")
    public void setCdistrict(String cdistrict) {
        this.cdistrict = cdistrict;
    }

    @JsonProperty("academicyear")
    public String getAcademicyear() {
        return academicyear;
    }

    @JsonProperty("academicyear")
    public void setAcademicyear(String academicyear) {
        this.academicyear = academicyear;
    }

    @JsonProperty("gender")
    public String getGender() {
        return gender;
    }

    @JsonProperty("gender")
    public void setGender(String gender) {
        this.gender = gender;
    }

    @JsonProperty("cstatename")
    public String getCstatename() {
        return cstatename;
    }

    @JsonProperty("cstatename")
    public void setCstatename(String cstatename) {
        this.cstatename = cstatename;
    }

    @JsonProperty("scellno")
    public String getScellno() {
        return scellno;
    }

    @JsonProperty("scellno")
    public void setScellno(String scellno) {
        this.scellno = scellno;
    }

    @JsonProperty("admissionyear")
    public String getAdmissionyear() {
        return admissionyear;
    }

    @JsonProperty("admissionyear")
    public void setAdmissionyear(String admissionyear) {
        this.admissionyear = admissionyear;
    }

    @JsonProperty("ccityname")
    public String getCcityname() {
        return ccityname;
    }

    @JsonProperty("ccityname")
    public void setCcityname(String ccityname) {
        this.ccityname = ccityname;
    }

    @JsonProperty("ppin")
    public Integer getPpin() {
        return ppin;
    }

    @JsonProperty("ppin")
    public void setPpin(Integer ppin) {
        this.ppin = ppin;
    }

    @JsonProperty("programdesc")
    public String getProgramdesc() {
        return programdesc;
    }

    @JsonProperty("programdesc")
    public void setProgramdesc(String programdesc) {
        this.programdesc = programdesc;
    }

    @JsonProperty("pdistrict")
    public String getPdistrict() {
        return pdistrict;
    }

    @JsonProperty("pdistrict")
    public void setPdistrict(String pdistrict) {
        this.pdistrict = pdistrict;
    }

    @JsonProperty("sectioncode")
    public String getSectioncode() {
        return sectioncode;
    }

    @JsonProperty("sectioncode")
    public void setSectioncode(String sectioncode) {
        this.sectioncode = sectioncode;
    }

    @JsonProperty("enrollmentno")
    public String getEnrollmentno() {
        return enrollmentno;
    }

    @JsonProperty("enrollmentno")
    public void setEnrollmentno(String enrollmentno) {
        this.enrollmentno = enrollmentno;
    }

    @JsonProperty("parentoccupation")
    public Object getParentoccupation() {
        return parentoccupation;
    }

    @JsonProperty("parentoccupation")
    public void setParentoccupation(Object parentoccupation) {
        this.parentoccupation = parentoccupation;
    }

    @JsonProperty("branchdesc")
    public String getBranchdesc() {
        return branchdesc;
    }

    @JsonProperty("branchdesc")
    public void setBranchdesc(String branchdesc) {
        this.branchdesc = branchdesc;
    }

    @JsonProperty("ptelephoneno")
    public Object getPtelephoneno() {
        return ptelephoneno;
    }

    @JsonProperty("ptelephoneno")
    public void setPtelephoneno(Object ptelephoneno) {
        this.ptelephoneno = ptelephoneno;
    }

    @JsonProperty("dateofbirth")
    public String getDateofbirth() {
        return dateofbirth;
    }

    @JsonProperty("dateofbirth")
    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    @JsonProperty("semailid")
    public String getSemailid() {
        return semailid;
    }

    @JsonProperty("semailid")
    public void setSemailid(String semailid) {
        this.semailid = semailid;
    }

    @JsonProperty("paddress1")
    public String getPaddress1() {
        return paddress1;
    }

    @JsonProperty("paddress1")
    public void setPaddress1(String paddress1) {
        this.paddress1 = paddress1;
    }

    @JsonProperty("paddress2")
    public Object getPaddress2() {
        return paddress2;
    }

    @JsonProperty("paddress2")
    public void setPaddress2(Object paddress2) {
        this.paddress2 = paddress2;
    }

    @JsonProperty("paddress3")
    public String getPaddress3() {
        return paddress3;
    }

    @JsonProperty("paddress3")
    public void setPaddress3(String paddress3) {
        this.paddress3 = paddress3;
    }

    @JsonProperty("pemailid")
    public String getPemailid() {
        return pemailid;
    }

    @JsonProperty("pemailid")
    public void setPemailid(String pemailid) {
        this.pemailid = pemailid;
    }

    @JsonProperty("pcityname")
    public Object getPcityname() {
        return pcityname;
    }

    @JsonProperty("pcityname")
    public void setPcityname(Object pcityname) {
        this.pcityname = pcityname;
    }

    @JsonProperty("stelephoneno")
    public Object getStelephoneno() {
        return stelephoneno;
    }

    @JsonProperty("stelephoneno")
    public void setStelephoneno(Object stelephoneno) {
        this.stelephoneno = stelephoneno;
    }

    @JsonProperty("stynumber")
    public Integer getStynumber() {
        return stynumber;
    }

    @JsonProperty("stynumber")
    public void setStynumber(Integer stynumber) {
        this.stynumber = stynumber;
    }

    @JsonProperty("caddress3")
    public Object getCaddress3() {
        return caddress3;
    }

    @JsonProperty("caddress3")
    public void setCaddress3(Object caddress3) {
        this.caddress3 = caddress3;
    }

    @JsonProperty("bloodgroup")
    public String getBloodgroup() {
        return bloodgroup;
    }

    @JsonProperty("bloodgroup")
    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    @JsonProperty("caddress1")
    public String getCaddress1() {
        return caddress1;
    }

    @JsonProperty("caddress1")
    public void setCaddress1(String caddress1) {
        this.caddress1 = caddress1;
    }

    @JsonProperty("caddress2")
    public String getCaddress2() {
        return caddress2;
    }

    @JsonProperty("caddress2")
    public void setCaddress2(String caddress2) {
        this.caddress2 = caddress2;
    }

    @JsonProperty("nationality")
    public String getNationality() {
        return nationality;
    }

    @JsonProperty("nationality")
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @JsonProperty("maritalstatus")
    public Object getMaritalstatus() {
        return maritalstatus;
    }

    @JsonProperty("maritalstatus")
    public void setMaritalstatus(Object maritalstatus) {
        this.maritalstatus = maritalstatus;
    }

    @JsonProperty("pstatename")
    public String getPstatename() {
        return pstatename;
    }

    @JsonProperty("pstatename")
    public void setPstatename(String pstatename) {
        this.pstatename = pstatename;
    }

    @JsonProperty("fathersname")
    public String getFathersname() {
        return fathersname;
    }

    @JsonProperty("fathersname")
    public void setFathersname(String fathersname) {
        this.fathersname = fathersname;
    }

    @JsonProperty("accountno")
    public Object getAccountno() {
        return accountno;
    }

    @JsonProperty("accountno")
    public void setAccountno(Object accountno) {
        this.accountno = accountno;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("pcellno")
    public String getPcellno() {
        return pcellno;
    }

    @JsonProperty("pcellno")
    public void setPcellno(String pcellno) {
        this.pcellno = pcellno;
    }

    @JsonProperty("bankname")
    public Object getBankname() {
        return bankname;
    }

    @JsonProperty("bankname")
    public void setBankname(Object bankname) {
        this.bankname = bankname;
    }

    @JsonProperty("category")
    public String getCategory() {
        return category;
    }

    @JsonProperty("category")
    public void setCategory(String category) {
        this.category = category;
    }

    @JsonProperty("cpin")
    public Integer getCpin() {
        return cpin;
    }

    @JsonProperty("cpin")
    public void setCpin(Integer cpin) {
        this.cpin = cpin;
    }

}

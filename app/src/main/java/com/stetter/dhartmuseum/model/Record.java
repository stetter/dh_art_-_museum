
package com.stetter.dhartmuseum.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;
import com.google.gson.annotations.Expose;

@Entity(tableName = "records")

public class Record {

    @Expose
    private String accessionmethod;
    @Expose
    private Long accessionyear;
    @Expose
    private Long accesslevel;
    @Expose
    private String century;
    @Expose
    private String classification;
    @Expose
    private Long classificationid;
    @Expose
    private Long colorcount;
    //@Expose
    //private List<Color> colors;
    //@Expose
    //private Object commentary;
    @Expose
    private String contact;
    @Expose
    private Long contextualtextcount;
    //@Expose
    //private Object copyright;
    @Expose
    private String creditline;
    @Expose
    private String culture;
    @Expose
    private Long datebegin;
    @Expose
    private String dated;
    @Expose
    private Long dateend;
    @Expose
    private String dateoffirstpageview;
    @Expose
    private String dateoflastpageview;
    @Expose
    private String department;
    //@Expose
    //private Object description;
    @Expose
    private String dimensions;
    @Expose
    private String division;
    //@Expose
    //private Object edition;
    @Expose
    private Long exhibitioncount;
    @Expose
    private Long groupcount;
    @Expose
    @PrimaryKey
    private Long id;
    @Expose
    private Long imagecount;
    @Expose
    private Long imagepermissionlevel;
    //@Expose
    //private List<Object> images;
    //@Expose
    //private Object labeltext;
    @Expose
    private String lastupdate;
    @Expose
    private Long markscount;
    @Expose
    private Long mediacount;
    //@Expose
    //private Object medium;
    @Expose
    private Long objectid;
    @Expose
    private String objectnumber;
    //@Expose
    //private List<Person> people;
    @Expose
    private Long peoplecount;
    /*@Expose
    private Object period;
    @Expose
    private Object periodid;
    @Expose
    private Object primaryimageurl;*/
    @Expose
    private String provenance;
    @Expose
    private Long publicationcount;
    @Expose
    private Long rank;
    @Expose
    private Long relatedcount;
   /* @Expose
    private List<SeeAlso> seeAlso;*/
 /*   @Expose
    private Object signed;*/
    @Expose
    private String standardreferencenumber;
   /* @Expose
    private Object state;
    @Expose
    private Object style;*/
    @Expose
    private String technique;
    @Expose
    private Long techniqueid;
    @Expose
    private String title;
    @Expose
    private Long titlescount;
    @Expose
    private Long totalpageviews;
    @Expose
    private Long totaluniquepageviews;
    @Expose
    private String url;
    @Expose
    private Long verificationlevel;
    @Expose
    private String verificationleveldescription;
    /*@Expose
    private List<Worktype> worktypes;*/

    public String getAccessionmethod() {
        return accessionmethod;
    }

    public void setAccessionmethod(String accessionmethod) {
        this.accessionmethod = accessionmethod;
    }

    public Long getAccessionyear() {
        return accessionyear;
    }

    public void setAccessionyear(Long accessionyear) {
        this.accessionyear = accessionyear;
    }

    public Long getAccesslevel() {
        return accesslevel;
    }

    public void setAccesslevel(Long accesslevel) {
        this.accesslevel = accesslevel;
    }

    public String getCentury() {
        return century;
    }

    public void setCentury(String century) {
        this.century = century;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public Long getClassificationid() {
        return classificationid;
    }

    public void setClassificationid(Long classificationid) {
        this.classificationid = classificationid;
    }

    public Long getColorcount() {
        return colorcount;
    }

    public void setColorcount(Long colorcount) {
        this.colorcount = colorcount;
    }

   /* public List<Color> getColors() {
        return colors;
    }

    public void setColors(List<Color> colors) {
        this.colors = colors;
    }

    public Object getCommentary() {
        return commentary;
    }

    public void setCommentary(Object commentary) {
        this.commentary = commentary;
    }
*/
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Long getContextualtextcount() {
        return contextualtextcount;
    }

    public void setContextualtextcount(Long contextualtextcount) {
        this.contextualtextcount = contextualtextcount;
    }

    /*public Object getCopyright() {
        return copyright;
    }

    public void setCopyright(Object copyright) {
        this.copyright = copyright;
    }
*/
    public String getCreditline() {
        return creditline;
    }

    public void setCreditline(String creditline) {
        this.creditline = creditline;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public Long getDatebegin() {
        return datebegin;
    }

    public void setDatebegin(Long datebegin) {
        this.datebegin = datebegin;
    }

    public String getDated() {
        return dated;
    }

    public void setDated(String dated) {
        this.dated = dated;
    }

    public Long getDateend() {
        return dateend;
    }

    public void setDateend(Long dateend) {
        this.dateend = dateend;
    }

    public String getDateoffirstpageview() {
        return dateoffirstpageview;
    }

    public void setDateoffirstpageview(String dateoffirstpageview) {
        this.dateoffirstpageview = dateoffirstpageview;
    }

    public String getDateoflastpageview() {
        return dateoflastpageview;
    }

    public void setDateoflastpageview(String dateoflastpageview) {
        this.dateoflastpageview = dateoflastpageview;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

   /* public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }*/

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

   /* public Object getEdition() {
        return edition;
    }

    public void setEdition(Object edition) {
        this.edition = edition;
    }*/

    public Long getExhibitioncount() {
        return exhibitioncount;
    }

    public void setExhibitioncount(Long exhibitioncount) {
        this.exhibitioncount = exhibitioncount;
    }

    public Long getGroupcount() {
        return groupcount;
    }

    public void setGroupcount(Long groupcount) {
        this.groupcount = groupcount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getImagecount() {
        return imagecount;
    }

    public void setImagecount(Long imagecount) {
        this.imagecount = imagecount;
    }

    public Long getImagepermissionlevel() {
        return imagepermissionlevel;
    }

    public void setImagepermissionlevel(Long imagepermissionlevel) {
        this.imagepermissionlevel = imagepermissionlevel;
    }

    /*public List<Object> getImages() {
        return images;
    }

    public void setImages(List<Object> images) {
        this.images = images;
    }

    public Object getLabeltext() {
        return labeltext;
    }

    public void setLabeltext(Object labeltext) {
        this.labeltext = labeltext;
    }*/

    public String getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(String lastupdate) {
        this.lastupdate = lastupdate;
    }

    public Long getMarkscount() {
        return markscount;
    }

    public void setMarkscount(Long markscount) {
        this.markscount = markscount;
    }

    public Long getMediacount() {
        return mediacount;
    }

    public void setMediacount(Long mediacount) {
        this.mediacount = mediacount;
    }

   /* public Object getMedium() {
        return medium;
    }

    public void setMedium(Object medium) {
        this.medium = medium;
    }*/

    public Long getObjectid() {
        return objectid;
    }

    public void setObjectid(Long objectid) {
        this.objectid = objectid;
    }

    public String getObjectnumber() {
        return objectnumber;
    }

    public void setObjectnumber(String objectnumber) {
        this.objectnumber = objectnumber;
    }

 /*   public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }*/

    public Long getPeoplecount() {
        return peoplecount;
    }

    public void setPeoplecount(Long peoplecount) {
        this.peoplecount = peoplecount;
    }

    /*public Object getPeriod() {
        return period;
    }

    public void setPeriod(Object period) {
        this.period = period;
    }

    public Object getPeriodid() {
        return periodid;
    }

    public void setPeriodid(Object periodid) {
        this.periodid = periodid;
    }

    public Object getPrimaryimageurl() {
        return primaryimageurl;
    }

    public void setPrimaryimageurl(Object primaryimageurl) {
        this.primaryimageurl = primaryimageurl;
    }*/

    public String getProvenance() {
        return provenance;
    }

    public void setProvenance(String provenance) {
        this.provenance = provenance;
    }

    public Long getPublicationcount() {
        return publicationcount;
    }

    public void setPublicationcount(Long publicationcount) {
        this.publicationcount = publicationcount;
    }

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    public Long getRelatedcount() {
        return relatedcount;
    }

    public void setRelatedcount(Long relatedcount) {
        this.relatedcount = relatedcount;
    }

   /* public List<SeeAlso> getSeeAlso() {
        return seeAlso;
    }

    public void setSeeAlso(List<SeeAlso> seeAlso) {
        this.seeAlso = seeAlso;
    }

    public Object getSigned() {
        return signed;
    }

    public void setSigned(Object signed) {
        this.signed = signed;
    }
*/
    public String getStandardreferencenumber() {
        return standardreferencenumber;
    }

    public void setStandardreferencenumber(String standardreferencenumber) {
        this.standardreferencenumber = standardreferencenumber;
    }

   /* public Object getState() {
        return state;
    }

    public void setState(Object state) {
        this.state = state;
    }

    public Object getStyle() {
        return style;
    }

    public void setStyle(Object style) {
        this.style = style;
    }*/

    public String getTechnique() {
        return technique;
    }

    public void setTechnique(String technique) {
        this.technique = technique;
    }

    public Long getTechniqueid() {
        return techniqueid;
    }

    public void setTechniqueid(Long techniqueid) {
        this.techniqueid = techniqueid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getTitlescount() {
        return titlescount;
    }

    public void setTitlescount(Long titlescount) {
        this.titlescount = titlescount;
    }

    public Long getTotalpageviews() {
        return totalpageviews;
    }

    public void setTotalpageviews(Long totalpageviews) {
        this.totalpageviews = totalpageviews;
    }

    public Long getTotaluniquepageviews() {
        return totaluniquepageviews;
    }

    public void setTotaluniquepageviews(Long totaluniquepageviews) {
        this.totaluniquepageviews = totaluniquepageviews;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getVerificationlevel() {
        return verificationlevel;
    }

    public void setVerificationlevel(Long verificationlevel) {
        this.verificationlevel = verificationlevel;
    }

    public String getVerificationleveldescription() {
        return verificationleveldescription;
    }

    public void setVerificationleveldescription(String verificationleveldescription) {
        this.verificationleveldescription = verificationleveldescription;
    }

  /*  public List<Worktype> getWorktypes() {
        return worktypes;
    }

    public void setWorktypes(List<Worktype> worktypes) {
        this.worktypes = worktypes;
    }
*/
}

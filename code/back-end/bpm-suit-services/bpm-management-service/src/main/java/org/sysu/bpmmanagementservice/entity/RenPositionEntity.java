package org.sysu.bpmmanagementservice.entity;

import javax.persistence.*;

@Entity
@Table(name = "ren_position")
public class RenPositionEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "note")
    private String note;

    @Column(name = "belongToId")
    private String belognToId;

    @Column(name = "reportedId")
    private String reportedId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getBelognToId() {
        return belognToId;
    }

    public void setBelognToId(String belognToId) {
        this.belognToId = belognToId;
    }

    public String getReportedId() {
        return reportedId;
    }

    public void setReportedId(String reportedId) {
        this.reportedId = reportedId;
    }
}

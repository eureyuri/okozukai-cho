package edu.brandeis.cs.eureynoguchi.expenselog;

public class ExpenseLogEntryData {
    private long id;
    private String description;
    private String notes;
    private String timeDate;

    public ExpenseLogEntryData(long id, String description, String notes,  String timeDate) {
        this.id = id;
        this.description = description;
        this.notes = notes;
        this.timeDate = timeDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getNotes() {
        return notes;
    }

    public String getTimeDate() {
        return timeDate;
    }
}

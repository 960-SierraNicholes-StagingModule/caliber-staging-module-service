package com.revature.backend.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "swot_analysis")
public class Swot {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  //Changed this to 'EAGER' to prevent a jackson crash
  @ManyToOne(fetch = FetchType.EAGER)	//removed CascadeType.ALL, this will cause problems with deletion.
  @JoinColumn(name = "associate_id")
  private Associate associate;

  //Changed this to 'EAGER' to prevent a jackson crash
  @ManyToOne(fetch = FetchType.EAGER)	//removed CascadeType.ALL, this will cause problems with deletion.
  @JoinColumn(name = "created_by")
  private Manager manager;

  @Column(name = "created_on")
  private Timestamp createdOn;
  
  @Column(name="description")
  private String description;

  @Column(name = "last_modified")
  private Timestamp lastModified;
  
  @JsonManagedReference //Prevents recursion in retrieve requests
  @OneToMany(mappedBy = "swot", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<AnalysisItem> analysisItems = new ArrayList<>();
  
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name="swot_id")
  private Set<ProgressReport> progressReports = new HashSet<>();

  public Swot() {}

  public Swot(int id, Associate associate_id, Manager manager, Timestamp createdOn, Timestamp lastModified, String description) {
    super();
    this.id = id;
    this.associate = associate_id;
    this.manager = manager;
    this.createdOn = createdOn;
    this.lastModified = lastModified;
    this.description = description;
  }

  public Swot(
      Associate associate_id, Manager manager, Timestamp createdOn, Timestamp lastModified) {
    super();
    this.associate = associate_id;
    this.manager = manager;
    this.createdOn = createdOn;
    this.lastModified = lastModified;
  }

  public Swot(
      Associate associate_id, Manager manager, Timestamp createdOn, Timestamp lastModified, String description) {
    super();
    this.associate = associate_id;
    this.manager = manager;
    this.createdOn = createdOn;
    this.lastModified = lastModified;
    this.description = description;
  }
  public Swot(Associate associate, Manager manager, Timestamp createdOn, Timestamp lastModified, String description,
			List<AnalysisItem> analysisItems) {
		super();
		this.associate = associate;
		this.manager = manager;
		this.createdOn = createdOn;
		this.lastModified = lastModified;
		this.description = description;
		this.analysisItems = analysisItems;
	  }

  public Swot(Associate associate, Manager manager, Timestamp createdOn, Timestamp lastModified, String description,
		List<AnalysisItem> analysisItems, Set<ProgressReport> progressReports) {
	super();
	this.associate = associate;
	this.manager = manager;
	this.createdOn = createdOn;
	this.lastModified = lastModified;
	this.description = description;
	this.analysisItems = analysisItems;
	this.progressReports = progressReports;
  }

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public Associate getAssociate() {
	return associate;
}

public void setAssociate(Associate associate) {
	this.associate = associate;
}

public Manager getManager() {
	return manager;
}

public void setManager(Manager manager) {
	this.manager = manager;
}

public Timestamp getCreatedOn() {
	return createdOn;
}

public void setCreatedOn(Timestamp createdOn) {
	this.createdOn = createdOn;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public Timestamp getLastModified() {
	return lastModified;
}

public void setLastModified(Timestamp lastModified) {
	this.lastModified = lastModified;
}

public void setLastModifiedNow() {
	this.lastModified = new Timestamp(System.currentTimeMillis());
}


public List<AnalysisItem> getAnalysisItems() {
	return analysisItems;
}

public void setAnalysisItems(List<AnalysisItem> analysisItems) {
	this.analysisItems = analysisItems;
}

public Set<ProgressReport> getProgressReports() {
	return progressReports;
}

public void setProgressReports(Set<ProgressReport> progressReports) {
	this.progressReports = progressReports;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((analysisItems == null) ? 0 : analysisItems.hashCode());
	result = prime * result + ((associate == null) ? 0 : associate.hashCode());
	result = prime * result + ((createdOn == null) ? 0 : createdOn.hashCode());
	result = prime * result + ((description == null) ? 0 : description.hashCode());
	result = prime * result + id;
	result = prime * result + ((lastModified == null) ? 0 : lastModified.hashCode());
	result = prime * result + ((manager == null) ? 0 : manager.hashCode());
	result = prime * result + ((progressReports == null) ? 0 : progressReports.hashCode());
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Swot other = (Swot) obj;
	if (analysisItems == null) {
		if (other.analysisItems != null)
			return false;
	} else if (!analysisItems.equals(other.analysisItems))
		return false;
	if (associate == null) {
		if (other.associate != null)
			return false;
	} else if (!associate.equals(other.associate))
		return false;
	if (createdOn == null) {
		if (other.createdOn != null)
			return false;
	} else if (!createdOn.equals(other.createdOn))
		return false;
	if (description == null) {
		if (other.description != null)
			return false;
	} else if (!description.equals(other.description))
		return false;
	if (id != other.id)
		return false;
	if (lastModified == null) {
		if (other.lastModified != null)
			return false;
	} else if (!lastModified.equals(other.lastModified))
		return false;
	if (manager == null) {
		if (other.manager != null)
			return false;
	} else if (!manager.equals(other.manager))
		return false;
	if (progressReports == null) {
		if (other.progressReports != null)
			return false;
	} else if (!progressReports.equals(other.progressReports))
		return false;
	return true;
}

@Override
public String toString() {
	return "Swot [id=" + id + ", associate=" + associate + ", manager=" + manager + ", createdOn=" + createdOn
			+ ", description=" + description + ", lastModified=" + lastModified + ", analysisItems=" + analysisItems
			+ ", progressReports=" + progressReports + "]";
}	
}

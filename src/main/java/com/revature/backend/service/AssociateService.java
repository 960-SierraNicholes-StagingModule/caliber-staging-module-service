package com.revature.backend.service;

import java.util.List;

import com.revature.backend.model.Associate;
import com.revature.backend.repository.AssociateRepository;

import org.springframework.stereotype.Service;

@Service
public class AssociateService {
  private final AssociateRepository associateRepo;

  public AssociateService(AssociateRepository associateRepo) {
    this.associateRepo = associateRepo;
  }

  public List<Associate> getAllAssociates() {
      return null;
  }

  public List<Associate> saveAssociates(List<Associate> aList) {
    return associateRepo.saveAll(aList);
  }

  public Associate getAssociateById(int id) {
    return associateRepo.findById(id);
  }

  public void updateAssociate(Associate associate) {
    associateRepo.save(associate);
  }
  public Associate getAssociateByEmail(String email) {
	    return associateRepo.findByEmail(email);
	  }
}

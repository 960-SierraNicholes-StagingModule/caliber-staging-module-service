package com.revature.backend.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.stereotype.Service;

import com.revature.backend.model.AnalysisItem;
import com.revature.backend.model.Swot;
import com.revature.backend.repository.AnalysisItemRepository;
import com.revature.backend.repository.SwotRepository;

@Service
public class SwotService {
  private final SwotRepository swotRepository;
  private final AnalysisItemRepository analysisItemRepository;

  public SwotService(SwotRepository swotRepository,
        AnalysisItemRepository analysisItemRepository) {
    this.swotRepository = swotRepository;
    this.analysisItemRepository = analysisItemRepository;
  }

	/**
	 *
	 * @param swot
	 * @return boolean indicating whether save was successful or not
	 *
	 * Creates a new SWOT with initial AnalysisItems.
	 *
	 * The Swot object is the parent object for each
	 * individual AnalysisItem (Swot > AnalysisItem).
	 *
	 * Each AnalysisItem has a foreign key reference to
	 * it's owning SWOT.
	 *
	 * The JSON array of AnalysisItems needs to be stored
	 * in a holder list where it will have the foreign key
	 * set to the parent SWOT's id.
	 *
	 * After, the List of AnalysisItems is
	 * properly assigned to it's parent
	 * Swot object.
	 *
	 * Returns true if successful, false otherwise.
	 */
	public boolean createNewSwot(Swot swot) {
		return swotRepository.save(swot) != null; // we create parent object in db
	}

	/**
	*
	* @param associateId
	* @return a list of the corresponding associate's swots
	*
	* Retrieves all SWOTs by the AssociateId.
	* Takes in the Associate's id (as an int)
	* as a parameter.
	* Returns a List of Swots for that Associate.
	*/
	public List<Swot> retrieveAllSwotByAssociateId(int associateId) {
		return swotRepository.findAllByAssociateId(associateId);
	}
	
	/**
	*
	* @param swotId
	* @return a swot of the corresponding swot id
	*
	* Retrieves a swot by a swot id
	* Takes in the swot's id (as an int)
	* as a parameter.
	* Returns a Swot for that id
	*/
	public Swot retrieveSwotById(int swotId) {
		return swotRepository.findById(swotId);
	}

	/**
	 *
	 * @param analysisItem
	 * @return true if successfully saved in database, false otherwise
	 *
	 * Creates a new AnalysisItem for a SWOT.
	 * Accepts the AnalysisItem as a parameter.
	 * Returns true if successful, false otherwise.
	 */
	public boolean createNewItem(AnalysisItem analysisItem) {
		Swot updateSwot = swotRepository.findById(analysisItem.getSwot().getId());
		updateSwot.setLastModifiedNow();
		swotRepository.save(updateSwot);
		return analysisItemRepository.save(analysisItem) != null;
	}

	/**
	 *
	 * @param analysisItem
	 * @return true if successfully updated in database, false if not
	 *
	 * Updates an AnalysisItem.
	 * Takes the AnalysisItem object that needs to
	 * be updated with the changed fields.
	 *
	 * Prior to calling the repo, set the respective SWOT's
	 * lastModified field to System.currentTimeMillis() and
	 * then save the object to the database to update.
	 *
	 * Returns the updated item if successful.
	 */
	public AnalysisItem updateItem(AnalysisItem analysisItem) {
		Swot updateSwot = swotRepository.findById(analysisItem.getSwot().getId());
		updateSwot.setLastModifiedNow();
		swotRepository.save(updateSwot);
		return analysisItemRepository.save(analysisItem);
	}

	/**
	 *
	 * @param analysisItemId
	 * @return true if successfully deleted, false otherwise
	 *
	 * Deletes an AnalysisItem from a SWOT by
	 * its id.
	 * Only takes in the Id as an integer as a
	 * parameter.
	 * Should return true if successful, false
	 * otherwise.
	 */
	public boolean deleteItem(int analysisItemId) {
		Optional<AnalysisItem> optionalAnalysisItem = analysisItemRepository.findById(analysisItemId);
		AnalysisItem analysisItem = optionalAnalysisItem.orElse(null);
		if(analysisItem!=null) {
			Swot updateSwot = swotRepository.findById(analysisItem.getSwot().getId());
			updateSwot.setLastModifiedNow();
			swotRepository.save(updateSwot);
			analysisItemRepository.deleteById(analysisItemId);
		}
		return false; 
	}

	/**
	 *
	 * Method to retrieve all SWOTs found in the
	 * database.
	 * Currently only used for testing purposes.
	 *
	 * @return a list of all swots in the database
	 */
	public List<Swot> retrieveAllSwot() {
		return swotRepository.findAll();
	}

	public Swot retrieveSwotById(Integer id) { return swotRepository.getOne(id); }

	public boolean updateSwot(Swot swot) {
		swotRepository.save(swot);
		return true;
	}
}

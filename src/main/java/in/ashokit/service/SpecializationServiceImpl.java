package in.ashokit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.entity.Specialization;
import in.ashokit.exception.SpecializationNotFoundException;
import in.ashokit.repo.SpecializationRepo;
@Service
public class SpecializationServiceImpl implements ISpecializationService {
    @Autowired
	private SpecializationRepo repo;

	@Override
	public Long saveSpecialization(Specialization spec) {
		
		return repo.save(spec).getId();
	}

	@Override
	public List<Specialization> getAllSpecializations() {
		
		return repo.findAll();
	}

	@Override
	public void removeSpecialization(Long id) {
	//	repo.deleteById(id);
		repo.delete(getOneSpecialization(id));
	}

	@Override
	public Specialization getOneSpecialization(Long id) {
	Optional<Specialization>optional=repo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}else{
			throw new SpecializationNotFoundException(id+"Not Found");
		}
	}

	@Override
	public void updateSpecialization(Specialization spec) {
		repo.save(spec);
		
	}

	@Override
	public boolean isSpecCodeExist(String specCode) {
		/*
		 * Integer count = repo.getSpecCodeCount(specCode); 
		 * boolean exist =count>0 ? true : false; 
		 * return exist;
		 */
		return repo.getSpecCodeCount(specCode)>0;
	}
	
	

}

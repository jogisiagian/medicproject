package com.mini.medicproject.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mini.medicproject.models.Medical_Item;

@Repository
public interface Medical_ItemRepo extends JpaRepository<Medical_Item, Long>{
	
	@Query(value="SELECT m.name, m.packaging, m.price_min, m.price_max, m.indication, m.image_path FROM m_medical_item m JOIN m_medical_item_category mc ON "
			+ "m.medical_item_category_id = mc.id JOIN m_medical_item_segmentation ms ON"
			+ "m.medical_item_segmentation_id = ms.id WHERE"
			+ "mc.name = ?1 AND LOWER(m.name) LIKE %?2% OR LOWER(m.indication) LIKE %?2% AND ms.name LIKE %?3% AND m.price_min >= ?4 AND"
			+ "m.price_max <= ?5", nativeQuery= true)
	List<Map<String, Object>> getNama(String category, String keyword, String segmentation, Long min, Long max);

}

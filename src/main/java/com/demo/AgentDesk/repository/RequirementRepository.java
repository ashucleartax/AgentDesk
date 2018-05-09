package com.demo.AgentDesk.repository;

import com.demo.AgentDesk.entity.Requirement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ashutoshpandey on 08/05/18.
 */
//Its not required for current problem. Just made it for sake of completeness
@Repository
@Transactional
public interface RequirementRepository extends CrudRepository<Requirement, Long> {
}

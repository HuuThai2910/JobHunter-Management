/*
 * @ (#) .java    1.0
 * Copyright (c)  IUH. All rights reserved.
 */
package edu.iuh.fit.backend.service.impl;

import edu.iuh.fit.backend.domain.Skill;
import edu.iuh.fit.backend.domain.Subscriber;
import edu.iuh.fit.backend.repository.SkillRepository;
import edu.iuh.fit.backend.repository.SubscriberRepository;
import edu.iuh.fit.backend.util.error.InvalidException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/*
 * @description
 * @author: Huu Thai
 * @date:
 * @version: 1.0
 */
@Service
@AllArgsConstructor
public class SubscriberServiceImpl implements edu.iuh.fit.backend.service.SubscriberService {
    private final SubscriberRepository subscriberRepository;
    private final SkillRepository skillRepository;

    @Override
    public Subscriber handleCreate(Subscriber subscriber) throws InvalidException {
        boolean isExists = this.subscriberRepository.existsByEmail(subscriber.getEmail());
        if(isExists == true){

                throw new InvalidException("Email " +subscriber.getEmail() + "exists");

        }
        if(subscriber.getSkills() != null){
            List<Long> reqSkills = subscriber.getSkills()
                    .stream().map(Skill::getId)
                    .toList();
            List<Skill> dbSkills = this.skillRepository.findByIdIn(reqSkills);
            subscriber.setSkills(dbSkills);
        }
        return this.subscriberRepository.save(subscriber);
    }
    @Override
    public Subscriber handleUpdate(Subscriber subDB, Subscriber subsRequest){
        if(subsRequest.getSkills() != null){
            List<Long> reqSkills = subsRequest.getSkills()
                    .stream().map(Skill::getId)
                    .toList();
            List<Skill> dbSkills = this.skillRepository.findByIdIn(reqSkills);
            subDB.setSkills(dbSkills);
        }
        return this.subscriberRepository.save(subDB);
    }

    @Override
    public Subscriber findById(Long id){
        return this.subscriberRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Subscriber not found"));
    }

}

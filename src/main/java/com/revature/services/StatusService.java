package com.revature.services;

import com.revature.daos.StatusDAO;
import com.revature.exceptions.StatusNotFoundException;
import com.revature.models.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService {
    private final StatusDAO statusDao;

    @Autowired
    public StatusService(StatusDAO statusDao){
        this.statusDao = statusDao;
    }

    public Status addStatus(Status status){

        Status returnedStatus = statusDao.save(status);

        if (returnedStatus.getId() > 0){
            // Successful
            // TODO put success log
        } else{
            // This was a failure to add the course
            // TODO put warning log
        }

        return returnedStatus;

    }

    public Status updateStatus(Status s){
        return statusDao.save(s);
    }

    public boolean deleteStatus(int id){
        statusDao.deleteById(id);

        return !(statusDao.existsById(id));
    }

    public Status findStatusById(int id){
        return statusDao.findById(id).orElseThrow(() -> new StatusNotFoundException("No status found with id: " + id));
    }

    public List<Status> getAllStatus(){
        List<Status> status = statusDao.findAll();

        return status;
    }

    public List<Status> searchStatuses(String searchPattern){
        return statusDao.findByNameContainingIgnoreCase(searchPattern);
    }
}

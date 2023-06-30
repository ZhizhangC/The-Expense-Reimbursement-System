package com.revature.controllers;

import com.revature.models.Status;
import com.revature.services.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("statuses")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class StatusController {
    private final StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping
    public List<Status> getAllCoursesHandler(@RequestParam(name = "search", required = false) String searchPattern){

        if (searchPattern != null){
            return statusService.searchStatuses(searchPattern);
        }

        return statusService.getAllStatus();
    }

    @GetMapping("{id}")
    public Status findStatusByIdHandler(@PathVariable("id") int id){
        return statusService.findStatusById(id);
    }

    @PostMapping
    public Status createStatusHandler(@RequestBody Status s){
        return statusService.addStatus(s);
    }

    @PutMapping
    public Status updateStatusHandler(@RequestBody Status s){
        return statusService.updateStatus(s);
    }

    @DeleteMapping("delete/{id}")
    public boolean deleteStatusHandler(@PathVariable("id") int id){
        return statusService.deleteStatus(id);
    }
}
